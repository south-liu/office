package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.HoliStuService;
import com.ht.vo.EmpVO;
import com.ht.vo.HolidayStudent;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HolServiceImpl extends BaseDao implements HoliStuService {
    @Override
    public List selSpage(int currPage, int pageSize,int mon) {
        return pageListBySql("select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId where date_format(`startTime`,'%m')="+mon+" group by hs.studentId",currPage,pageSize);
    }

    @Override
    public List<HolidayStudent> pageListWhere(int page, int limit, String stuName,String month) {
        String whereN = null;
        String wh = null;
        String an = null;
        String whereM = null;
        String sql = null;

        if (month!=null && month!="all" && stuName.equals("")){
            wh =" where";
            whereM = " date_format(`startTime`,'%m')='"+month+"'";
            sql = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId"+wh+whereM+"group by hs.studentId";
        } else if (month == "all" &&  stuName.equals("")){
            sql = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId group by hs.studentId";
        } else if (month == "all" &&  stuName!=""){
            wh =" where";
            whereN =" stu.stuName like'%"+stuName+"%'";
            sql = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId "+wh+whereN+"group by hs.studentId";
        } else if (stuName!="" && month!="all"){
            wh =" where";
            whereN =" stu.stuName like'%"+stuName+"%'";
            an= "and";
            whereM = " date_format(`startTime`,'%m')='"+month+"'";
            sql = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId"+wh+whereM+an+whereN+"group by hs.studentId";
        }

        System.out.println("1111"+sql);

//        String sql11 = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId  where stu.stuName like'%"+stuName+"%' "+whereM+" group by hs.studentId";
//        String sql = "select studentId,count(holidayId) sum,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId"+wh+whereN+whereM+"group by hs.studentId";
        return pageListBySql(sql,page,limit);
    }


    @Override
    public Integer seltotalholWhere(String stuName,String month) {
        return totalRowBySql("select count(stu.stuName) from holidayStudent hs left join student stu on stu.stuName='"+stuName+"'");
    }

    @Override
    public List selSpageholi(int currPage, int pageSize,Integer studentId,Integer sum,String month) {
        String sql = null;
        if (month.equals("all")){
            sql = "select stu.stuName,hs.* from holidayStudent hs left join student stu on hs.studentId=stu.studId where studentId=" + studentId;
        }else {
            sql = "select stu.stuName,hs.* from holidayStudent hs left join student stu on hs.studentId=stu.studId where studentId=" + studentId + " and date_format(`startTime`,'%m')="+month;
        }//        return pageListBySql("select stu.stuName,hs.* from holidayStudent hs left join student stu on hs.studentId=stu.studId where studentId="+studentId+" and date_format(`startTime`,'%m')="+month,currPage,sum);
        return pageListBySql(sql,currPage,sum);

    }

}
