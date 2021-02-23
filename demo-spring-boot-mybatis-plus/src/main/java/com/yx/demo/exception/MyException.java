package com.yx.demo.exception;

/**
 * 自定义业务异常
 * @author yangxi
 * @version 1.0
 */
public class MyException extends RuntimeException {

    private Integer code;

    private String msg;

    public MyException(Integer code, String msg){
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