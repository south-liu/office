package com.ht.service.zwj.other.student;

import com.ht.service.zwj.other.CommonService;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OStudentService extends CommonService {
    /**
     * 通过学生班级Id查询所有的学生
     *
     * @param classId
     * @return
     */
    public List<Map<String, Object>> findStudentByClassId(Integer classId) {
        Session session = sessionFactory.getCurrentSession();
        List<Map<String, Object>> list = session.createSQLQuery("select s.studId,s.stuName from student as s where clazz = " + classId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }
}
