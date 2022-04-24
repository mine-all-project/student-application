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
    //状态 0:审核成功 1:审核中 2:审核失败
    private Integer status;
    private String type;
    private String secondType;
    private String note;
    @ManyToOne
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
