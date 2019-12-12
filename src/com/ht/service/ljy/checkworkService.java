package com.ht.service.ljy;

import com.ht.vo.CheckWorkVO;

import java.util.List;

/**
 * Created by JY on 2019/12/11.
 */
public interface checkworkService {


    public List checkwork_list(int page, int limit, int empId);

    public int checkwork_count(int empId);

    public void checkwork_delete(CheckWorkVO checkWorkVO);

    public void checkwork_update(int checkworkId, String remark);



    public void checkwork_add(CheckWorkVO checkWorkVO);

    //查询上级领导的id
    public List checkwork_chairmanId(int empId);

//根据id查询我的审批
    public List checkwork_mychecklist(int page, int limit, int empId);

//    根据id查询我的审批的个数
    public int checkwork_mycheckcount(int empId);


    public void updatestatus(int checkworkId, int status, String checkTime, String remark);

}
