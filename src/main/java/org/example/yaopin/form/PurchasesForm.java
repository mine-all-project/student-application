package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;
import org.example.yaopin.entity.Goods;
import org.example.yaopin.entity.Purchases;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PurchasesForm {
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
