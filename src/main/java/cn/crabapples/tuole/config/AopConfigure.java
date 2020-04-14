package cn.crabapples.tuole.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * TODO service切面拦截
 *
 *
 * 2020/1/30 18:07
 *
 *
 *
 */
@Aspect
@Configuration
@Order(50)
public class AopConfigure {
    private static final Logger logger = LoggerFactory.getLogger(AopConfigure.class);
    private static final String CONTROLLER_AOP = "execution(* cn.crabapples.spring.system.controller.*.*(..))";

    @Pointcut(CONTROLLER_AOP)
    public void controllerAop() {
    }

    @Around("controllerAop()")
    public Object controllerAop(ProceedingJoinPoint join) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) join.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("切面拦截成功,拦截方法:[{}],参数:[{}]", method.getName(), join.getArgs());
        Object result = join.proceed();
        logger.info("切面拦截结束,返回值:[{}]", result);
        return result;
    }

}
