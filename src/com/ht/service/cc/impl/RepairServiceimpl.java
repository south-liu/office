package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.RepairService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmpService;
import com.ht.service.llb.IStudentService;
import com.ht.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class RepairServiceimpl extends BaseDao implements RepairService {
    @Resource
    private IStudentService studentService;
    @Resource
    private studentclassService studentclassService;
    @Resource
    private IEmpService empService;
    @Resource
    private IDeptService deptService;

    @Override
    public List selSpage(int currPage, int pageSize) {
        List<Map<String, Object>> list = pageListBySql("select * from equipmentRepair", currPage, pageSize);
        for (Map<String, Object> map : list) {
            int type = (int) map.get("userType");
            if (type == 1) {// 为学生
                int studentId = (int) map.get("student");
                StudentVO student = studentService.findById(studentId);
                System.out.println(student);
                StudentClassVO studentClass = studentclassService.studentclassbyid(student.getClazz());
                map.put("classOrDept", studentClass.getClassName());// 设置申请人所在类别 学生显示班级 老师显示部门
                map.put("proposer", student.getStuName());// 设置申请人名称
            } else if (type == 2) {// 为员工
                int empId = (int) map.get("empId");
                EmpVO emp = empService.findEmpById(empId);
                DeptVO dept = deptService.selById(emp.getDeptId());
                map.put("classOrDept", dept.getDeptName());
                map.put("proposer", emp.getEmpName());// 设置申请人名称
            }
        }
        return list;
    }

    @Override
    public Integer seltotalRepair() {
        return totalRowByHql("select count(*) from EquipmentRepairVO");
    }

    @Override
    public void AddRepair(EquipmentRepairVO equipmentRepairVO) {
        saveObject(equipmentRepairVO);
    }

    @Override
    public void UpdRepair(int equid, int status, String data) {
        executeSQL("update equipmentRepair set status = " + status + ",endTime = '" + data + "' where equipmentId = " + equid);
    }

    @Override
    public void delRepair(String id) {
        executeSQL("delete from equipmentRepair where equipmentId=" + id);
    }
}