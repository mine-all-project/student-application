package org.example.fangwuzulin.form;

import lombok.Data;
import org.example.fangwuzulin.config.groups.*;
import org.example.fangwuzulin.entity.Houses;
import org.example.fangwuzulin.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
public class HousesForm {
    private String id;

    @NotBlank(message = "标题不能为空", groups = {IsNotNull.class})
    private String title;

    @NotBlank(message = "地址不能为空", groups = {IsNotNull.class})
    private String address;

    @NotNull(message = "价格不能为空", groups = {IsNotNull.class})
    private BigDecimal price;

    private String note;

    private String img_src;

    private String contract_id;

    private String province;

    private String city;

    private String area;

    public Houses toEntity() {
        Houses entity = new Houses();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setAddress(this.address);
        entity.setPrice(this.price);
        entity.setNote(this.note);
        entity.setImg_src(this.img_src);
        entity.setProvince(this.province);
        entity.setCity(this.city);
        entity.setArea(this.area);
        entity.setContract_id(this.contract_id);
        return entity;
    }
}
