package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.ISystemLogDao;
import com.ht.vo.SystemLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SystemLogDaoImpl extends BaseDao implements ISystemLogDao {
    @Override
    public List pageList(int page, int limit) {
        return pageListBySql("select s.*,e.empName from systemLog s left join emp e on s.empId = e.empId order by s.logId desc",page,limit);
    }

    @Override
    public List pageListWhere(int page, int limit, String startTime, String endTime) {
        String sql = "select s.*,e.empName from systemLog s left join emp e on s.empId = e.empId where 1=1 ";
        if (startTime!=null && !"".equals(startTime)){
            sql+=" and s.optime >'"+startTime+"'";
        }
        if (endTime!=null && !"".equals(endTime)){
            sql+=" and s.optime < '"+endTime+"'";
        }
        sql+="order by s.logId desc";
        return pageListBySql(sql,page,limit);
    }

    @Override
    public List<SystemLogVO> allSystemLog() {
        return findAllByHql("from SystemLogVO");
    }

    @Override
    public List allSystemLogBySql() {
        return findAllBySql("select s.*,e.empName from systemLog s left join emp e on s.empId = e.empId");
    }

    @Override
    public void addSystemLog(SystemLogVO systemLogVO) {
        saveObject(systemLogVO);
    }

    @Override
    public SystemLogVO selSystemLogById(Integer logId) {
        return (SystemLogVO) findOneByHql("from SystemLogVO where logId = "+logId);
    }

    @Override
    public Map selSystemLogByIdToMap(Integer logId) {
        return (Map) findOneBySql("select s.*,e.empName from systemLog s left join emp e on s.empId = e.empId where s.logId = "+logId);
    }
}
