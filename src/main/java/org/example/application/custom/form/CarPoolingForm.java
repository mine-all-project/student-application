package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.common.groups.IsNotNull;
import org.example.application.custom.entity.CarPooling;
import org.example.application.custom.entity.Message;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CarPoolingForm extends BaseForm {
    @NotBlank(message = "标题不能为空", groups = IsNotNull.class)
    private String title;
    @NotNull(message = "人数不能为空", groups = IsNotNull.class)
    private Integer personCount;
    @NotBlank(message = "出发地不能为空", groups = IsNotNull.class)
    private String fromAddress;
    @NotBlank(message = "目的地不能为空", groups = IsNotNull.class)
    private String targetAddress;
    private String content;
    @NotNull(message = "出发时间不能为空", groups = IsNotNull.class)
    private LocalDateTime beginTime;
    private Integer status;
    private String note;
    private SysUser publisher;
    private List<FileInfo> images;
    private List<Message> messages;

    @Override
    public CarPooling toEntity() {
        CarPooling entity = new CarPooling();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
