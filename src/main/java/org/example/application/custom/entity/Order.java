package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.common.Groups;
import org.example.application.system.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@ToString
@Table(name = "order_", schema = "demo-02")
public class Order extends BaseEntity {
    @Column(columnDefinition = "varchar(64) default null comment '订单号'")
    private String no;
    @NotBlank(message = "收货人不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '收货人'")
    private String name;
    @NotBlank(message = "联系电话人不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '联系电话'")
    private String phone;
    @NotBlank(message = "地址不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '地址'")
    private String address;
    @Column(columnDefinition = "tinyint default 0 comment '订单状态 0:已送货 1:待送货'")
    private String status;
    @ManyToOne
    private Goods goods;
    @ManyToOne
    private SysUser user;
}
