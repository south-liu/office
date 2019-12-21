package com.ht.service.llb.impl;

import com.ht.dao.llb.INoticeDao;
import com.ht.service.llb.INoticeService;
import com.ht.vo.NoticeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements INoticeService {

    @Resource
    private INoticeDao noticeDao;


    @Override
    public Integer countNotice(Integer noticeType) {
        return noticeDao.countNotice(noticeType);
    }

    @Override
    public List<NoticeVO> pageList(Integer noticeType,int page, int limit) {
        return noticeDao.pageList(noticeType,page,limit);
    }

    @Override
    public List<NoticeVO> allNotice() {
        return noticeDao.allNotice();
    }

    @Override
    public List allNoticeBySql() {
        return noticeDao.allNoticeBySql();
    }

    @Override
    public void addNotice(NoticeVO noticeVO) {
        noticeDao.addNotice(noticeVO);
    }

    @Override
    public void delNotice(NoticeVO noticeVO) {
        noticeDao.delNotice(noticeVO);
    }

    @Override
    public void updNotice(NoticeVO noticeVO) {
        noticeDao.updNotice(noticeVO);
    }

    @Override
    public NoticeVO selNoticeById(Integer noticeId) {
        return noticeDao.selNoticeById(noticeId);
    }

    @Override
    public Map selNoticeByIdToMap(Integer noticeId) {
        return noticeDao.selNoticeByIdToMap(noticeId);
    }
}
