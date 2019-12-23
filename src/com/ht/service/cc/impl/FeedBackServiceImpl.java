package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.FeedBackService;
import com.ht.vo.DeptVO;
import com.ht.vo.FeedBackMsgVO;
import com.ht.vo.FeedbackVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl extends BaseDao implements FeedBackService {
    @Override
    public List selSpage(int currPage, int pageSize) {
        return pageListByHql("from FeedbackVO",currPage,pageSize);
    }

    @Override
    public List stuselSpage(int currPage, int pageSize, String stuId) {
        return pageListBySql("select * from feedback where empId="+stuId,currPage,pageSize);
    }

    @Override
    public Integer seltotalFeed() {
        return totalRowByHql("select count(*) from FeedbackVO");
    }

    @Override
    public Integer seltotalFeedbyId(String stuId) {
        return totalRowBySql("select count(*) from feedback where empId="+stuId);
    }

    @Override
    public void AddFeed(FeedbackVO feedbackVO) {
        saveObject(feedbackVO);
    }

    @Override
    public void UpdFeed(FeedbackVO feedbackVO) {
        update(feedbackVO);
    }

    @Override
    public void delFeed(String id) {
        executeSQL("delete from feedback where feedbackId ="+id);
    }

    @Override
    public List<DeptVO> AllDept() {
        return findAllByHql("from DeptVO");
    }

    @Override
    public FeedbackVO feedbackbyId(int feedbackId) {
        return (FeedbackVO) selectOneByHql("from FeedbackVO where feedbackId ="+feedbackId);
    }

    @Override
    public List<FeedBackMsgVO> AllMsg(int feedbackId) {
        return findAllBySql("select * from feedbackmsg where feedbackId="+feedbackId+" order by singDate desc");
    }

    @Override
    public void AddMsg(FeedBackMsgVO feedBackMsgVO,int feedbackId) {
        executeSQL("update feedback set status=2 where feedbackId ="+feedbackId);
        saveObject(feedBackMsgVO);
    }
}
