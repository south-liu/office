package com.ht.service.llb.impl;

import com.ht.dao.llb.ISystemLogDao;
import com.ht.service.llb.ISystemLogService;
import com.ht.vo.SystemLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SystemLogServiceImpl implements ISystemLogService {

    @Resource
    private ISystemLogDao systemLogDao;

    @Override
    public List pageList(int page, int limit) {
        return systemLogDao.pageList(page,limit);
    }

    @Override
    public List pageListWhere(int page, int limit, String startTime, String endTime) {
        return systemLogDao.pageListWhere(page,limit,startTime,endTime);
    }

    @Override
    public List<SystemLogVO> allSystemLog() {
        return systemLogDao.allSystemLog();
    }

    @Override
    public List allSystemLogBySql() {
        return systemLogDao.allSystemLogBySql();
    }

    @Override
    public void addSystemLog(SystemLogVO systemLogVO) {
        systemLogDao.addSystemLog(systemLogVO);
    }

    @Override
    public SystemLogVO selSystemLogById(Integer logId) {
        return systemLogDao.selSystemLogById(logId);
    }

    @Override
    public Map selSystemLogByIdToMap(Integer logId) {
        return systemLogDao.selSystemLogByIdToMap(logId);
    }
}
