package cn.crabapples.application.system.dto;

import cn.crabapples.application.custom.entity.Tags;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TODO 分页DTO
 *
 * @author Mr.He
 * 8/29/20 11:47 PM
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name root
 */
@Getter
@Setter
public class SysUserDTO {
    private String id;
    private String username;
    private String name;
    private List<Tags> tags;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
