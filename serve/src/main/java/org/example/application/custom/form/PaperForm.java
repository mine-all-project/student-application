//package org.example.application.custom.form;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.example.application.common.BaseForm;
//import org.example.application.common.Groups;
//import org.example.application.custom.entity.Paper;
//import org.example.application.system.entity.FileInfo;
//import org.springframework.beans.BeanUtils;
//
//import javax.persistence.OneToOne;
//import javax.validation.constraints.NotBlank;
//
//@Getter
//@Setter
//public class PaperForm extends BaseForm<Paper> {
//    @NotBlank(message = "标题不能为空", groups = {Groups.IsNotNull.class})
//    private String title;
//    @NotBlank(message = "正文不能为空", groups = {Groups.IsNotNull.class})
//    private String content;
//    private String type;
//    @OneToOne
//    private FileInfo image;
//
//    @Override
//    public Paper toEntity() {
//        Paper entity = new Paper();
//        BeanUtils.copyProperties(this, entity);
//        return entity;
//    }
//}
