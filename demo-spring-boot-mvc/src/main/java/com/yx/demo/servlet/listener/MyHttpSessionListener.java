package com.yx.demo.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSessionListener要起作用，得那种传统的前后端不分离基于类似JSP模板开发的系统才行，前后端分离的系统，前端通过restful接口请求后台，都是无状态的，没有seesion这个概念
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSessionListener 初始化了。。。");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSessionListener 销毁了。。。");
    }
}
