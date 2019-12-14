package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IDataDocDao;
import com.ht.vo.DataDocVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDocDaoImpl extends BaseDao implements IDataDocDao {
    @Override
    public void addFile(DataDocVO dataDocVO) {
        saveObject(dataDocVO);
    }

    @Override
    public int countFile() {
        return totalRowByHql("select count(*) from DataDocVO");
    }

    @Override
    public List<DataDocVO> pageList(int page, int limit) {
        return pageListBySql("select d.*,e.empName from dataDoc d left join emp e on d.empId = e.empId",page,limit);
    }

    @Override
    public DataDocVO findById(Integer docId) {
        return (DataDocVO) findOneByHql("from DataDocVO where docId = "+docId);
    }

    @Override
    public void deleteFile(DataDocVO dataDocVO) {
        delete(dataDocVO);
    }

    @Override
    public DataDocVO findFile(Integer docId) {
        return (DataDocVO) findOneByHql("from DataDocVO where docId ="+docId);
    }
}
