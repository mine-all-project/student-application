package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class FileInfo extends BaseEntity {
    @Column(columnDefinition = "varchar(256) default null ")
    private String oldName;
    @Column(columnDefinition = "varchar(256) default null ")
    private String virtualPath;
    @Column(columnDefinition = "varchar(256) default null ")
    private String uploadPath;
    @Column(columnDefinition = "varchar(256) default null ")
    private String contentType;
    @Column(columnDefinition = "long default null ")
    private Long fileSize;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
