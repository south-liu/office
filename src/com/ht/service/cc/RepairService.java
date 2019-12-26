package com.ht.service.cc;

import com.ht.vo.*;

import java.util.List;

public interface RepairService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //查询总行数
    public Integer seltotalRepair();
    //添加方法
    public void AddRepair(EquipmentRepairVO equipmentRepairVO);
    //修改方法
    public void UpdRepair(int equid, int status, String data);
    //删除方法
    public void delRepair(String id);
}
