package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.common.Groups;
import org.example.application.system.entity.SysUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_")
@ToString
public class Order extends BaseEntity {
    @Column(columnDefinition = "varchar(64) default null comment '订单号'")
    private String no;
    @NotBlank(message = "收货人不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '收货人'")
    private String name;
    @NotBlank(message = "联系电话不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '联系电话'")
    private String phone;
    @Column(columnDefinition = "decimal(10,2) default null comment '金额'")
    private BigDecimal price;
    @NotBlank(message = "地址不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '地址'")
    private String address;
    @Column(columnDefinition = "tinyint default 1 comment '订单状态 0:已送货 1:待送货'")
    private String status;
    @Column(columnDefinition = "longtext default null comment '备注'")
    private String note;
    @ManyToMany
    private List<StoreCar.StoreCarItem> goods;
    @ManyToOne
    private SysUser user;

}
