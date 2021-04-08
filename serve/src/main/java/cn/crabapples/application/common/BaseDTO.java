package cn.crabapples.application.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 返回结果基本DTO
 *
 * @author Mr.He
 * 2019/9/21 17:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public abstract class BaseDTO {
    private String userId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
