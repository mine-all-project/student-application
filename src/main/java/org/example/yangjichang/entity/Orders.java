package org.example.yangjichang.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Orders extends BaseEntity {

    private Integer count;
    private BigDecimal sum;
    @CreatedDate
    private LocalDate orderTime;
    @ManyToOne
    private SysUser sysUser;
    @ManyToOne
    private Animal animals;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
