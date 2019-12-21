package com.ht.service.wzq.impl;

import com.ht.dao.wzq.BaseDao;
import com.ht.service.wzq.RFSerivce;
import com.ht.vo.AduitLogVO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shkstart on 2019/12/17.
 */
@Service
public class RFServiceImpl extends BaseDao implements RFSerivce {
    @Override
    public List selzong() {
        return listBySql2("select date_format(noCardTime,'%Y年%m月') Times from checkwork group by Times");
    }

    @Override
    public List selcount(String sql) {
        return listBySql2(sql);
    }

    @Override
    public List selzong2() {
        return listBySql2("select date_format(noCardTime,'%Y年') Times from checkwork group by Times");
    }





    @Override
    public List selempassessment(String date, int page, int limit) {
        return pageBySql("select e.*, d.deptName, (a.totalScores + score) zongscore from emp e left join dept d on e.deptId = d.deptId left join empAssessmentTotal a on e.empId = a.empId left join (select m.empId, sum(case when date_format(m.auditDate,'%Y年%m月')='" + date + "' then m.score else 0 end) as score from aduitLog m group by m.empId) m on a.empId = m.empId", page, limit);
    }

    @Override
    public Integer selcountempassessment() {
        return selTotalRow("select count(*) from emp");
    }

    @Override
    public List seladuitlog() {
        return listBySql("select date_format(m.auditDate,'%Y年%m月') dates from aduitLog m group by dates desc");
    }

    @Override
    public List selempassessmentaduitlog(Integer empId, String date, int page, int limit) {
        return pageBySql("select a.*, d.aduitName, e.empName from aduitLog a left join aduitModel d on a.aduitModelId = d.aduitModelId left join emp e on a.empId = e.empId where e.empId = "+ empId +" and date_format(a.auditDate,'%Y年%m月') = '"+ date +"'", page, limit);
    }

    @Override
    public Integer selcountempassessmentaduitlog(Integer empId, String date) {
        return selTotalRow("select count(*) from aduitLog a left join aduitModel d on a.aduitModelId = d.aduitModelId left join emp e on a.empId = e.empId where e.empId = "+ empId +" and date_format(a.auditDate,'%Y年%m月') = '"+ date +"'");
    }
}
