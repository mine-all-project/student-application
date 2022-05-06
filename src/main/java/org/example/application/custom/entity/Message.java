package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class Message extends BaseEntity {
    //内容
    @Column(columnDefinition = "longtext")
//    @Column(columnDefinition = "CLOB")
    private String content;
    //回复
    @Column(columnDefinition = "longtext")
//    @Column(columnDefinition = "CLOB")
    private String recontent;
    @OneToOne
    private SysUser publisher;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
