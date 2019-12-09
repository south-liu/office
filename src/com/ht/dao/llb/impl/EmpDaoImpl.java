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
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        EmpVO empVO = (EmpVO) session.createQuery("from EmpVO where phone = '"+phone+"'").uniqueResult();
        return empVO;
    }

    @Override
    public int countEmp() {
        return totalRowByHql("select count(*) from EmpVO");
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
    public void delEmp(String ids) {
        executeSQL("delete from emp where empId in ("+ids+")");
    }

    @Override
    public void updEmp(EmpVO empVO) {
        update(empVO);
    }

    @Override
    public void updEmpStatus(EmpVO empVO) {
        update(empVO);
    }
}
