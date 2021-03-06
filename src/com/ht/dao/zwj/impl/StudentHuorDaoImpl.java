package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.StudentHuorDao;
import com.ht.vo.StudentFloorVO;
import com.ht.vo.StudentHuorVO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StudentHuorDaoImpl implements StudentHuorDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<StudentHuorVO> allData(Integer page, Integer limit) {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from " + currentTableName + " order by huoeIddsc asc," + getCurrentTableId + " desc limit ?,?");
        sqlQuery.setInteger(0, (page - 1) * limit);
        sqlQuery.setInteger(1, limit);
        sqlQuery.addEntity(StudentHuorVO.class);
        List<StudentHuorVO> list = sqlQuery.list();// 执行查询

        session.close();
        return list;
    }

    @Override
    public List<StudentHuorVO> allData() {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from " + currentTableName + " order by " + getCurrentTableId + " asc");
        sqlQuery.addEntity(StudentHuorVO.class);
        List<StudentHuorVO> list = sqlQuery.list();// 执行查询

        session.close();
        return list;
    }

    @Override
    public List<StudentHuorVO> allDataByFloorId(Integer floorId) {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select h.* from " + currentTableName + " as h left join " + floorTableName + " as f on h.floorId = f.floorId where f.floorId = " + floorId + " order by " + getCurrentTableId + " asc");
        sqlQuery.addEntity(StudentHuorVO.class);
        List<StudentHuorVO> list = sqlQuery.list();// 执行查询

        session.close();
        return list;
    }

    @Override
    public List<StudentHuorVO> queryStudentHuorNotHuorid(Integer hourId, String huorName) {
        Session session = sessionFactory.openSession();

        List<StudentHuorVO> list =
                session.createSQLQuery("select * from " + currentTableName + " where hourId != " + hourId + " and huorName = '" + huorName + "'").setResultTransformer(Transformers.aliasToBean(StudentHuorVO.class)).list();

        session.close();
        return list;
    }

    @Override
    public int updateStudentHuorIdByHuorId(int afterHourid, int beforeHourid) {
        Session session = sessionFactory.openSession();

        int i = session.createSQLQuery("update student set huor = " + afterHourid + " where huor = " + beforeHourid).executeUpdate();

        session.close();
        return i;
    }

    @Override
    public long getTotality() {
        Session session = sessionFactory.openSession();

        long totality = Long.parseLong(String.valueOf(session.createSQLQuery("select count(1) from  " + currentTableName).uniqueResult()));

        session.close();
        return totality;
    }

    @Override
    public StudentFloorVO obtainStudentFloorByFloorId(Integer floorId) {
        Session session = sessionFactory.openSession();

        StudentFloorVO studentFloor = (StudentFloorVO) session.createSQLQuery("select * from " + floorTableName + " where floorId = " + floorId).addEntity(StudentFloorVO.class).uniqueResult();

        session.close();
        return studentFloor;
    }


    @Override
    public int add(StudentHuorVO studentHuorVO) {
        Session session = sessionFactory.openSession();

        int id = (int) session.save(studentHuorVO);

        session.close();

        return id;
    }

    @Override
    public StudentHuorVO getStudentHuorByHuorName(String huorName) {
        Session session = sessionFactory.openSession();

        StudentHuorVO studentHuorVO = (StudentHuorVO) session.createSQLQuery("select * from " + currentTableName + " where huorName = " + huorName).addEntity(StudentHuorVO.class).uniqueResult();

        session.close();
        return studentHuorVO;
    }

    @Override
    public List<StudentFloorVO> allFloorData() {
        Session session = sessionFactory.openSession();

        List<StudentFloorVO> list = session.createSQLQuery("select * from " + floorTableName).addEntity(StudentFloorVO.class).list();

        session.close();
        return list;
    }

    @Override
    public StudentHuorVO getStudentHuorById(int hourId) {
        Session session = sessionFactory.openSession();

        StudentHuorVO object = (StudentHuorVO) session.createSQLQuery("select * from " + currentTableName + " where hourId = " + hourId).addEntity(StudentHuorVO.class).uniqueResult();

        session.close();
        return object;
    }

    @Override
    public void updateStudentHuor(StudentHuorVO studentHuor) {
        Session session = sessionFactory.openSession();

        session.update(studentHuor);
        session.flush();

        session.close();
    }

    @Override
    public void delete(StudentHuorVO studentHuorById) {
        Session session = sessionFactory.openSession();

        session.delete(studentHuorById);
        session.flush();

        session.close();
    }
}
