package com.itStuManager.utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取你要访问的业务方法
        String method = req.getParameter("method");
        //获取子类的字节码文件
        Class<? extends BaseServlet> classZi = this.getClass();
        //根据方法名和参数类型获取方法
        try {
            Method m = classZi.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //方法执行
            m.invoke(classZi.newInstance(),req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(classZi);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
