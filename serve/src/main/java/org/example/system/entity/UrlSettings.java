package org.example.system.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.example.common.BaseEntity;
import org.example.common.groups.IsNotNull;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class UrlSettings extends BaseEntity {

    @Column(columnDefinition = "longtext comment '网址'")
    private String url;

    @Column(columnDefinition = "varchar(64) comment '关键词'", unique = true)
    private String keyWords;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
