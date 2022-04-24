package org.example.application.custom.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseForm;
import org.example.application.custom.entity.Message;
import org.example.application.custom.entity.Paper;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class PaperForm extends BaseForm<Paper> {
    private String title;
    private String content;
    private Integer status;
    private String type;
    private String secondType;
    private SysUser publisher;
    private String note;
    private List<FileInfo> files;
    private List<Message> messages;

    @Override
    public Paper toEntity() {
        Paper entity = new Paper();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
