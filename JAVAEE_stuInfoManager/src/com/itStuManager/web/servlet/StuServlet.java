package com.itStuManager.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itStuManager.domain.PageBean;
import com.itStuManager.domain.Student;
import com.itStuManager.service.UserService;
import com.itStuManager.service.impl.UserServiceImpl;
import com.itStuManager.utils.BaseServlet;
import com.itStuManager.utils.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
@MultipartConfig
@WebServlet("/stuServlet")
public class StuServlet extends BaseServlet {
    UserService service = new UserServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取表单中的所有数据
        Map<String, String[]> map = req.getParameterMap();

//        map.get()//通过map获取hobby
        //创建对象
        Student student = new Student();
        
        try {
            //使用beanUtils将map中的数据封装到student对象中
            BeanUtils.populate(student,map);
            
            //接收爱好包含所有复选框数据，封装进学生对象中
            String[] hobbys = req.getParameterValues("hobby");
            student.setHobby(Arrays.toString(hobbys));

            //调用上传工具完成头像上传
            String filename = UploadUtils.upload(req);
            student.setPhoto(filename);
        } catch (IllegalAccessException | InvocationTargetException | IOException | ServletException e) {
            e.printStackTrace();
        }
        //调用业务
        int row = service.addUser(student);
        //响应处理业务
        if (row>0){
            //添加成功
//            System.out.println("添加成功");
            //重定向分页查询
            resp.sendRedirect(req.getContextPath()+"/stuServlet?method=findPages");
        }else{
            throw new RuntimeException("添加学生失败");
        }

//        System.out.println("添加学生业务");
    }

    public void findPages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受请求参数
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");


        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "3";
        }
        //2、调用业务
        PageBean pb= service.findUserByPage(currentPage,rows,name,sex);
        //3、响应处理结果
        req.setAttribute("pb",pb);
        //转发到list.jsp
        req.getRequestDispatcher("/jsp/list.jsp").forward(req,resp);
    }

    public void findPages1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受请求参数
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");

        //2、调用业务
        PageBean pb= service.findUserByPage(currentPage,rows,name,sex);
        //3、将pb转成json对象
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(pb);
        resp.getWriter().print(s);
    }

    public void delStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取ids
        String ids = req.getParameter("ids");
        //调用业务
        int row =service.delAll(ids);
        //响应处理结果
        if(row>0){
            //重定向到分页查询
            resp.sendRedirect(req.getContextPath()+"/stuServlet?method=findPages");
        }else{
            throw new RuntimeException("批量删除学生失败");
        }
    }

    public void findStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取sid
        String sid = req.getParameter("sid");
        //调用业务
        Student student=  service.findStudent(sid);
        //放入域对象
        req.setAttribute("stu",student);
        //转发到edit.jsp
        req.getRequestDispatcher("/jsp/edit.jsp").forward(req,resp);

    }
    public void updateStu(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {
        //接受表单数据到map中
        Map<String, String[]> map = req.getParameterMap();
        //创建Student对象
        Student stu = new Student();
        //将map中的数据封装到stu对象
        BeanUtils.populate(stu,map);
        String[] hobbies = req.getParameterValues("hobby");
        stu.setHobby(Arrays.toString(hobbies));
        //获取文件资源
        Part part = req.getPart("fileName");
        //获取文件名
        String fileName = part.getSubmittedFileName();

        if("".equals(fileName) || fileName==null){
            //没有上传新头像
            //获取原始头像名称
            String oldPhoto = req.getParameter("oldphoto");
            //设置头像名称
            stu.setPhoto(oldPhoto);
        }else{
            //上传了新头像
            fileName= UploadUtils.upload(req);
            //设置新的文件名
            stu.setPhoto(fileName);
        }
        //调用业务
        int row =service.updateStudent(stu);
        //响应处理结果
        if (row > 0) {
            //更新成功
            resp.sendRedirect(req.getContextPath() + "/stuServlet?method=findPages");
        } else {
            throw new RuntimeException("更新学生失败");
        }

    }

}
