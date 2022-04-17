package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.custom.entity.LostItems;
import org.example.application.custom.entity.Message;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class LostItemsForm extends BaseForm<LostItems> {
    private String title;
    private String content;
    private Integer status;
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
