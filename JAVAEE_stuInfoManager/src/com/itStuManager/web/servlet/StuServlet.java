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
        //��ȡ���е���������
        Map<String, String[]> map = req.getParameterMap();

//        map.get()//ͨ��map��ȡhobby
        //��������
        Student student = new Student();
        
        try {
            //ʹ��beanUtils��map�е����ݷ�װ��student������
            BeanUtils.populate(student,map);
            
            //���հ��ð������и�ѡ�����ݣ���װ��ѧ��������
            String[] hobbys = req.getParameterValues("hobby");
            student.setHobby(Arrays.toString(hobbys));

            //�����ϴ��������ͷ���ϴ�
            String filename = UploadUtils.upload(req);
            student.setPhoto(filename);
        } catch (IllegalAccessException | InvocationTargetException | IOException | ServletException e) {
            e.printStackTrace();
        }
        //����ҵ��
        int row = service.addUser(student);
        //��Ӧ����ҵ��
        if (row>0){
            //��ӳɹ�
//            System.out.println("��ӳɹ�");
            //�ض����ҳ��ѯ
            resp.sendRedirect(req.getContextPath()+"/stuServlet?method=findPages");
        }else{
            throw new RuntimeException("���ѧ��ʧ��");
        }

//        System.out.println("���ѧ��ҵ��");
    }

    public void findPages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.�����������
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
        //2������ҵ��
        PageBean pb= service.findUserByPage(currentPage,rows,name,sex);
        //3����Ӧ������
        req.setAttribute("pb",pb);
        //ת����list.jsp
        req.getRequestDispatcher("/jsp/list.jsp").forward(req,resp);
    }

    public void findPages1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.�����������
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");

        //2������ҵ��
        PageBean pb= service.findUserByPage(currentPage,rows,name,sex);
        //3����pbת��json����
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(pb);
        resp.getWriter().print(s);
    }

    public void delStu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //��ȡids
        String ids = req.getParameter("ids");
        //����ҵ��
        int row =service.delAll(ids);
        //��Ӧ������
        if(row>0){
            //�ض��򵽷�ҳ��ѯ
            resp.sendRedirect(req.getContextPath()+"/stuServlet?method=findPages");
        }else{
            throw new RuntimeException("����ɾ��ѧ��ʧ��");
        }
    }

    public void findStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡsid
        String sid = req.getParameter("sid");
        //����ҵ��
        Student student=  service.findStudent(sid);
        //���������
        req.setAttribute("stu",student);
        //ת����edit.jsp
        req.getRequestDispatcher("/jsp/edit.jsp").forward(req,resp);

    }
    public void updateStu(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {
        //���ܱ����ݵ�map��
        Map<String, String[]> map = req.getParameterMap();
        //����Student����
        Student stu = new Student();
        //��map�е����ݷ�װ��stu����
        BeanUtils.populate(stu,map);
        String[] hobbies = req.getParameterValues("hobby");
        stu.setHobby(Arrays.toString(hobbies));
        //��ȡ�ļ���Դ
        Part part = req.getPart("fileName");
        //��ȡ�ļ���
        String fileName = part.getSubmittedFileName();

        if("".equals(fileName) || fileName==null){
            //û���ϴ���ͷ��
            //��ȡԭʼͷ������
            String oldPhoto = req.getParameter("oldphoto");
            //����ͷ������
            stu.setPhoto(oldPhoto);
        }else{
            //�ϴ�����ͷ��
            fileName= UploadUtils.upload(req);
            //�����µ��ļ���
            stu.setPhoto(fileName);
        }
        //����ҵ��
        int row =service.updateStudent(stu);
        //��Ӧ������
        if (row > 0) {
            //���³ɹ�
            resp.sendRedirect(req.getContextPath() + "/stuServlet?method=findPages");
        } else {
            throw new RuntimeException("����ѧ��ʧ��");
        }

    }

}
