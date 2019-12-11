package com.ht.service.ljy;

import com.ht.vo.StudentClassVO;

import java.util.List;

/**
 * Created by JY on 2019/12/6.
 */
public interface studentclassService {

    public List studentclass_list();

    public List studentclass_list(int page, int limit);

    public int studentclass_count();

    public void studentclass_delete(StudentClassVO studentClassVO);

    public void studentclass_add(StudentClassVO studentClassVO);

    public void student_update(StudentClassVO studentClassVO);

    public StudentClassVO studentclassbyid(int classId);


    public int studentclasschoose_count(int falled);

    public List studentclass_choose(int falled, int page, int limit);

}
