package org.example.gongjiao.form;

import lombok.Data;
import org.example.gongjiao.config.groups.IsAdd;
import org.example.gongjiao.config.groups.IsEdit;
import org.example.gongjiao.entity.Linees;
import org.example.gongjiao.entity.Stands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StandsForm {
    private String id;

    @NotBlank(message = "站点名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;

    public Stands toEntity() {
        Stands stands = new Stands();
        stands.setId(this.id);
        stands.setName(this.name);
        return stands;
    }
}
