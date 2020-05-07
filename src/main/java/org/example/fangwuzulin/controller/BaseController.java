package org.example.fangwuzulin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.example.fangwuzulin.config.ApplicationException;
import org.example.fangwuzulin.dto.RandomCode;
import org.example.fangwuzulin.dto.ResponseDTO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

public abstract class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private Validator validator;
    @Autowired
    private Producer producer;

    /**
     * 属性校验的方法
     *
     * @param object 需要验证的对象
     */
    protected final void validator(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        for (ConstraintViolation constraintViolation : constraintViolations) {
            throw new ApplicationException(constraintViolation.getMessageTemplate());
        }
    }

    @ExceptionHandler
    @ResponseBody
    protected ResponseDTO applicationExceptionHandler(Exception e) {
        logger.error("ajax出现异常:[{}]\n", e.getMessage(), e);
        if (e instanceof UnauthenticatedException) {
            return ResponseDTO.returnAuthFail("未获授权,请先登陆后重试,5秒后自动跳转");
        }
        return ResponseDTO.returnError(e.getMessage());
    }

    protected RandomCode createRandomCode() {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        return new RandomCode(code, image);
    }

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String code = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(code);
        logger.info("captcha.jpg---------------->text--------->" + code);
        //保存到shiro session
        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, code);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
