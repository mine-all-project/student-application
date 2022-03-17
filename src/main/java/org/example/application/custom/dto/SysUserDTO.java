package org.example.application.custom.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 分页DTO
 *

 * 8/29/20 11:47 PM



 */
@Getter
@Setter
public class SysUserDTO {
    private String id;
    private String username;
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
