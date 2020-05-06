package org.example.fangwuzulin.form;

import lombok.Data;
import org.example.fangwuzulin.config.groups.*;
import org.example.fangwuzulin.entity.Houses;
import org.example.fangwuzulin.entity.SysUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class HousesForm {
    private String id;

    @NotBlank(message = "地址不能为空", groups = {IsNotNull.class})
    private String address;

    @NotBlank(message = "价格不能为空", groups = {IsNotNull.class})
    private String price;

    private String key_words;

    private String note;

    private String img_src;

    public Houses toEntity() {
        Houses houses = new Houses();
        houses.setId(this.id);
        houses.setAddress(this.address);
        houses.setPrice(this.price);
        houses.setKey_words(this.key_words);
        houses.setNote(this.note);
        houses.setImg_src(this.img_src);
        return houses;
    }
}
