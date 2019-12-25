package com.ht.dao.wzq;

import java.util.List;
import java.util.Queue;
import javax.annotation.Resource;
import org.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shkstart on 2019/10/21.
 */

/*
* 1、BaseDao为业务处理（逻辑）层，封装的是常用方法，用于减少重复代码和使代码重复使用。
* */
@Transactional
public class BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }


    /**
     * 根据实际需求合理使用HQL、SQL，一般使用SQL查询，HQL增、删、改。
     * @param hql
     * @return
     */
    //查询列表
    public List listByHql(String hql){
        Session session = getSession();
        Query sqlQuery = session.createQuery(hql);
        List list = sqlQuery.list();
        session.close();
        return list;
    }

    //普通sql查询
    public List listBySql2(String sql){
        Session session = getSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        List list = sqlquery.list();
        session.close();
        return list;
    }

    //HQL分页(适用范围：分页查询一个表的所有字段)
    public List pageByHql(String hql, int currPage, int pageSize){
        Session session = getSession();
        Query query = session.createQuery(hql);
        query.setFirstResult((currPage - 1) * pageSize);  //开始行数
        query.setMaxResults(pageSize);  //每页行数(每页查几行)
        session.close();
        return query.list();
    }

    //SQL查询列表(适用范围：连接多个表、筛选列时使用。)
    public List listBySql(String sql){
        Session session = getSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        sqlquery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);  //把结果变形为List<Map>（键值对）形式，以列名为键，以列值为值。在页面显示需要使用大写的字段名！
        /**存储方式模拟：（一条数据，就是一个Map。）
         * Map map = new Map();
         * map.put("EMPNO", 1001);
         * map.put("ENAME", "张三");
         * map.put("JOB", "程序猿");
         * ...
         *map.put("DNAME", "开发班")
         *map.put("LOC", "开发好")
         */
        List list = sqlquery.list();
        session.close();
        return list;  //没变形之前返回的list是一个Object数组。
    }

    //SQL查询列表(适用范围：连接多个表、筛选列时使用。)
    public Object listBySql3(String sql){
        Session session = getSession();
        Object o = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).uniqueResult();  //把结果变形为List<Map>（键值对）形式，以列名为键，以列值为值。在页面显示需要使用大写的字段名！
        /**存储方式模拟：（一条数据，就是一个Map。）
         * Map map = new Map();
         * map.put("EMPNO", 1001);
         * map.put("ENAME", "张三");
         * map.put("JOB", "程序猿");
         * ...
         *map.put("DNAME", "开发班")
         *map.put("LOC", "开发好")
         */
        session.close();
        return o;  //没变形之前返回的list是一个Object数组。
    }

    //SQL分页查询(适用范围：一个表或连接多个表、筛选列时使用。)
    public List pageBySql(String sql, int currPage, int pageSize){
        Session session = getSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        sqlquery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);  //把结果变形为List<Map>（键值对）形式，以列名为键，以列值为值。在页面显示需要使用大写的字段名！
        /**存储方式模拟：（一条数据，就是一个Map。）
         * Map map = new Map();
         * map.put("EMPNO", 1001);
         * map.put("ENAME", "张三");
         * map.put("JOB", "程序猿");
         * ...
         *map.put("DNAME", "开发班")
         *map.put("LOC", "开发好")
         */
        sqlquery.setFirstResult((currPage - 1) * pageSize);  //开始行数
        sqlquery.setMaxResults(pageSize);  //每页行数(每页查几行)
        List list = sqlquery.list();
        session.close();
        return list;  //没变形之前返回的list是一个Object数组。
    }

    //三合一SQL执行（增、删、改）
    public void executeSQL(String sql){
        Session session = getSession();
        SQLQuery sqlquery =session.createSQLQuery(sql);
        sqlquery.executeUpdate();
        session.close();
    }

    //SQL查询总行数
    //select count(*) from newemp
    public int selTotalRow(String sql){
        Session session = getSession();
        SQLQuery sqlquery = session.createSQLQuery(sql);
        int i = Integer.parseInt(sqlquery.uniqueResult() + "");
        session.close();
        return i;
    }

    //添加
    public void addObject(Object obj){
        Session session = getSession();
        session.save(obj);
        session.flush();
        session.close();
    }

    //查询对象(单个对象)
    public Object getObject(Class clazz, Integer id){
        Session session = getSession();
        Object o = session.get(clazz, id);
        session.close();
        return o;
    }

    //修改
    public void updObject(Object obj){
        Session session = getSession();
        session.update(obj);
        session.flush();
        session.close();
    }

    //删除
    public void delObject(Object obj){
        Session session = getSession();
        session.delete(obj);
        session.flush();
        session.close();
    }
}
