package cn.crabapples.system.form;

import cn.crabapples.common.BaseForm;
import cn.crabapples.common.groups.IsAdd;
import cn.crabapples.common.groups.IsEdit;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
