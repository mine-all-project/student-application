package org.example.application.custom.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewerForm {
    private String id;
    private Integer status;
    private String content;
}
