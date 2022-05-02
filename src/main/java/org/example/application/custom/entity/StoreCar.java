package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class StoreCar extends BaseEntity {
    @ManyToMany
    private List<Goods> goods;
    @OneToOne
    private SysUser user;
}
