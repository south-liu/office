package com.ht.service.llb;

import com.ht.vo.DataDocVO;

import java.util.List;

public interface IDataDocService {
    public void addFile(DataDocVO dataDocVO);
    public int countFile();
    public List<DataDocVO> pageList(int page, int limit);
    public DataDocVO findById(Integer docId);
    public void deleteFile(DataDocVO dataDocVO);
    public DataDocVO findFile(Integer docId);
}
