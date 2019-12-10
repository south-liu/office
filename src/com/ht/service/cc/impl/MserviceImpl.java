package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.CollegeService;
import com.ht.service.cc.MajorService;
import com.ht.vo.CollegeDeptVO;
import com.ht.vo.MajorVO;
import com.ht.vo.StudentFallVO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MserviceImpl extends BaseDao implements MajorService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List selSpage(int currPage, int pageSize) {
      return pageListBySql("select m.*,c.collegeDeptName from major m left join collegeDept c on m.collegeDeptId=c.collegeDeptId ",currPage,pageSize);
//        return  pageListByHql("from MajorVO  m left join collegeDept c on m.collegeDeptId=c.collegeDeptId ",currPage,pageSize);
    }


    @Override
    public Integer seltotalMajor() {
        //return totalRowBySql("select count(*) from MajorVO");
        return totalRowByHql("select count(*) from MajorVO");
    }

    @Override
    public void AddMajor(MajorVO majorVO) {
        saveObject(majorVO);
    }


    @Override
    public void UpdMajor(MajorVO majorVO) {
            update(majorVO);
    }

    @Override
    public void delMajor(String id) {
        executeSQL("delete from major where majorId ="+id);
    }

    @Override
    public List<CollegeDeptVO> AllCollege() {
        return findAllByHql("from CollegeDeptVO");
    }

    @Override
    public MajorVO AllMajor(int majorId) {
        return (MajorVO)selectOneByHql("from MajorVO where majorId ="+majorId);
    }

    @Override
    public List<MajorVO> allMajor() {
        return findAllByHql("from MajorVO");
    }

}
