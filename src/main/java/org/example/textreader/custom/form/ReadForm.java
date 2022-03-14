package org.example.textreader.custom.form;

import lombok.Data;
import org.example.textreader.common.groups.IsEdit;
import org.example.textreader.common.groups.IsNotNull;
import org.example.textreader.common.groups.IsStatusChange;
import org.example.textreader.custom.entity.Paper;
import org.example.textreader.custom.entity.ReadInfo;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class ReadForm {
    private String id;
    @NotBlank(message = "阅读元数据信息不能为空", groups = {IsStatusChange.class, IsEdit.class})
    private Paper paper;
    @NotBlank(message = "音色不能为空", groups = {IsNotNull.class})
    private String voice;
    private Integer pitchRate;
    private Integer speechRate;

    public ReadInfo toEntity() {
        final ReadInfo readInfo = new ReadInfo();
        BeanUtils.copyProperties(this, readInfo);
        return readInfo;
    }
}
