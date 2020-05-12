package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.IsAdd;
import org.example.yaopin.config.groups.IsEdit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
/**
 * 库存信息
 */
public class Storages extends BaseEntity {
    @OneToOne
    private Purchases purchases;
    @OneToOne
    private Goods goods;
    @Column(columnDefinition = "bit(1) default 0 not null comment '状态标记 0:入库 1:出库'")
    private Integer type;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
