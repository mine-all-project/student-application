package cn.crabapples.application.common;

import cn.crabapples.application.system.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO 全局异常处理
 *
 * @author Mr.He
 * 2021/4/9 1:51
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Component
@ControllerAdvice
public class CustomExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler
    protected ResponseDTO applicationExceptionHandler(Exception e) {
        logger.warn("XHR出现异常:[{}]", e.getMessage(), e);
        if (e instanceof HttpMessageNotReadableException) {
            return ResponseDTO.returnError("参数错误");
        }
        if (e instanceof ApplicationException) {
            if (401 == ((ApplicationException) e).getCode()) {
                return ResponseDTO.returnAuthFail("身份认证失败");
            }
        }
        return ResponseDTO.returnError(e.getMessage());
    }
}
