package org.example.gongjiao.form;

import lombok.Getter;
import lombok.Setter;
import org.example.gongjiao.config.groups.IsAdd;
import org.example.gongjiao.config.groups.IsEdit;
import org.example.gongjiao.entity.Stands;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class StandsForm {
    private String id;
    private LocalDateTime createTime;
    @NotBlank(message = "站点名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;

    public Stands toEntity() {
        Stands entity = new Stands();
        entity.setId(this.id);
        entity.setCreateTime(this.createTime);
        entity.setCreateTime(this.createTime);
        entity.setName(this.name);
        return entity;
    }
}
