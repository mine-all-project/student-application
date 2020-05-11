package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class PapersForm {
//    private String id;
//    private LocalDateTime createTime;
//    @NotBlank(message = "标题不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String title;
//    @NotBlank(message = "标识不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String keyWords;
//    @NotBlank(message = "正文不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String content;
//
//    public Papers toEntity() {
//        Papers entity = new Papers();
//        entity.setId(this.id);
//        entity.setCreateTime(this.createTime);
//        entity.setTitle(this.title);
//        entity.setContent(this.content);
//        entity.setKeyWords(this.keyWords);
//        return entity;
//    }
}
