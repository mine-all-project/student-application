package org.example.application.system.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.example.application.common.ApplicationException;
import org.example.application.common.BaseForm;
import org.example.application.common.Groups;
import org.example.application.system.entity.FileInfo;
import org.example.application.system.entity.SystemUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserInfoForm extends BaseForm<SystemUser> {
    @NotBlank(message = "id不能为空", groups = Groups.IsNotNull.class)
    private String id;

    @NotBlank(message = "密码不能为空", groups = {Groups.IsNotNull.class})
    private String name;

    @NotNull(message = "性别不能为空", groups = {Groups.IsNotNull.class})
    private Integer gender;

    @NotNull(message = "年龄不能为空", groups = {Groups.IsNotNull.class})
    private Integer age;

    @NotBlank(message = "邮箱不能为空", groups = {Groups.IsNotNull.class})
    private String mail;

    private FileInfo headImg;

    @Override
    public SystemUser toEntity() {
        throw new ApplicationException("暂无转换方法");
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
