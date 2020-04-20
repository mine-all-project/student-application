package org.example.yangjichang.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Orders extends BaseEntity {
    private String goodsId;
    private String orderNumber;
    private Integer counts;
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
