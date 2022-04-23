package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Person;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class PersonForm extends BaseForm {
    @NotBlank(message = "姓名不能为空", groups = IsNotNull.class)
    private String name;
    @NotNull(message = "性别不能为空", groups = IsNotNull.class)
    private Integer gender;
    @NotBlank(message = "手机号不能为空", groups = IsNotNull.class)
    private String phone;
    @NotNull(message = "钱包金额不能为空", groups = IsNotNull.class)
    private BigDecimal money;
    @NotBlank(message = "运营商不能为空", groups = IsNotNull.class)
    private String type;

    @Override
    public Person toEntity() {
        Person entity = new Person();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
