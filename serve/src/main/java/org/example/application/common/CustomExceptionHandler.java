package org.example.application.common;

import org.example.application.system.dto.ResponseDTO;
import org.example.application.common.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
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
        if (e instanceof org.example.application.common.ApplicationException) {
            if (401 == ((ApplicationException) e).getCode()) {
                return ResponseDTO.returnAuthFail("身份认证失败");
            }
        }
        return ResponseDTO.returnError(e.getMessage());
    }
}
