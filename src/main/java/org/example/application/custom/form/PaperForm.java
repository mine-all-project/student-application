package org.example.application.custom.form;

import lombok.Getter;
import lombok.Setter;
import org.example.application.common.BaseEntity;
import org.example.application.common.BaseForm;

@Getter
@Setter
public class PaperForm extends BaseForm {


    @Override
    public BaseEntity toEntity() {
        return null;
    }
}
