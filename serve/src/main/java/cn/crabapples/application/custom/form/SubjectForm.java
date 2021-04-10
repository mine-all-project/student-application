package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import cn.crabapples.application.custom.entity.SubjectStep;
import cn.crabapples.application.system.entity.SysUser;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
public class SubjectForm extends BaseForm {
    @NotBlank(message = "项目名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String title;
    private List<String> personList;
    private List<SubjectStep> stepList;
    private List<String> tagsList;
    private SysUser createBy;
    private String userId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}