package com.ht.service.ljy;

import java.util.List;

/**
 * Created by JY on 2019/12/19.
 */
public interface bedroomService {

    //    查询分页
    public List bedroom_list(int page, int limit);

    //查询分页计数
    public int bedroom_count();

    //带条件查询
    public List bedroom_search_list(int page, int limit, Integer clazz, Integer floor, Integer huor);

    //    带条件查询的个数
    public int bedroom_search_count(Integer clazz, Integer floor, Integer huor);

    //查询所在宿舍的学生
    public List huor_stuName(int page, int limit, int huor);

    public int huor_stuName_count(int huor);

}
