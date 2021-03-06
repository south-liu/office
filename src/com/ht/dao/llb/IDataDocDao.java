package com.ht.dao.llb;

import com.ht.vo.DataDocVO;

import java.util.List;

public interface IDataDocDao {
    public void addFile(DataDocVO dataDocVO);
    public int countFile();
    public List<DataDocVO> pageList(int page, int limit);
    public DataDocVO findById(Integer docId);
    //删除
    public void deleteFile(DataDocVO dataDocVO);

    public DataDocVO findFile(Integer docId);
}
