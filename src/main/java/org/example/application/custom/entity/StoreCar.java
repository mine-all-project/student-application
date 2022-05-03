package org.example.application.custom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.common.BaseEntity;
import org.example.application.system.entity.SysUser;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class StoreCar extends BaseEntity {
    @ManyToMany
    private List<StoreCarItem> item;
    @OneToOne
    private SysUser user;

    @Getter
    @Setter
    @Entity
    @ToString
    public static class StoreCarItem extends BaseEntity {
        @ManyToOne
        private Goods goods;
        @Column(columnDefinition = "int default 1 comment '数量' ")
        private Integer countNum;
    }
}
