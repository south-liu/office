package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IDeptDao;
import com.ht.vo.DeptVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class DeptDaoImpl extends BaseDao implements IDeptDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<DeptVO> allDept() {
        return findAllByHql("from DeptVO");
    }

    @Override
    public List<DeptVO> allHeadDept() {
        return findAllByHql("from DeptVO where parentId = 0");
    }

    @Override
    public DeptVO selById(Integer deptId) {
        Session session = sessionFactory.openSession();
        DeptVO deptVO = (DeptVO) session.createQuery("from DeptVO where deptId = "+deptId).uniqueResult();
        session.close();
        return deptVO;
    }

    @Override
    public void addDept(DeptVO deptVO) {
        saveObject(deptVO);
    }

    @Override
    public void updateDept(DeptVO deptVO) {
        update(deptVO);
    }

    @Override
    public void deleteDept(DeptVO deptVO) {
        delete(deptVO);
    }

    @Override
    public void deleteChildDept(Integer pid) {
       executeSQL("delete from dept where parentId = "+pid);
    }
}
