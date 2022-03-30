package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "order_")
@ToString
public class Order extends BaseEntity {
    @Column(columnDefinition = "decimal(10,2) default null comment '金额'")
    private BigDecimal price;
    @ManyToOne
    private Goods goods;
    @ManyToOne
    private SysUser user;
}
