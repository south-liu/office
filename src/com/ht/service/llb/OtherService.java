package com.ht.service.llb;

import com.ht.dao.llb.OtherDao;
import com.ht.vo.StudentClassVO;
import com.ht.vo.StudentFloorVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OtherService {
    @Resource
    private OtherDao otherDao;

    //查询所有班级
    public List<StudentClassVO> studentClassList(){
        return otherDao.studentClassList();
    }

    //调整学生班级
    public void updStudentClass(Integer stuId,Integer classId){
        otherDao.updStudentClass(stuId,classId);
    };

    //调整班级人数
    public void updClassCount(Integer classId,Integer num){
       otherDao.updClassCount(classId,num);
    };

    //调整学生宿舍
    public void updStudentHour(Integer stuId,Integer hourId){
        otherDao.updStudentHour(stuId,hourId);
    };

    //调整宿舍人数
    public void updHuorCount(Integer huorId,Integer num){
        otherDao.updHuorCount(huorId,num);
    };

    public List<StudentFloorVO> allFloor(){
        return otherDao.allFloor();
    }
}
