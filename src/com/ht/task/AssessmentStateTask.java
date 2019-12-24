package com.ht.task;

import com.ht.service.zwj.AssessmentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

// 定时任务：自动查询需要更改的考评并更改考评的状态
@Component
public class AssessmentStateTask {
    @Resource
    private AssessmentService assessmentService;

    // 将所有未完成的考评并且已经过了结束时间的考评，更改为结束状态
    @Scheduled(cron = "0/5 * * * * ?")// 每五秒执行一次
    public void changeAssessmentState() {
        /*
        1.先查询出未结束的所有考评
        2.将未结束的考评循环更改状态
         */
        List<Map<String, Object>> unfinishedAssessmentList = assessmentService.queryUnfinishedAssessment();
        if (unfinishedAssessmentList != null && unfinishedAssessmentList.size() > 0) {
            for (Map<String, Object> assessment : unfinishedAssessmentList) {
                assessmentService.changeAssessmentState((int) assessment.get("assessmentId"), 1);// 将考评状态更改为已完成
            }
        }
    }
}
