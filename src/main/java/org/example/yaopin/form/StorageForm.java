//package org.example.yaopin.form;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.example.yaopin.config.groups.IsAdd;
//import org.example.yaopin.config.groups.IsEdit;
//import org.example.yaopin.entity.Goods;
//
//import javax.validation.constraints.NotBlank;
//
///**
// * 入库表单
// */
//@Getter
//@Setter
//public class StorageForm {
//    private String id;
//    @NotBlank(message = "采购信息不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String purchasesId;
//    private Goods goods;
//    private Integer type;
//}
