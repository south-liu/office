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

    //调整学生宿舍
    public void updStudentHour(Integer stuId,Integer hourId){
        executeSQL("update student set huor = "+hourId+" where studId = "+stuId);
    };

    //调整班级人数
    public void updClassCount(Integer classId,Integer num){
        executeSQL("update studentClass set count = count +"+num+" where classId = "+classId);
    };

    //调整宿舍人数
    public void updHuorCount(Integer huorId,Integer num){
        executeSQL("update studentHuor set count = count +"+num+" where hourId = "+huorId);
    };

    //按时间查询我的周报
    public int myWeeklyOfTime(Integer empId,String startTime,String endTime){
        return totalRowBySql("select count(*) from weekly w where w.empId = "+empId+" and w.workDay BETWEEN '"+startTime+"' and '"+endTime+"'");
    };

    //按时间查询我的谈心记录
    public int mychatRecordOfTime(Integer empId,String startTime,String endTime){
        return totalRowBySql("select count(*) from chatRecord w where w.teacher = "+empId+" and w.chatDate BETWEEN '"+startTime+"' and '"+endTime+"'");
    };
}
