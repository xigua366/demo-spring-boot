package com.yx.demo.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyTwoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义two servlet get 方法。。。");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter(); writer.write("自定义two servlet get 方法。。。"); writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义two servlet post 方法。。。");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter(); writer.write("自定义two servlet post 方法。。。"); writer.flush();
        writer.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义two servlet put 方法。。。");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter(); writer.write("自定义two servlet put 方法。。。"); writer.flush();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("自定义two servlet delete 方法。。。");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter(); writer.write("自定义two servlet delete 方法。。。"); writer.flush();
        writer.close();
    }
}
