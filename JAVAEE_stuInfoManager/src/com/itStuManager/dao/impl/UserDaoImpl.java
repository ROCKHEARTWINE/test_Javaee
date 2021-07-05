package com.itStuManager.dao.impl;

import com.itStuManager.dao.UserDao;
import com.itStuManager.domain.Student;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    ComboPooledDataSource ds =  new ComboPooledDataSource();
    QueryRunner qr = new QueryRunner(ds);

    @Override
    public int saveUser(Student stu) {
        String sql = "INSERT INTO student VALUES (null,?,?,?,?,?,?)";
        try {
            return qr.update(sql,stu.getName(),stu.getSex(),stu.getHobby(),stu.getBirthday(),stu.getSdesc(),stu.getPhoto());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int findCount(String sql, Object[] params) {
        try {
            List<Student> studentList = qr.query(sql, new BeanListHandler<Student>(Student.class), params);
            return studentList.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Student> findByLimit(StringBuffer sb, ArrayList<Object> params, int current, int row) {
        sb.append(" limit ?,? ");

        int start = (current - 1) * row;
        params.add(start);
        params.add(row);

        try {
            return qr.query(sb.toString(), new BeanListHandler<Student>(Student.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public int delAllByIds(String ids) {
        String sql = "DELETE FROM student WHERE sid in ( " +ids+ ")";

        try {
            return qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findBySid(String sid) {
        String sql = "select * from student where sid =?";

        try {
            return qr.query(sql, new BeanHandler<Student>(Student.class), sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateStudentBySid(Student s) {
        String sql = "update student set name =?,sex=?,hobby=?,birthday=?,sdesc=?,photo=? where sid =?";

        try {
            return qr.update(sql, s.getName(), s.getSex(), s.getHobby(), s.getBirthday(), s.getSdesc(), s.getPhoto(), s.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
