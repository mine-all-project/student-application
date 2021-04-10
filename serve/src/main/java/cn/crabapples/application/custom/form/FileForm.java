package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO 
 *
 * @author Mr.He
 * 2021/4/9 1:49
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Getter
@Setter
public class FileForm extends BaseForm {
    @NotNull(message = "文件地址不能为空", groups = {IsAdd.class, IsEdit.class})
    private String url;
    private String des;
    private String type;
    @NotBlank(message = "关键字不能为空", groups = {IsAdd.class, IsEdit.class})
    private String keywords;

}