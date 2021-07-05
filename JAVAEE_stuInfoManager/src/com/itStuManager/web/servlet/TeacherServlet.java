package com.itStuManager.web.servlet;

import com.itStuManager.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacherServlet")
public class TeacherServlet extends BaseServlet {
    public void add(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("添加老师业务");
    }
    public void update(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("修改老师业务");
    }
    public void find(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("查询老师业务");
    }
}
