package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class DatabaseBak extends BaseEntity {
    @Column(columnDefinition = "varchar(128) comment '备份文件路径'")
    private String filePath;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
