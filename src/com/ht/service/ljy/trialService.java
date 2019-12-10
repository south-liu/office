package com.ht.service.ljy;

import com.ht.vo.StudentClassVO;
import com.ht.vo.TrialVO;

import java.util.List;

/**
 * Created by JY on 2019/12/9.
 */
public interface trialService {

    //    查询
    public List trial_list();

    public int trial_count();

    //    带分页查询
    public List trial_list(int page, int limit);

    public void trial_add(TrialVO trialVO);

    public void trial_detele(Integer[] ids);

    public void trial_update(TrialVO trialVO);





}
