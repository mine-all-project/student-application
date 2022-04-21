package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class Paper extends BaseEntity {
    //名称
    @Column
    private String title;
    //详情
    @Column
    private String content;
    //状态 0:正常 1:待审 2:驳回
    private Integer status;
    private String type;
    @OneToOne
    private SysUser publisher;
    @ManyToMany
    private List<FileInfo> files;
    @OneToMany
    private List<Message> messages;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
