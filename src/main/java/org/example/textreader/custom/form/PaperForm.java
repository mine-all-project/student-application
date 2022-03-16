package org.example.textreader.custom.form;

import lombok.Data;
import org.example.textreader.common.groups.*;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.system.entity.FileInfo;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class PaperForm {
    @NotBlank(message = "id不能为空", groups = {IsStatusChange.class, IsEdit.class})
    @Null(message = "id必须为空", groups = IsAdd.class)
    private String id;

    @NotBlank(message = "标题不能为空", groups = {IsNotNull.class, IsLogin.class})
    private String title;

    private String content;
    private String author;
    private FileInfo fileInfo;


    public Paper toEntity() {
        Paper paper = new Paper();
        BeanUtils.copyProperties(this, paper);
        return paper;
    }
}
