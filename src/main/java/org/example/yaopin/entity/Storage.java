package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
@Setter
public class Storage extends BaseEntity {
    @OneToOne
    private Purchases purchases;

    @OneToOne
    private Goods goods;


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
