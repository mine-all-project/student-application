package cn.crabapples.application.system.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 请求DTO
 *

 * 8/30/20 10:59 AM



 */
@Getter
@Setter
public class RequestDTO extends PageDTO {
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
