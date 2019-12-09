package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.CollegeService;
import com.ht.vo.CollegeDeptVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CserviceImpl extends BaseDao implements CollegeService {

    @Override
    public List selSpage(int currPage, int pageSize) {
      //  return pageListBySql("select * from collegeDept",currPage,pageSize);
        return  pageListByHql("from CollegeDeptVO",currPage,pageSize);
    }


    @Override
    public Integer seltotalClege() {
        //return totalRowBySql("select count(*) from collegeDept");
        return totalRowByHql("select count(*) from CollegeDeptVO");
    }

    @Override
    public void AddClege(CollegeDeptVO collegeDeptVO) {
        saveObject(collegeDeptVO);
    }


    @Override
    public void UpdClege(CollegeDeptVO collegeDeptVO) {
            update(collegeDeptVO);
    }

    @Override
    public void delClege(String id) {
        System.out.println("delete from collegeDept where collegeDeptId ="+id+"");
        executeSQL("delete from collegeDept where collegeDeptId ="+id);
    }

}
