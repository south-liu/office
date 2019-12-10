package com.ht.service.llb;

import com.ht.dao.llb.OtherDao;
import com.ht.vo.StudentClassVO;
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
}
