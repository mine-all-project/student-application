package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * 评论表单
 */
@Getter
@Setter
public class DiscussForm extends BaseForm {
    private String subjectId;
    private String parentId;
    private String content;
}
