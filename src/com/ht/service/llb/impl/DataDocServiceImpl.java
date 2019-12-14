package com.ht.service.llb.impl;

import com.ht.dao.llb.IDataDocDao;
import com.ht.service.llb.IDataDocService;
import com.ht.vo.DataDocVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDocServiceImpl implements IDataDocService {
    @Resource
    private IDataDocDao dataDocDao;

    @Override
    public void addFile(DataDocVO dataDocVO) {
        dataDocDao.addFile(dataDocVO);
    }

    @Override
    public int countFile() {
        return dataDocDao.countFile();
    }

    @Override
    public List<DataDocVO> pageList(int page, int limit) {
        return dataDocDao.pageList(page,limit);
    }

    @Override
    public DataDocVO findById(Integer docId) {
        return dataDocDao.findById(docId);
    }

    @Override
    public void deleteFile(DataDocVO dataDocVO) {
        dataDocDao.deleteFile(dataDocVO);
    }

    @Override
    public DataDocVO findFile(Integer docId) {
        return dataDocDao.findFile(docId);
    }
}
