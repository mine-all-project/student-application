package org.example.application.custom.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "order_")
public class Order extends BaseEntity {
//    @Column(columnDefinition = "varchar(256) default null comment '词条'")
//    private String keyword;
    @Column(columnDefinition = "tinyint default null comment '0:已缴费 1:待缴费 2:逾期'")
    private Integer status;
    @Column(columnDefinition = "decimal(10,2) default 0 comment '金额'")
    private BigDecimal price;
    @Column(columnDefinition = "tinyint default null comment '0:月供缴费 1:其他缴费'")
    private Integer type;
    @Column(columnDefinition = "varchar(256) default null comment '备注'")
    private String note;
    @Column(columnDefinition = "varchar(64) default null comment '流水号'")
    private String no;
    @Column(columnDefinition = "timestamp  comment '到期时间'")
    private LocalDate lastTime;
    @ManyToOne
    private Person person;

    @ManyToOne
    @CreatedBy
    private SysUser createBy;
    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> childrenOrder;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
