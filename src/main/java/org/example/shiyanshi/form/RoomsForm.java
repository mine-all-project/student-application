package org.example.shiyanshi.form;

import lombok.Getter;
import lombok.Setter;
import org.example.shiyanshi.config.groups.*;
import org.example.shiyanshi.entity.Machines;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class RoomsForm {
    private String id;
    @NotBlank(message = "名称不能为空", groups = {IsNotNull.class})
    private String name;
    private List<Machines> machines;
}
