package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Subject$Step$ResultInfoForm extends BaseForm {
    @NotBlank(message = "阶段id不能为空", groups = {IsAdd.class})
    private String stepId;
    @NotBlank(message = "成果url不能为空", groups = {IsAdd.class, IsEdit.class})
    private String url;
    @NotBlank(message = "文件名不能为空", groups = {IsAdd.class, IsEdit.class})
    private String fileName;
    @NotBlank(message = "成果描述不能为空", groups = {IsAdd.class, IsEdit.class})
    private String content;
    private boolean isEdit;
    private boolean isAdd;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
