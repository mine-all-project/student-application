package org.example.waimai.form;

import lombok.Data;
import org.example.waimai.config.groups.IsNotNull;
import org.example.waimai.entity.LeaveMessage;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class LeaveMessageForm {
    @NotBlank(message = "房屋Id不能为空", groups = {IsNotNull.class})
    private String houses_id;
    @NotBlank(message = "内容不能为空", groups = {IsNotNull.class})
    private String content;
    private String parent_id;

    public LeaveMessage toEntity() {
        LeaveMessage entity = new LeaveMessage();
        entity.setId(UUID.randomUUID().toString());
        entity.setHouses_id(this.houses_id);
        entity.setContent(this.content);
        entity.setParent_id(this.parent_id);
        return entity;
    }
}
