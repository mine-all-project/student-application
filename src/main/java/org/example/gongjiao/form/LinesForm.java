package org.example.gongjiao.form;

import lombok.Data;
import org.example.gongjiao.config.groups.*;
import org.example.gongjiao.entity.Linees;
import org.example.gongjiao.entity.Stands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LinesForm {
    private String id;

    @NotBlank(message = "线路不能为空", groups = {IsAdd.class, IsEdit.class})
    private String number;

    @NotNull(message = "首班时间", groups = {IsAdd.class, IsEdit.class})
    private LocalDateTime startTime;

    @NotNull(message = "末班时间", groups = {IsAdd.class, IsEdit.class})
    private LocalDateTime endTime;

    @NotEmpty(message = "站点不能为空", groups = {IsAdd.class, IsEdit.class})
    private List<Stands> standsList;

    @NotEmpty(message = "间隔时间不能为空", groups = {IsAdd.class, IsEdit.class})
    private Integer offset;

    public Linees toEntity() {
        Linees lines = new Linees();
        lines.setId(this.id);
        lines.setNumber(this.number);
        lines.setStartTime(this.startTime);
        lines.setEndTime(this.endTime);
        lines.setOffset(this.offset);
        lines.setStandsList(this.standsList);
        return lines;
    }
}
