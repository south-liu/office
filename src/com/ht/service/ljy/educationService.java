package com.ht.service.ljy;

import com.ht.vo.EducationVO;

import java.util.List;

/**
 * Created by JY on 2019/12/5.
 */
public interface educationService {
//    分页查询教育背景页面
    public List education_list(int page, int limit,String empId);

//    查询教育背景总数
    public int education_count();

    //    查询教育背景总数
    public int education_countByEmpId(Integer empId);

//    添加教育背景
    public void education_add(EducationVO educationVO);

//    删除教育背景
    public void education_delete(EducationVO educationVO);

//    修改教育背景
    public void education_update(EducationVO educationVO);




}
