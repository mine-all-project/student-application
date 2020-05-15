package org.example.yaopin.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
/**
 * 销售信息
 */
public class Sales extends BaseEntity {
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Long getTempCount() {
        return tempCount;
    }

    public void setTempCount(Long tempCount) {
        this.tempCount = tempCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @ManyToOne
    private Goods goods;
    @Column(columnDefinition = "int(10) not null comment '数量'")
    private Long counts;
    @Column(columnDefinition = "int(10) not null comment '临时库存'")
    private Long tempCount;
    @Column(columnDefinition = "tinyint(1) not null comment '状态标记 0:售出 1:退货'")
    private Integer type;
    @Column(columnDefinition = "tinyint(1) not null comment '状态标记 0:正常 1:已退'")
    private Integer status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
