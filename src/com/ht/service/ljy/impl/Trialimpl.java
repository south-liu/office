package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.trialService;
import com.ht.vo.StudentClassVO;
import com.ht.vo.TrialVO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JY on 2019/12/9.
 */
@Service
public class Trialimpl extends ljyDao implements trialService {

    @Override
    public List trial_list() {
        return listbysql("select * from trial");
    }

    @Override
    public int trial_count() {
        return selTotalRow("select count(*) from trial");
    }

    @Override
    public List trial_list(int page, int limit) {
//        return pagelistbysql("select * from trial", page, limit);
        return pagelistbysql("select trl.*,cos.courseName,costy.courseTypeName,e.empName from trial trl left join course cos on trl.CourseId=cos.courseId left join emp e on trl.empid=e.empId left join courseType costy on trl.type=costy.courseTypeId", page, limit);
    }

    @Override
    public void trial_add(TrialVO trialVO) {
        addobject(trialVO);
    }

    @Override
    public void trial_detele(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }

        System.out.println(Arrays.toString(ids));

        for (Integer id : ids) {
            TrialVO o = (TrialVO) dataById(TrialVO.class, id);
            System.out.println(o);
            if (o == null) {
                continue;
            }
            deleteobject(o);
        }

    }

    @Override
    public void trial_update(TrialVO trialVO) {
        updateobject(trialVO);
    }


}
