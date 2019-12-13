package com.ht.dao.llb;

import com.ht.vo.StudentVO;

import java.util.List;

public interface IStudentDao {
    public StudentVO findById(Integer studId);
    public StudentVO findByPhone(String phone);

    public List<StudentVO> allStu();
    public List<StudentVO> pageList(int page, int limit);

    public int countStudent();

    public void addStu(StudentVO studentVO);
    public void delStu(String ids);
    public void updStu(StudentVO studentVO);

    public void repass(Integer stuId);
}
