package org.example.fangwuzulin.form;

import lombok.Data;
import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.entity.Contracts;

import javax.validation.constraints.NotBlank;

@Data
public class ContractForm {
    private String id;

    @NotBlank(message = "标题不能为空", groups = {IsNotNull.class})
    private String title;

    @NotBlank(message = "内容不能为空", groups = {IsNotNull.class})
    private String content;

    public Contracts toEntity() {
        Contracts entity = new Contracts();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        return entity;
    }
}