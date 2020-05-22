package org.example.shiyanshi.form;

import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.groups.IsNotNull;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MachinesForm {
    private String id;
    private Integer time;
    @NotBlank(message = "名称不能为空", groups = {IsNotNull.class})
    private String name;
    private String content;
}
