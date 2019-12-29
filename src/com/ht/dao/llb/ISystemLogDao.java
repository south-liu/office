package com.ht.dao.llb;

import com.ht.vo.SystemLogVO;

import java.util.List;
import java.util.Map;

public interface ISystemLogDao {

    List pageList(int page, int limit);
    List pageListWhere(int page, int limit,String startTime,String endTime);

    List<SystemLogVO> allSystemLog();
    List allSystemLogBySql();

    void addSystemLog(SystemLogVO systemLogVO);

    SystemLogVO selSystemLogById(Integer logId);

    Map selSystemLogByIdToMap(Integer logId);
}