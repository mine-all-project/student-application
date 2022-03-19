package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Setter
@Getter
@Entity
public class Message extends BaseEntity {
    @Column(columnDefinition = "longtext default null comment '内容'")
    private String content;
    @Column(columnDefinition = "tinyint default 1 comment '状态 0:正常 1:待审 2:驳回 3:'")
    private Integer status;
//    @OneToMany
//    private List<Message> messageList;
    @OneToOne
    private SysUser publisher;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
