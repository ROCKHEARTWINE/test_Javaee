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
        //��ȡ��Ҫ���ʵ�ҵ�񷽷�
        String method = req.getParameter("method");
        //��ȡ������ֽ����ļ�
        Class<? extends BaseServlet> classZi = this.getClass();
        //���ݷ������Ͳ������ͻ�ȡ����
        try {
            Method m = classZi.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //����ִ��
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
