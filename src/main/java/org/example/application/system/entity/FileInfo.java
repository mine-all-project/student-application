package org.example.application.system.entity;

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
    //原文件名
    @Column(columnDefinition = "varchar(256) default null ")
    private String oldName;
    //文件虚拟访问路径
    @Column(columnDefinition = "varchar(256) default null ")
    private String virtualPath;
    //文件真实保存访问路径
    @Column(columnDefinition = "varchar(256) default null ")
    private String uploadPath;
    //文件类型
    @Column(columnDefinition = "varchar(256) default null ")
    private String contentType;
    //文件大小
    @Column(columnDefinition = "long default null ")
    private Long fileSize;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
