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
public class SearchForm {
    private String type;
    private String name;
}
