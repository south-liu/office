package com.ht.service.zwj.other.emp;

import com.ht.service.zwj.other.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("oEmpService")
public class OEmpService extends CommonService {
    /**
     * 查询 职称是（班主任、任课老师） 的员工
     *
     * @return
     */
    public List<Map<String, Object>> queryPortionEmp() {
        List<Map<String, Object>> list = this.findListBySql("select empId,empName,postId,postName from emp where postName in('班主任','授课老师')");
        return list;
    }
}
