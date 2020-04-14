package cn.crabapples.tuole.config;

/**
 * TODO 应用异常类
 *
 *
 * 2019/9/21 20:54
 *
 *
 * pc-name 29404
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
