package org.example.fangwuzulin.form;

import lombok.Data;
import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.entity.LeaveMessage;

import javax.validation.constraints.NotBlank;

@Data
public class LeaveMessageForm {
    @NotBlank(message = "房屋Id不能为空", groups = {IsNotNull.class})
    private String houses_id;
    @NotBlank(message = "内容不能为空", groups = {IsNotNull.class})
    private String content;
    private String parent_id;

    public LeaveMessage toEntity() {
        LeaveMessage entity = new LeaveMessage();
        entity.setHouses_id(this.houses_id);
        entity.setContent(this.content);
        entity.setParent_id(this.parent_id);
        return entity;
    }
}
