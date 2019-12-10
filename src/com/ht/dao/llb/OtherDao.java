package com.ht.dao.llb;

import com.ht.dao.BaseDao;
import com.ht.vo.StudentClassVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class OtherDao extends BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    //查询所有班级
    public List<StudentClassVO> studentClassList(){
        return findAllByHql("from StudentClassVO");
    }
}
