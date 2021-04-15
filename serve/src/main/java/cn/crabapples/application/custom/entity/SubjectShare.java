package cn.crabapples.application.custom.entity;

import cn.crabapples.application.common.BaseEntity;
import cn.crabapples.application.system.dto.SysUserDTO;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 科研项目分享实体类
 */
@Setter
@Getter
@Entity
public class SubjectShare extends BaseEntity {
    @OneToOne
    private Subject subject;

    @ManyToMany
    @JSONField(serialize = false)
    private List<SysUser> shareUser;

    @ManyToOne
    @JSONField(serialize = false)
    private SysUser shareBy;

    @Transient
    private List<SysUserDTO> shareUserList;

    @Transient
    private SysUserDTO shareByUser;

    @Column(columnDefinition = "tinyint(1) default 0 comment '状态 0:生效 1:未生效'")
    private int status;

}
