package com.ht.dao;

import org.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    /**
     * hql单个查询
     * @param hql
     * @return
     */
    public Object selectOneByHql(String hql){
        Session session = getSession();
        Object o = session.createQuery(hql).uniqueResult();
        session.close();
        return o;
    }

    /**
     * 保存
     * @param o
     */
    public void saveObject(Object o){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(o);
        transaction.commit();
        session.close();
    }

    /**
     * 更新
     * @param o
     */
    public void update(Object o){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(o);
        transaction.commit();
        session.close();
    }

    /**
     * 删除
     * @param o
     */
    public void delete(Object o){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    /**
     * hql查询单个对象
     * @param hql
     * @return
     */
    public Object findOneByHql(String hql){
        Session session = getSession();
        Object o = session.createQuery(hql).uniqueResult();
        session.close();
        return o;
    }

    /**
     * sal查询单个
     * ALIAS_TO_ENTITY_MAP把结果转换成List<map>
     * @param sql
     * @return
     */
    public Object findOneBySql(String sql){
        Session session = getSession();
        Object o = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).uniqueResult();
        session.close();
        return o;
    }


    /**
     * hal查询所有列表
     * @param hql
     * @return
     */
    public List findAllByHql(String hql){
        Session session = getSession();
        List list = session.createQuery(hql).list();
        session.close();
        return list;
    }

    /**
     * sal查询所有列表
     * ALIAS_TO_ENTITY_MAP把结果转换成List<map>
     * @param sql
     * @return
     */
    public List findAllBySql(String sql){
        Session session = getSession();
        List list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
        session.close();
        return list;
    }

    /**
     * hql分页查询所有列表
     * @param hql
     * @param page
     * @param limit
     * @return
     */
    public List pageListByHql(String hql,Integer page,Integer limit){
        Session session = getSession();
        List list = session.createQuery(hql).setFirstResult((page - 1) * limit).setMaxResults(limit).list();
        session.close();
        return list;
    }

    /**
     * sql分页查询所有列表
     * ALIAS_TO_ENTITY_MAP把结果转换成List<map>
     * @param sql
     * @param page
     * @param limit
     * @return
     */
    public List pageListBySql(String sql,Integer page,Integer limit){
        Session session = getSession();
        List list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                .setFirstResult((page - 1) * limit).setMaxResults(limit).list();
        session.close();
        return list;
    }

    /**
     * hql查询总条数
     * @param hql
     * @return
     */
    public int totalRowByHql(String hql){
        Session session = getSession();
        Number num = (Number) session.createQuery(hql).uniqueResult();
        session.close();
        return num.intValue();
    }

    /**
     * sql查询总条数
     * @param sql
     * @return
     */
    public int totalRowBySql(String sql){
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        int i = Integer.parseInt(sqlQuery.uniqueResult().toString());
        session.close();
        return i;
    }

    /**
     * 执行sql语句
     * @param sql
     * @return
     */
    public int executeSQL(String sql){
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        int i = sqlQuery.executeUpdate();
        session.close();
        return i;
    }

}
