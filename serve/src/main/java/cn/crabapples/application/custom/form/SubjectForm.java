package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import cn.crabapples.application.custom.entity.Subject;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SubjectForm extends BaseForm {
    @NotBlank(message = "项目名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String title;
    private List<SysUser> personList;
    private List<Subject.Step> stepList;
    private List<String> tagList;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
