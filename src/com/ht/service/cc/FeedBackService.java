package com.ht.service.cc;

import com.ht.vo.DeptVO;
import com.ht.vo.FeedBackMsgVO;
import com.ht.vo.FeedbackVO;


import java.util.List;

public interface FeedBackService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //学生界面
    public List stuselSpage(int currPage, int pageSize, String stuId);
    //查询总行数
    public Integer seltotalFeed();

    public Integer seltotalFeedbyId(String stuId);
    //添加方法
    public void AddFeed(FeedbackVO feedbackVO);
    //修改方法
    public void UpdFeed(FeedbackVO feedbackVO);
    //删除方法
    public void delFeed(String id);
    //查询出所有部门
    public List<DeptVO> AllDept();
    //查询出所有部门
    public FeedbackVO feedbackbyId(int feedbackId);
    //查询出所有消息
    public List<FeedBackMsgVO> AllMsg(int feedbackId);
    //添加方法
    public void AddMsg(FeedBackMsgVO feedBackMsgVO, int feedbackId);
}
