package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.StudentFallDao;
import com.ht.vo.StudentFallVO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StudentFallDaoImpl implements StudentFallDao {
    @Resource
    private SessionFactory sessionFactory;

    /**
     * 根据分页查询届别记录
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<StudentFallVO> allData(Integer page, Integer limit) {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from studentFall order by fallId desc limit ?,?");
        sqlQuery.setInteger(0, (page - 1) * limit);
        sqlQuery.setInteger(1, limit);
        sqlQuery.addEntity(StudentFallVO.class);
        List<StudentFallVO> list = sqlQuery.list();// 执行查询

        session.close();
        return list;
    }

    /**
     * 通过id查询对象
     *
     * @param fallId
     * @return
     */
    @Override
    public StudentFallVO getStudentFallById(int fallId) {
        Session session = sessionFactory.openSession();

        StudentFallVO studentFallVO = (StudentFallVO) session.get(StudentFallVO.class, fallId);

        session.close();
        return studentFallVO;
    }

    /**
     * 通过id删除对象
     *
     * @param studentFallVO
     */
    @Override
    public void delete(StudentFallVO studentFallVO) {
        Session session = sessionFactory.openSession();

        session.delete(studentFallVO);
        session.flush();

        session.close();
    }

    /**
     * 添加对象
     *
     * @param studentFallVO
     * @return
     */
    @Override
    public int add(StudentFallVO studentFallVO) {
        Session session = sessionFactory.openSession();

        int id = (int) session.save(studentFallVO);

        session.close();

        return id;
    }

    /**
     * 通过届别名称获取对象
     *
     * @param level
     * @return
     */
    @Override
    public StudentFallVO getStudentFallByLevel(String level) {
        Session session = sessionFactory.openSession();

        StudentFallVO studentFallVO = (StudentFallVO) session.createSQLQuery("select * from studentFall where level = '" + level + "'").addEntity(StudentFallVO.class).uniqueResult();

        session.close();
        return studentFallVO;
    }

    /**
     * 修改对象
     *
     * @param studentFallVO
     */
    @Override
    public void update(StudentFallVO studentFallVO) {
        Session session = sessionFactory.openSession();

        session.update(studentFallVO);
        session.flush();

        session.close();
    }

    /**
     * 获取表总数
     *
     * @return
     */
    @Override
    public long getTotality() {
        Session session = sessionFactory.openSession();

        long totality = Long.parseLong(String.valueOf(session.createSQLQuery("select count(1) from studentFall").uniqueResult()));

        session.close();
        return totality;
    }

    @Override
    public List<StudentFallVO> allData() {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from studentFall order by fallId asc");
        sqlQuery.addEntity(StudentFallVO.class);
        List<StudentFallVO> list = sqlQuery.list();// 执行查询

        session.close();
        return list;
    }

    @Override
    public List<StudentFallVO> queryStudentFallByLevelNotFallid(Integer fallId, String level) {
        Session session = sessionFactory.openSession();

        List<StudentFallVO> list =
                session.createSQLQuery("select * from studentFall where fallId != " + fallId + " and level = '" + level + "'").addEntity(StudentFallVO.class).list();

        session.close();
        return list;
    }
}
