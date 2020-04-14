package org.example.tuole.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * TODO 订单模块实体类
 *
 *
 * 2020/4/7 22:56
 *
 *
 * pc-name 29404
 */
@Getter
@Setter
@Entity
public class Orders extends BaseEntity {

    private String keyword;
    @CreatedDate
    private LocalDate orderTime;
    @ManyToOne
    private SysUser sysUser;
    @ManyToMany
    private List<Goods> goods;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
