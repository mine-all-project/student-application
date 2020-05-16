package org.example.fangwuzulin.form;

import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.entity.LeaveMessage;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class LeaveMessageForm {

    @NotBlank(message = "房屋Id不能为空", groups = {IsNotNull.class})
    private String houses_id;
    @NotBlank(message = "内容不能为空", groups = {IsNotNull.class})
    private String content;
    private String parent_id;

    public LeaveMessageForm() {
    }

    public LeaveMessage toEntity() {
        LeaveMessage entity = new LeaveMessage();
        entity.setId(UUID.randomUUID().toString());
        entity.setHouses_id(this.houses_id);
        entity.setContent(this.content);
        entity.setParent_id(this.parent_id);
        return entity;
    }

    public @NotBlank(message = "房屋Id不能为空", groups = {IsNotNull.class}) String getHouses_id() {
        return this.houses_id;
    }

    public @NotBlank(message = "内容不能为空", groups = {IsNotNull.class}) String getContent() {
        return this.content;
    }

    public String getParent_id() {
        return this.parent_id;
    }

    public void setHouses_id(@NotBlank(message = "房屋Id不能为空", groups = {IsNotNull.class}) String houses_id) {
        this.houses_id = houses_id;
    }

    public void setContent(@NotBlank(message = "内容不能为空", groups = {IsNotNull.class}) String content) {
        this.content = content;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LeaveMessageForm)) return false;
        final LeaveMessageForm other = (LeaveMessageForm) o;
        if (!other.canEqual(this)) return false;
        final Object this$houses_id = this.getHouses_id();
        final Object other$houses_id = other.getHouses_id();
        if (this$houses_id == null ? other$houses_id != null : !this$houses_id.equals(other$houses_id)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$parent_id = this.getParent_id();
        final Object other$parent_id = other.getParent_id();
        return this$parent_id == null ? other$parent_id == null : this$parent_id.equals(other$parent_id);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LeaveMessageForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $houses_id = this.getHouses_id();
        result = result * PRIME + ($houses_id == null ? 43 : $houses_id.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $parent_id = this.getParent_id();
        result = result * PRIME + ($parent_id == null ? 43 : $parent_id.hashCode());
        return result;
    }

    public String toString() {
        return "LeaveMessageForm(houses_id=" + this.getHouses_id() + ", content=" + this.getContent() + ", parent_id=" + this.getParent_id() + ")";
    }
}
