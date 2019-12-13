package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IEmpDao;
import com.ht.vo.EmpVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class EmpDaoImpl extends BaseDao implements IEmpDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public void addEmp(EmpVO empVO) {
        saveObject(empVO);
    }

    @Override
    public EmpVO findEmpById(Integer empId) {
       return (EmpVO) findOneByHql("from EmpVO where empId = "+empId);
    }

    @Override
    public EmpVO findEmpByPhone(String phone) {
        return (EmpVO) findOneByHql("from EmpVO where phone = '"+phone+"'");
    }

    @Override
    public int countEmp() {
        return totalRowByHql("select count(*) from EmpVO");
    }

    @Override
    public int countEmpWhere(Integer deptId, String empName, String phone) {
        String sql = "select count(*) from emp e where 1=1";
        if (deptId!=null && !"".equals(deptId)){
            sql+=" and e.deptId = "+deptId;
        }
        if (empName!=null && !"".equals(empName)){
            sql+=" and e.empName like '%"+empName+"%'";
        }
        if (phone!=null && !"".equals(phone)){
            sql+=" and e.phone like '%"+phone+"%'";
        }
        return totalRowBySql(sql);
    }

    @Override
    public List<EmpVO> allEmp() {
        return findAllByHql("from EmpVO");
    }

    @Override
    public List<EmpVO> pageList(int page, int limit) {
        //return pageListByHql("from EmpVO",page,limit);
        return pageListBySql("select e.*,d.deptName from emp e left join dept d on e.deptId = d.deptId",page,limit);
    }

    @Override
    public List<EmpVO> pageListWhere(int page, int limit, Integer deptId, String empName, String phone) {
        String sql = "select e.*,d.deptName from emp e left join dept d on e.deptId = d.deptId where 1=1 ";
        if (deptId!=null && !"".equals(deptId)){
            sql+=" and e.deptId = "+deptId;
        }
        if (empName!=null && !"".equals(empName)){
            sql+=" and e.empName like '%"+empName+"%'";
        }
        if (phone!=null && !"".equals(phone)){
            sql+=" and e.phone like '%"+phone+"%'";
        }
        return pageListBySql(sql,page,limit);
    }

    @Override
    public void delEmp(String ids) {
        executeSQL("delete from emp where empId in ("+ids+")");
    }

    @Override
    public void updEmp(EmpVO empVO) {
        update(empVO);
    }

    @Override
    public void updEmpStatus(Integer empId,Integer status) {
        executeSQL("update emp set status = "+status+" where empId = "+empId);
    }

    @Override
    public void repass(Integer empId) {
        executeSQL("update emp set password = '123456' where empId = "+empId);
    }
}
