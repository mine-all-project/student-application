package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.LostItems;
import org.example.application.custom.entity.Message;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class LostItemsForm extends BaseForm {
    @NotBlank(message = "标题不能为空", groups = IsNotNull.class)
    private String title;
    private Integer price;
    private String content;
    @NotNull(message = "类型不能为空", groups = IsNotNull.class)
    private Integer type;
    private SysUser publisher;
    private List<FileInfo> images;
    private List<Message> messages;

    @Override
    public LostItems toEntity() {
        LostItems entity = new LostItems();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
