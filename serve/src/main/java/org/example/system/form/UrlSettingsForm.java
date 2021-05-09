package org.example.system.form;

import lombok.Data;
import org.example.common.groups.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UrlSettingsForm {
    private String id;
    private String url;
    private String keyWords;
}
