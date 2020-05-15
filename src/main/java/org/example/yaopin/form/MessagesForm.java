package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MessagesForm {
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getToAs() {
        return toAs;
    }

    public void setToAs(String toAs) {
        this.toAs = toAs;
    }

    @NotNull(message = "消息类型不能为空", groups = {IsAdd.class, IsEdit.class})
    private Integer type;
    @NotBlank(message = "数据Id不能为空", groups = {IsAdd.class, IsEdit.class})
    private String objectId;
//    @NotBlank(message = "发送方不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String fromAs;
    @NotBlank(message = "接收方不能为空", groups = {IsAdd.class, IsEdit.class})
    private String toAs;
}
