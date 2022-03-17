package org.example.application.custom.dto;

import org.example.application.common.ResponseCode;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 通用返回值DTO
 *

 * 2019/9/21 17:45



 */
@Getter
@Setter
public class ResponseDTO {
    private int status;
    private String message;
    private Object data;
    private long time;

    private ResponseDTO(ResponseCode status, String message, Object data) {
        this.status = status.getCode();
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    private ResponseDTO(int code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public static ResponseDTO returnSuccess(String message, Object data) {
        return new ResponseDTO(ResponseCode.SUCCESS, message, data);
    }

    public static ResponseDTO returnSuccess(String message) {
        return returnSuccess(message, null);

    }

    public static ResponseDTO returnError(String message, Object data) {
        return new ResponseDTO(ResponseCode.ERROR, message, data);
    }

    public static ResponseDTO returnError(String message) {
        return returnError(message, null);
    }

    public static ResponseDTO returnAuthFail(String message, Object data) {
        return new ResponseDTO(ResponseCode.AUTH_FAIL, message, data);
    }

    public static ResponseDTO returnAuthFail(String message) {
        return returnAuthFail(message, null);
    }

    public static ResponseDTO returnCustom(int status, String message, Object data) {
        return new ResponseDTO(status, message, data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
