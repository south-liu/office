package com.ht.dao.llb;

import com.ht.vo.StudentVO;

import java.util.List;

public interface IStudentDao {
    public StudentVO findById(Integer studId);
    public StudentVO findByPhone(String phone);

    public List<StudentVO> allStu();
    public List<StudentVO> pageList(int page, int limit,Integer empId,String postName);
    public List<StudentVO> pageListWhere(int page, int limit,Integer empId,String postName,String stuName,String phone,Integer clazz,Integer huor);
    public List pageListWhere(int page, int limit,String stuName,String phone);

    public int countStudent();
    public int countStuWhere(String stuName,String phone,Integer clazz,Integer huor);

    public void addStu(StudentVO studentVO);
    public void delStu(String ids);
    public void updStu(StudentVO studentVO);

    public void repass(Integer stuId,String password);
    public void updStuStatus(Integer stuId,Integer stat);
}
