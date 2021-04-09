package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 5:07
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Getter
@Setter
public class DiscussForm extends BaseForm {
    private String subjectId;
    private String parentId;
    private String content;
}
