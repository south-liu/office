package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.checkworkService;
import com.ht.vo.CheckWorkVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/11.
 */

@Service
public class checkworkimpl extends ljyDao implements checkworkService {


    @Override
    public List checkwork_list(int page, int limit, int empId) {
//        return pagelistbysql("select ck.*,emp.empName from checkwork ck left join emp on ck.empId=emp.empId where ck.empId="+empId, page, limit);
        return pagelistbysql("select ck.*,emp.empName,dept.chairman from checkwork ck left join emp on ck.empId=emp.empId left join dept on emp.deptId=dept.deptId where ck.empId=" + empId, page, limit);
    }

    @Override
    public int checkwork_count(int empId) {
        return selTotalRow("select count(*) from checkwork where empId=" + empId);
    }

    @Override
    public void checkwork_delete(CheckWorkVO checkWorkVO) {
        deleteobject(checkWorkVO);
    }

    @Override
    public void checkwork_update(int checkworkId, String remark) {
        executeSQL("update checkwork set remark='" + remark + "' where checkworkId=" + checkworkId);
    }

    @Override
    public void checkwork_add(CheckWorkVO checkWorkVO) {
        addobject(checkWorkVO);
    }

    @Override
    public List checkwork_chairmanId(int empId) {
        return listbysql("select emp.empId from emp where empName=(select dept.chairman from emp left join dept on emp.deptId=dept.deptId where emp.empId=" + empId + ")");
    }

    @Override
    public List checkwork_mychecklist(int page, int limit, int empId) {
        return pagelistbysql("select ck.*,emp.empName,dept.chairman from checkwork ck left join emp on ck.empId=emp.empId left join dept on emp.deptId=dept.deptId where ck.deptheadId =" + empId, page, limit);
    }

    @Override
    public int checkwork_mycheckcount(int empId) {
        return selTotalRow("select count(*) from checkwork ck left join emp on ck.empId=emp.empId left join dept on emp.deptId=dept.deptId where ck.deptheadId=" + empId);
    }

    @Override
    public void updatestatus(int checkworkId, int status, String checkTime,String remark) {
        executeSQL("update checkwork set status = " + status + ",checkTime='" + checkTime + "',remark='" + remark + "' where checkworkId = " + checkworkId);
    }

}
