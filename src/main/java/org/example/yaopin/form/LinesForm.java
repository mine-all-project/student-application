package org.example.yaopin.form;

import lombok.Getter;
import lombok.Setter;
import org.example.yaopin.config.groups.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class LinesForm  {
//    private String id;
//    private LocalDateTime createTime;
//    @NotBlank(message = "线路不能为空", groups = {IsAdd.class, IsEdit.class})
//    private String number;
//    @NotNull(message = "首班时间", groups = {IsAdd.class, IsEdit.class})
//    private LocalDateTime startTime;
//    @NotNull(message = "末班时间", groups = {IsAdd.class, IsEdit.class})
//    private LocalDateTime endTime;
//    @NotEmpty(message = "站点不能为空", groups = {IsAdd.class, IsEdit.class})
//    private List<Stands> standsList;
//    @NotEmpty(message = "间隔时间不能为空", groups = {IsAdd.class, IsEdit.class})
//    private Integer offset;
//
//    public Linees toEntity() {
//        Linees entity = new Linees();
//        entity.setId(this.id);
//        entity.setCreateTime(this.createTime);
//        entity.setNumber(this.number);
//        entity.setStartTime(this.startTime);
//        entity.setEndTime(this.endTime);
//        entity.setOffset(this.offset);
//        entity.setStandsList(this.standsList);
//        return entity;
//    }
}
