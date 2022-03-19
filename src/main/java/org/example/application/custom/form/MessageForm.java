package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.Message;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
@Setter
public class MessageForm extends BaseForm {
    @NotBlank(message = "名称不能为空", groups = IsNotNull.class)
    private String name;
    @NotNull(message = "金额不能为空", groups = IsNotNull.class)
    private Integer price;
    private String content;
    @NotNull(message = "商品类型不能为空", groups = IsNotNull.class)
    private Integer type;
    private SysUser publisher;
    private List<FileInfo> images;

    @Override
    public Message toEntity() {
        Message entity = new Message();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
