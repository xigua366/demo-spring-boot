package com.yx.demo.aop;

import com.yx.demo.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 统一日志打印切面
 * @author xi.yang
 * @version 1.0
 * @date 2020-10-31 14:11
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution (* com.yx.demo.controller..*.*(..))")
    public void logAop() {
    }

    @Around("logAop()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        MethodSignature signature = (MethodSignature) point.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        log.info("统一日志打印(start): {}.{}() ↓ ↓ ↓ ↓ ↓,请求参数:\n{}",
                declaringTypeName, methodName, argsToString(point.getArgs()));

        Object response = null;

        try {
            //执行该方法
            response = point.proceed();
        } finally {
            stopWatch.stop();

            log.info("统一日志打印(end): {}.{}() ↑ ↑ ↑ ↑ ↑,响应时间:{}毫秒,响应内容:\n{}",
                    declaringTypeName, methodName, stopWatch.getTotalTimeMillis(), argsToString(response));
        }

        return response;
    }

    private String argsToString(Object object) {
        try {
            return JsonUtils.object2Json(object);
        } catch (Exception e) {
            log.error("LogAspect转换参数异常：", e);
        }
        return String.valueOf(object);
    }
}