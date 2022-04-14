package org.example.application.system.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(schema = "system")
public class FileInfo extends BaseEntity {
    //原文件名
    private String oldName;
    //文件虚拟访问路径
    private String virtualPath;
    //文件真实保存访问路径
    private String uploadPath;
    //文件类型
    private String contentType;
    //文件大小
    private Long fileSize;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
