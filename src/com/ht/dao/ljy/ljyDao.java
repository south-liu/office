package com.ht.dao.ljy;

import com.ht.vo.TrialVO;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JY on 2019/12/4.
 */
@Transactional
public class ljyDao {
    @Resource
    public SessionFactory sessionFactory;

    private Session takeSession() {
        return sessionFactory.openSession();
    }

    //    分页查询
    public List pagelistbysql(String sql, int currPage, int pageSize) {
        Session session = takeSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setFirstResult((currPage - 1) * pageSize);
        sqlQuery.setMaxResults(pageSize);
        List list = sqlQuery.list();
        session.close();
        return list;
    }

    //    sql查询,无分页
    public List listbysql(String sql) {
        Session session = takeSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.close();
        return list;
    }


    //    查询总行数
    public int selTotalRow(String sql) {
        Session session = takeSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        int i = Integer.parseInt(sqlquery.uniqueResult() + "");
        session.close();
        return i;
    }

    //    查询一个对象
    public Object selOneValue(String sql) {
        Session session = takeSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        Object object =  sqlquery.uniqueResult();
        session.close();
        return object;
    }


    //    删除
    public void deleteobject(Object object) {
        Session session = takeSession();
        session.delete(object);
        session.flush();
        session.close();
    }

    //    sql根据id查询对象
    public Object dataById(Class classs, Integer id) {
        Session session = takeSession();
        Object o = session.get(classs, id);
        session.close();
        return o;
    }


    //    hql根据id查询对象
    public Object selectOneByHql(String hql) {
        Session session = takeSession();
        Object o = session.createQuery(hql).uniqueResult();
        session.close();
        return o;
    }


    //    修改
    public void updateobject(Object object) {
        Session session = takeSession();
        session.update(object);
        session.flush();
        session.close();
    }

    //    添加方法
    public void addobject(Object object) {
        Session session = takeSession();
        session.save(object);
        session.flush();
        session.close();
    }


    //sql增删改三合一
    public int executeSQL(String sql) {
        Session session = takeSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        int i = sqlQuery.executeUpdate();
        session.close();
        return i;
    }
}
