package org.example.fangwuzulin.form;

import org.example.fangwuzulin.config.groups.IsNotNull;
import org.example.fangwuzulin.entity.Contracts;

import javax.validation.constraints.NotBlank;

public class ContractForm {
    private String id;

    @NotBlank(message = "标题不能为空", groups = {IsNotNull.class})
    private String title;

    @NotBlank(message = "内容不能为空", groups = {IsNotNull.class})
    private String content;

    public ContractForm() {
    }

    public Contracts toEntity() {
        Contracts entity = new Contracts();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        return entity;
    }

    public String getId() {
        return this.id;
    }

    public @NotBlank(message = "标题不能为空", groups = {IsNotNull.class}) String getTitle() {
        return this.title;
    }

    public @NotBlank(message = "内容不能为空", groups = {IsNotNull.class}) String getContent() {
        return this.content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(@NotBlank(message = "标题不能为空", groups = {IsNotNull.class}) String title) {
        this.title = title;
    }

    public void setContent(@NotBlank(message = "内容不能为空", groups = {IsNotNull.class}) String content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ContractForm)) return false;
        final ContractForm other = (ContractForm) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        return this$content == null ? other$content == null : this$content.equals(other$content);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractForm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "ContractForm(id=" + this.getId() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ")";
    }
}
