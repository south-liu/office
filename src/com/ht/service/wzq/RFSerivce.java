package com.ht.service.wzq;

import com.ht.vo.AduitLogVO;

import java.util.List;
import java.util.Map;

/**
 * Created by shkstart on 2019/12/17.
 */
public interface RFSerivce {

    //----------图------------
    //查询有多少个年月份
    public List selzong();
    //查询对应时间的迟到次数
    public List selcount(String sql);
    //查询有多少个年月份
    public List selzong2();
    //----------表------------
    //查询时间(下拉框)
    public List selcheckwork();
    //查询未打卡报表
    public List selcheckwork(String date, int page, int limit);
    //查询总行数
    public Integer selcountcheckwork(String date);





    //查询员工考核报表
    public List selempassessment(String date, int page, int limit);
    //查询员工报表总行数
    public Integer selcountempassessment();
    //查询时间(下拉框)
    public List seladuitlog();
    //查询考核
    public List selempassessmentaduitlog(Integer empId, String date, int page, int limit);
    //查询考核总行数
    public Integer selcountempassessmentaduitlog(Integer empId, String date);
}
