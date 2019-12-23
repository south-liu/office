package com.ht.dao.zwj;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class DaoUtils {
    @Resource
    private SessionFactory sessionFactory;

    // 获取session
    private Session getSession() {
        return sessionFactory.openSession();
    }

    // 关闭session
    private void closeSession(Session session) {
        session.close();
    }

    /**
     * 通过SQL语句查询记录
     *
     * @param sql
     * @param resultTransformer Transformers.TO_LIST：转换成:List<List>
     *                          Transformers.aliasToBean()：转换成:List<Entity>
     *                          Transformers.ALIAS_TO_ENTITY_MAP：转换成:List<Map>
     * @return
     */
    public List queryAllBySql(String sql, ResultTransformer resultTransformer) {
        Session session = this.getSession();

        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(resultTransformer);
        List list = query.list();

        this.closeSession(session);
        return list;
    }

    /**
     * 通过SQL语句返回单个实体
     *
     * @param sql
     * @param resultTransformer
     * @return
     */
    public Object queryEntityBySql(String sql, ResultTransformer resultTransformer) {
        Session session = getSession();

        Query query = session.createSQLQuery(sql);
        Object o = query.setResultTransformer(resultTransformer).uniqueResult();

        closeSession(session);
        return o;
    }

    public long queryTotalNumber(String sql) {
        Session session = getSession();

        Query query = session.createSQLQuery(sql);
        Number number = (Number) query.uniqueResult();

        closeSession(session);
        return number.longValue();
    }

    /**
     * 通过SQL语句添加、修改、删除对象
     *
     * @param sql
     * @return 返回受影响的行数
     */
    public int updateBySql(String sql) {
        Session session = getSession();

        Query query = session.createSQLQuery(sql);
        int i = query.executeUpdate();

        closeSession(session);
        return i;
    }

    /**
     * 通过HQL方式查询单个实体
     *
     * @param clazz
     * @param s
     * @param <T>
     * @return
     */
    public <T> T queryEntityByHql(Class<T> clazz, Serializable s) {
        Session session = getSession();

        T o = (T) session.get(clazz, s);

        closeSession(session);
        return o;
    }

    /**
     * 添加实体
     *
     * @param o
     * @return
     */
    public long saveEntity(Object o) {
        Session session = getSession();

        Serializable serializable = session.save(o);
        session.flush();
        closeSession(session);

        System.out.println("save：" + serializable);
        // 如果是数值型主键则转换为long
        if (serializable instanceof Number) {
            return ((Number) serializable).longValue();
        }
        return serializable != null ? 1 : 0;
    }

    /**
     * 更新实体
     *
     * @param o
     */
    public void updateEntity(Object o) {
        Session session = getSession();

        session.update(o);
        session.flush();

        closeSession(session);
    }

    /**
     * 删除实体
     *
     * @param o
     */
    public void deleteEntity(Object o) {
        Session session = getSession();

        session.delete(o);
        session.flush();

        closeSession(session);
    }
}
