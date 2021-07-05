package com.itStuManager.service;

import com.itStuManager.domain.PageBean;
import com.itStuManager.domain.Student;

public interface UserService {
    public int addUser(Student stu);


    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param name
     * @param sex
     * @return
     */
    PageBean<Student> findUserByPage(String currentPage, String rows,String name, String sex);

    int delAll(String ids);

    Student findStudent(String sid);

    int updateStudent(Student stu);
}
