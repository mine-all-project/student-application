package org.example.shiyanshi.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.shiyanshi.config.groups.IsAdd;
import org.example.shiyanshi.config.groups.IsEdit;
import org.example.shiyanshi.entity.Machines;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 入库表单
 */
@Getter
@Setter
public class LineUpsForm {
    private String id;
    @NotEmpty(message = "设备信息不能为空", groups = {IsAdd.class})
    private String machinesId;
    @NotNull(message = "设备信息不能为空", groups = {IsAdd.class})
    private Machines machines;
    @NotNull(message = "开始时间不能为空", groups = {IsAdd.class})
    private LocalDateTime startTime;
    @NotNull(message = "结束时间不能未空", groups = {IsAdd.class})
    private LocalDateTime endTime;
}
