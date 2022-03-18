package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@Entity
public class Message extends BaseEntity {
    @Column(columnDefinition = "longtext default null comment '内容'")
    private String content;
    @OneToMany
    private List<Message> messageList;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
