package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.IsAdd;
import org.example.yaopin.config.groups.IsEdit;
import org.example.yaopin.entity.Goods;
import org.example.yaopin.entity.Purchases;
import org.example.yaopin.entity.Storages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 入库表单
 */
@Getter
@Setter
public class StoragesForm {
    private String id;
    @NotBlank(message = "采购信息不能为空", groups = {IsAdd.class, IsEdit.class})
    private String purchasesId;
    private Goods goods;
    private Integer type;
}
