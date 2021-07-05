package com.itStuManager.dao;

import com.itStuManager.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 保存stu封装的对象
     * @param stu
     * @return
     */
    public int saveUser(Student stu);


    int findCount(String sql, Object[] params);

    List<Student> findByLimit(StringBuffer sb, ArrayList<Object> params, int current, int row);

    int delAllByIds(String ids);

    Student findBySid(String sid);

    int updateStudentBySid(Student stu);
}
