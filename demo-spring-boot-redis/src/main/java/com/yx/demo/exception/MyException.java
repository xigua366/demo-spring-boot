package com.yx.demo.exception;

import com.yx.demo.enums.BizCodeEnum;

/**
 * 自定义业务异常
 * @author yangxi
 * @version 1.0
 */
public class MyException extends RuntimeException {

    private Integer code;

    private String msg;

    public MyException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }

    public MyException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }
}