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
