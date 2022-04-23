package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.groups.IsNotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MoneyForm {
    @NotBlank(message = "id不能为空", groups = IsNotNull.class)
    private String id;
    @NotNull(message = "金额不能为空", groups = IsNotNull.class)
    private BigDecimal moneyPlus;
    @NotNull(message = "金额不能为空", groups = IsNotNull.class)
    private BigDecimal moneyReduce;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
