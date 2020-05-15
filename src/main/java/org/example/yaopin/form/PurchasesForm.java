package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;
import org.example.yaopin.entity.Goods;
import org.example.yaopin.entity.Purchases;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PurchasesForm {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private String id;
    @NotBlank(message = "产品名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;
    @NotNull(message = "供应商不能为空", groups = {IsAdd.class, IsEdit.class})
    private String supply;
    @NotNull(message = "联系电话不能为空", groups = {IsAdd.class, IsEdit.class})
    private String phone;
    @NotEmpty(message = "数量不能为空", groups = {IsAdd.class, IsEdit.class})
    private Long counts;
    private Goods goods;
}
