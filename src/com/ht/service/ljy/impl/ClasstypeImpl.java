package com.ht.service.ljy.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.classTypeService;
import com.ht.vo.ClassTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/4.
 */

@Service
public class ClasstypeImpl extends ljyDao implements classTypeService {


    @Override
    public List classTypeList() {
        return listbysql("select * from classType ");
    }

    //    sql分页查询班级列表
    @Override
    public List classTypeList(int page, int limit) {
        return pagelistbysql("select * from classType ",page,limit);
    }

//    查询班级类别总数
    @Override
    public int classTypeCount() {
        return selTotalRow("select count(*) from classType ");
    }
//删除班级类别方法
    @Override
    public void deleteClassType(ClassTypeVO classTypeVO) {
      deleteobject(classTypeVO);
    }

//    添加班级类别方法
    @Override
    public void addClassType(ClassTypeVO classTypeVO) {
        addobject(classTypeVO);
    }


//    修改班级类型方法
    @Override
    public void updateClassType(ClassTypeVO classTypeVO) {
        updateobject(classTypeVO);
    }
}
