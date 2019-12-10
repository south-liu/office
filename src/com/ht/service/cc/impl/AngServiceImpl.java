package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.AngService;
import com.ht.vo.EmpVO;
import com.ht.vo.WeekArrangeVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AngServiceImpl extends BaseDao implements AngService {
    @Override
    public List selSpage(int currPage, int pageSize) {
        return pageListBySql("select w.*,e.empName from weekArrange w left join emp e on w.empId=e.empId ",currPage,pageSize);

    }

    @Override
    public Integer seltotalAng() {
        return totalRowByHql("select count(*) from WeekArrangeVO");
    }

    @Override
    public void AddAng(WeekArrangeVO weekArrangeVO) {
        saveObject(weekArrangeVO);
    }

    @Override
    public void UpdAng(WeekArrangeVO weekArrangeVO) {
        update(weekArrangeVO);
    }

    @Override
    public void delAng(String id) {
        executeSQL("delete from weekArrange where weekArrangeId ="+id);
    }

    @Override
    public List<EmpVO> Allemp() {
        return findAllByHql("from EmpVO");
    }

    @Override
    public WeekArrangeVO AllAng(int angId) {
        return (WeekArrangeVO) selectOneByHql("from WeekArrangeVO where weekArrangeId ="+angId);
    }

}
