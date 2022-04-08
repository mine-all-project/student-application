package org.example.application.system.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.application.common.DIC;
import org.example.application.common.ResponseCode;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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

    private ResponseDTO(ResponseCode status, String message) {
        this.status = status.getCode();
        this.message = message;
        this.time = System.currentTimeMillis();
    }

    private ResponseDTO(int code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    /*---------------- SUCCESS -------------*/
    public static <T> ResponseDTO returnSuccess(Collection<T> data) {
        return new ResponseDTO(ResponseCode.SUCCESS, DIC.BASE_SUCCESS_MESSAGE,data);
    }

    public static ResponseDTO returnSuccess(String message, Object data) {
        return new ResponseDTO(ResponseCode.SUCCESS, message, data);
    }

    public static ResponseDTO returnSuccess(Object data) {
        return returnSuccess(DIC.BASE_SUCCESS_MESSAGE, data);
    }

    public static ResponseDTO returnSuccess() {
        return returnSuccess(DIC.BASE_SUCCESS_MESSAGE, null);
    }


    /*---------------- ERROR -----------------*/
    public static ResponseDTO returnError(String message) {
        return new ResponseDTO(ResponseCode.ERROR, message);
    }

    /*---------------- AUTH_FAIL -------------*/
    public static ResponseDTO returnAuthFail(String message) {
        return new ResponseDTO(ResponseCode.AUTH_FAIL, message);
    }

    /*---------------- CUSTOM ----------------*/
    public static ResponseDTO returnCustom(int status, String message, Object data) {
        return new ResponseDTO(status, message, data);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
