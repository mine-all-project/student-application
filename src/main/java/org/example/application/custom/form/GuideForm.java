package org.example.application.custom.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.application.system.entity.SystemUser;

@Getter
@Setter
@ToString
public class GuideForm {
    private String id;
    private Integer status;
    private String content;
    private SystemUser reviewer;

}
