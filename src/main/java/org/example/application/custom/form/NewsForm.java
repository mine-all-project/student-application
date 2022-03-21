package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Message;
import org.example.application.custom.entity.News;
import org.example.application.custom.entity.Other;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class NewsForm extends BaseForm {
    @NotBlank(message = "标题不能为空", groups = IsNotNull.class)
    private String title;
    private String content;
    @NotNull(message = "类型不能为空", groups = IsNotNull.class)
    private Integer type;
    private Integer status;
    private String note;
    private SysUser publisher;
    private List<FileInfo> images;
    private List<Message> messages;

    @Override
    public News toEntity() {
        News entity = new News();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
