package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.ProjectDao;
import com.ht.vo.ProjectVO;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ProjectDaoImpl implements ProjectDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<ProjectVO> allData(Integer page, Integer limit) {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from " + currentTableName + " order by " + currentTableId + " asc limit ?,?");
        sqlQuery.setInteger(0, (page - 1) * limit);
        sqlQuery.setInteger(1, limit);
        sqlQuery.addEntity(ProjectVO.class);
        List<ProjectVO> list = sqlQuery.list();

        session.close();
        return list;
    }

    @Override
    public List<ProjectVO> allData() {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("select * from " + currentTableName + " order by " + currentTableId + " asc");
        sqlQuery.addEntity(ProjectVO.class);
        List<ProjectVO> list = sqlQuery.list();

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
    public void delete(int projectId) {
        Session session = sessionFactory.openSession();

        ProjectVO project = getProjectById(projectId);
        session.delete(project);
        session.flush();

        session.close();
    }

    @Override
    public int add(ProjectVO projectVO) {

        Session session = sessionFactory.openSession();

        int count = (int) session.save(projectVO);

        session.close();
        return count;
    }

    @Override
    public ProjectVO getProjectByProjectName(String projectName) {
        Session session = sessionFactory.openSession();

        ProjectVO project = (ProjectVO) session.createSQLQuery("select * from " + currentTableName + " where projectName = '" + projectName + "'").addEntity(ProjectVO.class).uniqueResult();

        session.close();
        return project;
    }

    @Override
    public ProjectVO getProjectById(int projectId) {
        Session session = sessionFactory.openSession();

        ProjectVO project = (ProjectVO) session.get(ProjectVO.class, projectId);

        session.close();
        return project;
    }

    @Override
    public void updateProject(ProjectVO project) {
        Session session = sessionFactory.openSession();

        session.update(project);
        session.flush();

        session.close();
    }
}
