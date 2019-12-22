package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.HoliEmpService;
import com.ht.vo.HolidayVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpHoliserviceImpl extends BaseDao implements HoliEmpService {

    @Override
    public List selSpageEmpholi(int currPage, int pageSize, int mo) {
        //where date_format(`startTime`,'%m')="+mo+"
        return pageListBySql("select e.empId as Id,count(holidayId) as sum,empName,sum(holidayDay) as totalitySum,sum(right(left(dayStr,char_length(dayStr)-2),(char_length(left(dayStr,char_length(dayStr)-2)))-instr(dayStr,'天'))) as hour from holiday hs left join emp e on hs.empId=e.empId  where date_format(`startTime`,'%m')="+mo+" group by hs.empId",currPage,pageSize);
    }

    @Override
    public List<HolidayVO> pageListEmpholiWhere(int currPage, int pageSize, String empName, String month) {
        String whereN = null;
        String wh = null;
        String an = null;
        String whereM = null;
        String sql = null;

        if (month!=null && month!="all" && empName.equals("")){
            wh =" where";
            whereM = " date_format(`startTime`,'%m')='"+month+"'";
            sql = "select e.empId as Id,count(holidayId) as sum,empName,sum(holidayDay) as totalitySum,sum(right(left(dayStr,char_length(dayStr)-2),(char_length(left(dayStr,char_length(dayStr)-2)))-instr(dayStr,'天'))) as hour from holiday hs left join emp e on hs.empId=e.empId "+wh+whereM+"group by hs.empId";
        } else if (month == "all" &&  empName.equals("")){
            sql = "select e.empId as Id,count(holidayId) as sum,empName,sum(holidayDay) as totalitySum,sum(right(left(dayStr,char_length(dayStr)-2),(char_length(left(dayStr,char_length(dayStr)-2)))-instr(dayStr,'天'))) as hour from holiday hs left join emp e on hs.empId=e.empId group by hs.empId";
        } else if (month == "all" &&  empName!=""){
            wh =" where";
            whereN =" e.empName like'%"+empName+"%'";
            sql = "select e.empId as Id,count(holidayId) as sum,empName,sum(holidayDay) as totalitySum,sum(right(left(dayStr,char_length(dayStr)-2),(char_length(left(dayStr,char_length(dayStr)-2)))-instr(dayStr,'天'))) as hour from holiday hs left join emp e on hs.empId=e.empId "+wh+whereN+"group by hs.empId";
        } else if (empName!="" && month!="all"){
            wh =" where";
            whereN =" e.empName like'%"+empName+"%'";
            an= "and";
            whereM = " date_format(`startTime`,'%m')='"+month+"'";
            sql = "select e.empId as Id,count(holidayId) as sum,empName,sum(holidayDay) as totalitySum,sum(right(left(dayStr,char_length(dayStr)-2),(char_length(left(dayStr,char_length(dayStr)-2)))-instr(dayStr,'天'))) as hour from holiday hs left join emp e on hs.empId=e.empId "+wh+whereM+an+whereN+"group by hs.empId";
        }
        return pageListBySql(sql,currPage,pageSize);

    }

    @Override
    public List selSpageEmpholibyid(int currPage, int pageSize, Integer empId, Integer sum, String month) {
        String sql = null;
        if (month.equals("all")){
            sql = "select e.empName as empName,hs.* from holiday hs left join emp e on hs.empId=e.empId where hs.empId=" + empId;
        }else {
            sql = "select e.empName,hs.* from holiday hs left join emp e on hs.empId=e.empId where hs.empId=" + empId + " and date_format(`startTime`,'%m')="+month;
        }//        return pageListBySql("select stu.stuName,hs.* from holidayStudent hs left join student stu on hs.studentId=stu.studId where studentId="+studentId+" and date_format(`startTime`,'%m')="+month,currPage,sum);
        return pageListBySql(sql,currPage,sum);
    }



    @Override
    public Integer seltotalEmpholiWhere(String empName, String month) {
        return totalRowBySql("select count(e.empName) from holiday hs left join emp e on e.empName='"+empName+"'");
    }
}
