package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.IsAdd;
import org.example.yaopin.config.groups.IsEdit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 入库表单
 */
public class SalesForm {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private String id;
    @NotBlank(message = "商品信息不能为空", groups = {IsAdd.class, IsEdit.class})
    private String goodsId;
    @NotEmpty(message = "数量不能为空", groups = {IsAdd.class, IsEdit.class})
    private Long counts;
    @NotNull(message = "类型不能为空", groups = {IsAdd.class, IsEdit.class})
    private Integer type;
}
