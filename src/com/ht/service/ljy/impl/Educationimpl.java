package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.educationService;
import com.ht.vo.EducationVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/5.
 */

@Service
public class Educationimpl extends ljyDao implements educationService {

    //    分页查询
    @Override
    public List education_list(int page, int limit,String empId) {
        return pagelistbysql("select * from education where empId="+empId, page, limit);
    }

    //    查询个数
    @Override
    public int education_count() {
        return selTotalRow("select count(*) from education");
    }

    @Override
    public int education_countByEmpId(Integer empId) {
        return selTotalRow("select count(*) from education where empId = "+empId);
    }

    //    添加教育背景
    @Override
    public void education_add(EducationVO educationVO) {
        addobject(educationVO);
    }

    //删除教育背景
    @Override
    public void education_delete(EducationVO educationVO) {
        deleteobject(educationVO);
    }

    //修改教育背景
    @Override
    public void education_update(EducationVO educationVO) {
        updateobject(educationVO);
    }
}
