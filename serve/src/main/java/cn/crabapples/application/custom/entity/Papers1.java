package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 文章实体类（mysql）
 */
@Entity
@Getter
@Setter
//@Document("FileData")
public class Papers1 extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '标题'")
    private String title;
    @Column(columnDefinition = "longtext comment '正文'")
    private String content;
    @ManyToOne
    @JSONField(serialize = false)
    private SysUser createBy;
    @Transient
    private SysUserDTO user;
    @ManyToMany
    private List<Tags> tagsList;
    @ManyToMany
    private List<PaperFileInfo> fileList;
    private int readerCount;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
