package com.ht.service.zwj.other;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public class CommonService {
    @Resource
    protected SessionFactory sessionFactory;

    /**
     * 查询得到list集合存储的map对象
     * 多用于查询列表、分页
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> findListBySql(String sql) {
        Session session = sessionFactory.getCurrentSession();

        return session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    /**
     * 查询得到LIst集合存储传入的对象
     *
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findListBySql(String sql, Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();

        return session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    /**
     * 查询得到单个对象
     *
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T findObjectBySql(String sql, Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();

        return (T) session.createSQLQuery(sql).addEntity(clazz).uniqueResult();
    }

    /**
     * 查询得到单个的map集合对象
     *
     * @param sql
     * @return
     */
    public Map<String, Object> findObjectMapBySql(String sql) {
        Session session = sessionFactory.getCurrentSession();

        return (Map<String, Object>) session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    /**
     * 查询得到数值
     * 多用于计算总数 单个返回值是int
     *
     * @param sql
     * @return
     */
    public Long findNumberBySql(String sql) {
        Session session = sessionFactory.getCurrentSession();
        Number number = (Number) session.createSQLQuery(sql).uniqueResult();

        return number != null ? (Long) number.longValue() : null;
    }
}
