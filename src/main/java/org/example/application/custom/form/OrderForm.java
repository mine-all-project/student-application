package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Order;
import org.example.application.custom.entity.Person;
import org.springframework.beans.BeanUtils;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderForm extends BaseForm {
    @NotBlank(message = "词条不能为空", groups = IsNotNull.class)
    private String keyword;
    private Integer status;
    @NotNull(message = "金额不能为空", groups = IsNotNull.class)
    private BigDecimal price;
    @NotNull(message = "类型不能为空", groups = IsNotNull.class)
    private Integer type;
    private String note;
    private String no;
    @ManyToOne
    private Person person;

    @Override
    public Order toEntity() {
        Order entity = new Order();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
