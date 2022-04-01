//package org.example.application.custom.form;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.Getter;
//import lombok.Setter;
//import org.example.application.common.BaseForm;
//import org.example.application.common.Groups;
//import org.example.application.custom.entity.Goods;
//import org.example.application.custom.entity.Order;
//import org.springframework.beans.BeanUtils;
//
//import javax.validation.constraints.NotNull;
//import java.math.BigDecimal;
//
//@Getter
//@Setter
//public class OrderForm extends BaseForm<Order> {
//    @NotNull(message = "金额不能为空", groups = Groups.IsNotNull.class)
//    private BigDecimal price;
//    @NotNull(message = "金额不能为空", groups = Groups.IsNotNull.class)
//    private Goods goods;
//
//    @Override
//    public Order toEntity() {
//        Order entity = new Order();
//        BeanUtils.copyProperties(this, entity);
//        return entity;
//    }
//
//    @Override
//    public String toString() {
//        return JSONObject.toJSONString(this);
//    }
//}
