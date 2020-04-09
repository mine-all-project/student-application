package cn.crabapples.tuole.controller;

import cn.crabapples.tuole.config.ApplicationException;
import cn.crabapples.tuole.dto.ResponseDTO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * TODO 基础Controller，其他controller请继承此类
 *
 * @author Mr.He
 * 2019/9/21 18:28
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public abstract class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private Validator validator;

    /**
     * 属性校验的方法
     * @param object 需要验证的对象
     */
    protected final void validator(Object object, Class<?>... groups){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object,groups);
        for (ConstraintViolation constraintViolation : constraintViolations ) {
           throw new ApplicationException(constraintViolation.getMessageTemplate());
        }
    }

    @ExceptionHandler
    @ResponseBody
    protected ResponseDTO applicationExceptionHandler(Exception e){
        logger.error("ajax出现异常:[{}]\n",e.getMessage(),e);
        if(e instanceof UnauthenticatedException){
            return ResponseDTO.returnAuthFail("未获授权,请先登陆后重试,5秒后自动跳转");
        }
        return ResponseDTO.returnError(e.getMessage());
    }
}
