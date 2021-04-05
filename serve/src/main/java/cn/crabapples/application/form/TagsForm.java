package cn.crabapples.application.form;

import cn.crabapples.common.BaseForm;
import cn.crabapples.common.groups.IsAdd;
import cn.crabapples.common.groups.IsEdit;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TagsForm extends BaseForm {
    @NotNull(message = "名称不能为空", groups = {IsAdd.class, IsEdit.class})
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
