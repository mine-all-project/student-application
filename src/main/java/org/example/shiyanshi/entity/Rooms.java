package org.example.shiyanshi.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
/*
 * 实验室信息
 */
@Entity
public class Rooms extends BaseEntity {
    @Column(columnDefinition = "varchar(64) comment '名称'")
    private String name;

    @OneToMany
    private List<Machines> machines;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
