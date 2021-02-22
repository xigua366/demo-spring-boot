package com.yx.demo.exception;

/**
 * 自定义业务异常
 * @author yangxi
 * @version 1.0
 */
public class MyException extends RuntimeException {

    private int code = -1;

    private String msg;

    public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException(BizCodeEnum bizCodeEnum) {
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;

    }
}