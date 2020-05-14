package org.example.shiyanshi.form;

import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.groups.IsAdd;
import org.example.shiyanshi.config.groups.IsEdit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 入库表单
 */
@Getter
@Setter
public class SalesForm {
    private String id;
    @NotBlank(message = "商品信息不能为空", groups = {IsAdd.class, IsEdit.class})
    private String goodsId;
    @NotEmpty(message = "数量不能为空", groups = {IsAdd.class, IsEdit.class})
    private Long counts;
    @NotNull(message = "类型不能为空", groups = {IsAdd.class, IsEdit.class})
    private Integer type;
}
