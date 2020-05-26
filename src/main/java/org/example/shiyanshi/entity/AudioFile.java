package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AudioFile extends BaseEntity {
    @Column(columnDefinition = "varchar(128) comment '图片url'")
    private String src;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
