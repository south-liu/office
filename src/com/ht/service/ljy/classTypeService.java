package com.ht.service.ljy;

import com.ht.vo.ClassTypeVO;

import java.util.List;

/**
 * Created by JY on 2019/12/4.
 */

public interface classTypeService {

    public List classTypeList();
    //    sql分页查询表,页数，每页个数
    public List classTypeList(int page, int limit);
    //查询个数
    public int classTypeCount();
    //删除方法
    public void deleteClassType(ClassTypeVO classTypeVO);
    //添加方法
    public void addClassType(ClassTypeVO classTypeVO);
    //修改方法
    public void updateClassType(ClassTypeVO classTypeVO);


}
