package com.yx.demo.exception;

import com.yx.demo.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public JsonData handle(Exception e) {

        logger.error("[ 系统异常 ]", e);

        if(e instanceof MyException) {
            MyException myException = (MyException) e;
            return JsonData.buildError(myException.getCode(), myException.getMsg());
        }else {
            return JsonData.buildError("全局异常，未知错误");
        }

    }

}
