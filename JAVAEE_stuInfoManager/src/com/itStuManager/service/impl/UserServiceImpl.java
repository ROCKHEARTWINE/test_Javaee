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
        //�ַ���תint
        int current = Integer.parseInt(currentPage);
        int row = Integer.parseInt(rows);
        //����ǰҳ
        pb.setCurrentPage(current);
        //��ÿҳ��ʾ����
        pb.setRows(row);

        //����һ���ɱ��sql
        StringBuffer sb = new StringBuffer("select * from student  where 1=1  ");
        //�������������洢?ռλ���Ĳ���
        ArrayList<Object> params = new ArrayList<>();
        if (name != null && !name.trim().equals("")) {
            //��Ҫ����name����
            sb.append("  and name like ?  ");
            params.add("%" + name + "%");
        }

        if (sex != null && !sex.equals("") && !"-1".equals(sex)) {

            sb.append("   and sex =?  ");
            if ("0".equals(sex)) {
                sex = "��";
            } else if ("1".equals(sex)) {
                sex = "Ů";
            }
            params.add(sex);
        }
        //��ѯ������
        int totalCount = dao.findCount(sb.toString(), params.toArray());
        //��װ��ȥ
        pb.setTotalCount(totalCount);

        //��ҳ��= ������%ÿҳ��ʾ����==0?������/ÿҳ��ʾ����:������/ÿҳ��ʾ����+1;
        int totalPage = totalCount % row == 0 ? totalCount / row : totalCount / row + 1;
        pb.setTotalPage(totalPage);
        //�����뿴��ҳ���ÿҳ��ʾ�����Լ���ѯ������ȡ�û��뿴������
        List<Student> list = dao.findByLimit(sb, params, current, row);
        //��װ��ȥ
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
