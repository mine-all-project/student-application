package cn.crabapples.application.custom.form;

import cn.crabapples.application.common.BaseForm;
import cn.crabapples.application.common.groups.IsAdd;
import cn.crabapples.application.common.groups.IsEdit;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

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
public class TagsForm extends BaseForm {
    @NotNull(message = "名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;
    private String color;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
