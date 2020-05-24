package org.example.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
/**
 * 媒体文件表
 */
public class AudioFile extends BaseEntity {
    private String title;
    @Column(columnDefinition = "longtext")
    private String url;
    @Column(columnDefinition = "varchar(64)")
    private String keyWord;
    private Integer sort;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
