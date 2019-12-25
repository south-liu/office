package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.StudentReplyScoreDao;
import com.ht.vo.ProjectVO;
import com.ht.vo.StudentReplyScoreVO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class StudentReplyScoreDaoImpl implements StudentReplyScoreDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Map<String, Object>> allData(Integer page, Integer limit) {
        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery("select srs.*,cla.className,pro.projectName,stu.stuName,emp.empName,(select e.empName from emp as e where e.empId=srs.gradeEmpId) as 'gradeEmpName' from " + currentTableName + " as srs left join student as stu on stu.studId = srs.studentId left join studentClass as cla on stu.clazz = cla.classId left join project as pro on pro.projectId = srs.projectId left join emp on emp.empId = srs.empId order by " + currentTableId + " asc limit ?,?");
        System.out.println(query);
        query.setInteger(0, (page - 1) * limit);
        query.setInteger(1, limit);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);// 将结果集以Map集合返回
        List<Map<String, Object>> list = query.list();

        session.close();
        return list;
    }

    @Override
    public long getTotality() {
        Session session = sessionFactory.openSession();

        long count = Long.parseLong(String.valueOf(session.createSQLQuery("select count(1) from " + currentTableName).uniqueResult()));

        session.close();
        return count;
    }

    @Override
    public int addStudentReplyScore(StudentReplyScoreVO studentReplyScore) {
        Session session = sessionFactory.openSession();

        int count = (int) session.save(studentReplyScore);

        session.close();
        return count;
    }

    @Override
    public StudentReplyScoreVO getStudentReplyScoreById(int replyId) {
        Session session = sessionFactory.openSession();

        StudentReplyScoreVO o = (StudentReplyScoreVO) session.get(StudentReplyScoreVO.class, replyId);

        session.close();
        return o;
    }

    @Override
    public void updateStudentReplyScore(StudentReplyScoreVO studentReplyScore) {
        Session session = sessionFactory.openSession();

        session.update(studentReplyScore);
        session.flush();

        session.close();
    }

    @Override
    public void deleteStudentReplyScore(StudentReplyScoreVO studentReplyScore) {
        Session session = sessionFactory.openSession();

        session.delete(studentReplyScore);
        session.flush();

        session.close();
    }

    @Override
    public List<Map<String, Object>> findDataBySql(String sql) {
        Session session = sessionFactory.openSession();

        List<Map<String, Object>> list = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        session.close();
        return list;
    }

    @Override
    public long findCountByOptions(String sql) {
        Session session = sessionFactory.openSession();

        long count = ((Number) session.createSQLQuery(sql).uniqueResult()).longValue();

        session.close();
        return count;
    }

    @Override
    public List<ProjectVO> findGradedProjectByStudentId(Integer stuId) {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select distinct p.* from " + currentTableName + " as srs left join project as p on p.projectId = srs.projectId where srs.studentId = " + stuId).addEntity(ProjectVO.class);
        List<ProjectVO> list = sqlQuery.list();

        session.close();

        return list;
    }

    @Override
    public Map<String, Object> findProjectScoreByOptions(Integer stuId, Integer projectId) {
        Session session = sessionFactory.openSession();

        Query query = session.createSQLQuery("select srs.*,stu.stuName,p.projectName,(select empName from emp where emp.empId = srs.empId) as gradeEmpName from " + currentTableName + " as srs left join project as p on p.projectId = srs.projectId left join student as stu on stu.studId = srs.studentId where stu.studId = " + stuId + " and srs.projectId = " + projectId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, Object> map = (Map<String, Object>) query.uniqueResult();

        session.close();

        return map;
    }
}
