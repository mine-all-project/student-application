package org.example.textreader.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.textreader.system.entity.BaseEntity;
import org.example.textreader.system.entity.FileInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Setter
@Getter
@Entity
public class Paper extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null comment '标题'")
    private String title;
    @OneToOne
    private FileInfo fileInfo;
    @Column(columnDefinition = "longtext default null comment '文件内容'")
    private String content;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
