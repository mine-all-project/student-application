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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@ToString
public class Temperature extends BaseEntity {
    @NotBlank(message = "姓名不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "varchar(64) default null comment '名称'")
    private String name;
    @NotNull(message = "体温不能为空", groups = {Groups.IsNotNull.class})
    @Column(columnDefinition = "tinyint default null comment '价格'")
    private Integer value;
    @ManyToOne
    private SysUser user;
}
