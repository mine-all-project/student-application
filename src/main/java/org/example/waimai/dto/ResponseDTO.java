package org.example.waimai.dto;

//@Getter
//@Setter
public class ResponseDTO {

    enum Status {
        SUCCESS(200),
        AUTH_FAIL(401),
        ERROR(500);
        int code;

        Status(int code) {
            this.code = code;
        }
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public static int getAuthFail() {
        return AUTH_FAIL;
    }

    public static int getERROR() {
        return ERROR;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private static final int SUCCESS = 200;
    private static final int AUTH_FAIL = 401;
    private static final int ERROR = 500;
    private int status;
    private String message;
    private Object data;
    private long time;

    private ResponseDTO(Status status, String message, Object data) {
        this.status = status.code;
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
        return new ResponseDTO(Status.SUCCESS, message, data);
    }

    public static ResponseDTO returnSuccess(String message) {
        return new ResponseDTO(Status.SUCCESS, message, null);
    }

    public static ResponseDTO returnError(String message) {
        return new ResponseDTO(Status.ERROR, message, null);
    }

    public static ResponseDTO returnError(String message, Object data) {
        return new ResponseDTO(Status.ERROR, message, data);
    }

    public static ResponseDTO returnAuthFail(String message) {
        return new ResponseDTO(Status.AUTH_FAIL, message, null);
    }

    public static ResponseDTO returnAuthFail(String message, Object data) {
        return new ResponseDTO(Status.AUTH_FAIL, message, data);
    }

    public static ResponseDTO returnCustom(int status, String message, Object data) {
        return new ResponseDTO(status, message, data);
    }

    public boolean isSuccess() {
        return 200 == this.status;
    }
}
