package com.ht.service.ljy;

import com.ht.vo.ChatRecordVO;

import java.util.List;

/**
 * Created by JY on 2019/12/5.
 */

//
public interface chatRecordService {

//查询谈心记录
    public List chatrecord_list(int page, int limit);

//    我的谈心记录
    public List chatrecord_mylist(int page, int limit,Integer empId);

//    查询谈心数量
    public int chatrecord_count();

    public int mychatrecord_count(Integer empId);

//    增加谈心记录

    public void chatrecord_add(ChatRecordVO chatRecordVO);

//    删除谈心记录

    public void chatrecord_delete(ChatRecordVO chatRecordVO);

}
