package org.example.gongjiao.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Stands extends BaseEntity {
    private String name;
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
