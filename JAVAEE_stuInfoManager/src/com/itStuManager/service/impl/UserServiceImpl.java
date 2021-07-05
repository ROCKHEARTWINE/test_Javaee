package com.itStuManager.service.impl;

import com.itStuManager.dao.UserDao;
import com.itStuManager.dao.impl.UserDaoImpl;
import com.itStuManager.domain.PageBean;
import com.itStuManager.domain.Student;
import com.itStuManager.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public int addUser(Student stu) {
        return dao.saveUser(stu);
    }

    @Override
    public PageBean<Student> findUserByPage(String currentPage, String rows,String name, String sex) {
        if (currentPage == null || currentPage.trim().equals("")) {
            currentPage = "1";
        }
        if (rows == null || rows.equals("")) {
            rows = "3";
        }


        PageBean pb = new PageBean();
        //字符串转int
        int current = Integer.parseInt(currentPage);
        int row = Integer.parseInt(rows);
        //将当前页
        pb.setCurrentPage(current);
        //将每页显示条数
        pb.setRows(row);

        //创建一个可变的sql
        StringBuffer sb = new StringBuffer("select * from student  where 1=1  ");
        //创建容器用来存储?占位符的参数
        ArrayList<Object> params = new ArrayList<>();
        if (name != null && !name.trim().equals("")) {
            //需要进行name搜索
            sb.append("  and name like ?  ");
            params.add("%" + name + "%");
        }

        if (sex != null && !sex.equals("") && !"-1".equals(sex)) {

            sb.append("   and sex =?  ");
            if ("0".equals(sex)) {
                sex = "男";
            } else if ("1".equals(sex)) {
                sex = "女";
            }
            params.add(sex);
        }
        //查询总条数
        int totalCount = dao.findCount(sb.toString(), params.toArray());
        //封装进去
        pb.setTotalCount(totalCount);

        //总页数= 总条数%每页显示条数==0?总条数/每页显示条数:总条数/每页显示条数+1;
        int totalPage = totalCount % row == 0 ? totalCount / row : totalCount / row + 1;
        pb.setTotalPage(totalPage);
        //根据想看的页面和每页显示条数以及查询条件获取用户想看的数据
        List<Student> list = dao.findByLimit(sb, params, current, row);
        //封装进去
        pb.setList(list);

        return pb;
    }

    @Override
    public int delAll(String ids) {
        return dao.delAllByIds(ids);
    }

    @Override
    public Student findStudent(String sid) {
        return dao.findBySid(sid);
    }

    @Override
    public int updateStudent(Student stu) {
        return dao.updateStudentBySid(stu);
    }


}
