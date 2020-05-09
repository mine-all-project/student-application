package org.example.gongjiao.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Stands extends BaseEntity {
    @Column(unique = true)
    private String name;
    @ManyToMany
    private List<Linees> linesList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
