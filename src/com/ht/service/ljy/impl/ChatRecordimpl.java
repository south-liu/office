package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.chatRecordService;
import com.ht.vo.ChatRecordVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/5.
 */
@Service
public class ChatRecordimpl extends ljyDao implements chatRecordService {

    //分页查询谈心记录
//    @Override
//    public List chatrecord_list(int page, int limit) {
//        return pagelistbysql("select * from chatRecord",page,limit);
//    }
    @Override
    public List chatrecord_list(int page, int limit) {
        return pagelistbysql("select stu.stuName,c.*,e.empName from chatRecord c left join emp e on c.teacher = e.empId left join student stu on stu.studId=c.sayFace", page, limit);
    }

    @Override
    public List chatrecord_mylist(int page, int limit, Integer empId) {
        return pagelistbysql("select stu.stuName,c.*,e.empName from chatRecord c left join emp e on c.teacher = e.empId left join student stu on stu.studId=c.sayFace where teacher=" + empId, page, limit);
    }

    //查询记录总数
    @Override
    public int chatrecord_count() {
        return selTotalRow("select count(*) from chatRecord ");
    }

    @Override
    public int mychatrecord_count(Integer empId) {
        return selTotalRow("select count(*) from chatRecord c left join emp e on c.teacher = e.empId left join student stu on stu.studId=c.sayFace where teacher=" + empId);
    }

    //添加记录
    @Override
    public void chatrecord_add(ChatRecordVO chatRecordVO) {
        addobject(chatRecordVO);
    }

    //删除记录
    @Override
    public void chatrecord_delete(ChatRecordVO chatRecordVO) {
        deleteobject(chatRecordVO);
    }
}
