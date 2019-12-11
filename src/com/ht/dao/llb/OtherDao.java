package com.ht.dao.llb;

import com.ht.dao.BaseDao;
import com.ht.vo.StudentClassVO;
import com.ht.vo.StudentFloorVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class OtherDao extends BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
    }

    //查询所有班级
    public List<StudentClassVO> studentClassList(){
        return findAllByHql("from StudentClassVO");
    }

    //查询所有楼栋
    public List<StudentFloorVO> allFloor(){
        return findAllByHql("from StudentFloorVO");
    }

    //调整学生班级
    public void updStudentClass(Integer stuId,Integer classId){
        executeSQL("update student set clazz = "+classId+" where studId = "+stuId);
    };

    //调整班级人数
    public void updClassCount(Integer classId,Integer num){
        executeSQL("update studentClass set count = count +"+num+" where classId = "+classId);
    };
}
