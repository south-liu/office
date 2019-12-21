package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.INoticeDao;
import com.ht.vo.NoticeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoticeDaoImpl extends BaseDao implements INoticeDao {
    @Override
    public Integer countNotice(Integer noticeType) {
        String hql = "";
        if (noticeType == 1) { //所有人
            hql = "select count(*) from NoticeVO";
        } else if (noticeType == 2) {//员工
            hql = "select count(*) from NoticeVO where noticeType != 3";
        } else if (noticeType == 3) {//学生
            hql = "select count(*) from NoticeVO where noticeType != 2";
        }
        return totalRowByHql(hql);
    }


    @Override
    public List<NoticeVO> pageList(Integer noticeType,int page, int limit) {
        String sql = "";
        if (noticeType == 1) { //所有人
            sql = "select n.*,e.empName from notice n left join emp e on n.empId = e.empId order by n.noticeId desc";
        } else if (noticeType == 2) {//员工
            sql = "select n.*,e.empName from notice n left join emp e on n.empId = e.empId where n.noticeType != 3 order by n.noticeId desc";
        } else if (noticeType == 3) {//学生
            sql = "select n.*,e.empName from notice n left join emp e on n.empId = e.empId where n.noticeType != 2 order by n.noticeId desc";
        }
        return pageListBySql(sql,page,limit);
//        return pageListByHql("from NoticeVO",page,limit);
    }


    @Override
    public List<NoticeVO> allNotice() {
        return findAllByHql("from NoticeVO");
    }

    @Override
    public List allNoticeBySql() {
        return findAllBySql("select n.*,e.empName from notice n left join emp e on n.empId = e.empId order by n.noticeId desc");
    }

    @Override
    public void addNotice(NoticeVO noticeVO) {
        saveObject(noticeVO);
    }

    @Override
    public void delNotice(NoticeVO noticeVO) {
        delete(noticeVO);
    }

    @Override
    public void updNotice(NoticeVO noticeVO) {
        update(noticeVO);
    }

    @Override
    public NoticeVO selNoticeById(Integer noticeId) {
        return (NoticeVO) findOneByHql("from NoticeVO where noticeId = "+noticeId);
    }

    @Override
    public Map selNoticeByIdToMap(Integer noticeId) {
        return  (Map) findOneBySql("select n.*,e.empName from notice n left join emp e on n.empId = e.empId where n.noticeId="+noticeId);
    }
}
