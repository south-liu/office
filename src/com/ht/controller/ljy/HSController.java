package com.ht.controller.ljy;

import com.ht.service.ljy.hstudentService;
import com.ht.utils.date.DatesUtil;
import com.ht.vo.EmpVO;
import com.ht.vo.HolidayStudent;
import com.ht.vo.HolidayVO;
import com.ht.vo.StudentVO;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by JY on 2019/12/13.
 */

@Controller
@RequestMapping("/hstudent")
public class HSController {

    @Resource
    hstudentService hstudentService;

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;


    //    去上传流程定义页面
    @RequestMapping("/goto_hs_uploadProcess")
    public String goto_hs_uploadProcess() {
        System.out.println("去流程上传页面");
        return "ljy/holiday_stu/hs_upldProDefin";
    }


    //    去填写请假单页面
    @RequestMapping("/goto_hs_apply")
    public String goto_hs_apply() {
        System.out.println("去流程上传页面");
        return "ljy/holiday_stu/hs_apply_add";
    }


    //去我的申请单界面
    @RequestMapping("/goto_hs_myapply_list")
    public String goto_hs_myapply_list() {
        System.out.println("去我的申请页面!");
        return "ljy/holiday_stu/hs_myapplylist";
    }


    //去我的任务界面
    @RequestMapping("/goto_hs_mytask_list")
    public String goto_hs_mytask_list() {
        System.out.println("去我的任务页面!");
//        return "ljy/holiday_stu/hs_mytask_list";
        return "redirect:hs_myTask_list";
    }


    //去流程定义列表
    @RequestMapping("/goto_hs_listProDefin")
    public String goto_hs_listProDefin() {
        System.out.println("去流程定义列表页面!");
        return "ljy/holiday_stu/hs_listProDefin";
    }


    // 流程定义上传
    @RequestMapping("/hs_uploadProcess")
    public String hs_uploadProcess(MultipartFile uploadfile) {
        try {
            //创建临时file对象
            File file = File.createTempFile("tmp", null);
            //把MultipartFile对象转换成java.io.FileUI对象
            uploadfile.transferTo(file);
            //部署Zip文件  将流程上传到Activiti框架
            Deployment deployment = repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(file))).deploy();
            System.out.println("部署ID " + deployment.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:progressDefine_list";
    }

    //    查看所有流程(查询流程定义表)
    @RequestMapping("/progressDefine_list")
    public String progressDefine(Model model) {
//        创建一个定义语句
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
//        倒序显示
        query.orderByProcessDefinitionVersion().desc();
//        使用list接收
        List<ProcessDefinition> list = query.list();
        System.out.println("所有流程数据：" + list.toString());
        model.addAttribute("processList", list);
        return "ljy/holiday_stu/hs_listProDefin";
    }


    //查看所有流程：删除流程
    @RequestMapping("/delProgressDefine")
    public String delProgressDefine(String id) {
//       根据部署id进行删除
        repositoryService.deleteDeployment(id, true);
//        转发到查询界面
        return "redirect:progressDefine";
    }

    //   查看所有流程： 查看流程图片
    @RequestMapping("/viewProcessImage")
    public String viewProcessImage(String did, String imageName, HttpServletResponse resp) {
//        获取到上传的资源
        InputStream in = repositoryService.getResourceAsStream(did, imageName);
        try {
            OutputStream out = resp.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len = in.read(b)) != -1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看所有流程：下载流程图
     */
    @RequestMapping("/toExport")
    public String toExport(String id, HttpServletResponse resp) {
        try {

            //设置response对象的头参数，attachment就是附件，filename=文件名称
            resp.setHeader("Content-disposition", "attachment;filename=" + id + ".zip");
            //下载的文件类型是zip文件
            resp.setContentType("application/x-zip-compressed");

            //----------------------------------------------------------------------------

            //流程定义对象
            ProcessDefinition processDefinition = repositoryService
                    .createProcessDefinitionQuery()
                    .processDefinitionId(id).singleResult();
            //部署id
            String deploymentId = processDefinition.getDeploymentId();

            //bpmn资源文件名称
            String resourceName_bpmn = processDefinition.getResourceName();
            //bpmn资源文件输入流
            InputStream inputStream_bpmn = repositoryService.getResourceAsStream(deploymentId, resourceName_bpmn);
            //png文件名称
            String resourceName_png = processDefinition.getDiagramResourceName();
            //png资源文件输入流
            InputStream inputStream_png = repositoryService.getResourceAsStream(deploymentId, resourceName_png);

            //------创建输出流，绑定到response对象-------------------------------------------------------
            OutputStream out = resp.getOutputStream();
            //创建ZIP输出对象，绑定到输出流
            ZipOutputStream zipo = new ZipOutputStream(out);

            //流复制
            byte[] b = new byte[1024];
            int len = -1;

            //定义zip压缩包中的文件对象（zip实体）
            ZipEntry ze = new ZipEntry(resourceName_bpmn);
            //把创建的实体对象放到压缩包中
            zipo.putNextEntry(ze);
            //文件内容拷贝
            while ((len = inputStream_bpmn.read(b, 0, 1024)) != -1) {
                zipo.write(b, 0, b.length);
            }
            zipo.closeEntry();
            //---------------
            ZipEntry ze1 = new ZipEntry(resourceName_png);
            zipo.putNextEntry(ze1);
            while ((len = inputStream_png.read(b, 0, 1024)) != -1) {
                zipo.write(b, 0, b.length);
            }
            //关闭流
            inputStream_bpmn.close();
            inputStream_png.close();
            zipo.flush();
            zipo.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    填写请假单
    @ResponseBody
    @RequestMapping("/my_apply_add")
    public String hs_apply(HolidayStudent holidayStudent, HttpSession session) {
        StudentVO studentVO = (StudentVO) session.getAttribute("student");
        holidayStudent.setStatus("0");
//     ----------------------------   改变为学生ID      ---------------------------------
        holidayStudent.setStudentId(studentVO.getStudId());
//        设置一个title值为在流程图点击空白时的ID
        holidayStudent.setTitle("holidaystu");
        hstudentService.hs_apply_add(holidayStudent);

        //    设置流程实例变量集合，对应数据库中的variable
        Map<String, Object> variables = new HashMap<>();
        variables.put("studentId", holidayStudent.getStudentId());
        variables.put("holidayDay", holidayStudent.getHolidayDay());
        variables.put("holidayId", holidayStudent.getHolidayId());
//        第一个执行人
//        variables.put("teacher", hstudentService.hs_assignee_teacher(studentVO.getStudId()));
        System.out.println("ID:" + studentVO.getStudId());

//    启动实例（通过流程定义的key来启动一个实例，getTitle对应表act_re_procdef中的key,点击空白时的id）
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(holidayStudent.getTitle(), variables);
        System.out.println("流程实例id：" + processInstance.getId());
        //为什么一开始就需要获取任务：填写申请也是任务
        //根据流程实例ID获取当前实例正在执行的任务,
        //对应数据库中的act_ru_task表，为什么返回唯一结果集，因为会有多个流程实例。为什么需要获取id查询：预防并发情况
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();
        System.out.println("任务ID：" + task.getId());
        //完成任务(通过任务ID完成该任务)，传递参数，存入数据库
        Map<String, Object> variable = new HashMap<>();
//        第一任申请人，应该在填写完申请之后设置
        variable.put("teacher", hstudentService.hs_assignee_teacher(studentVO.getStudId()));
        taskService.complete(task.getId(), variable);
        return "success";
    }


    //    查询我的请假单界面
    @ResponseBody
    @RequestMapping("/hs_myapply_list")
    public Map hs_myapply_list(HttpSession session, int page, int limit) {
//        --------------------------------改为学生ID  --------------------------------------------
        StudentVO studentVO = (StudentVO) session.getAttribute("student");
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("data", hstudentService.hs_apply_list(studentVO.getStudId(), page, limit));
        map.put("count", hstudentService.hs_apply_count(studentVO.getStudId()));

        return map;
    }


    //我的请假单：查看办理进度，红色框框
    @RequestMapping("/hs_myapply_lookapplyImg")
    public String lookTaskImg(String holidayId, Model model, String instId) {
        String processInstanceId = "";//流程实例ID
        if (holidayId != null && !"".equals(holidayId)) {
            //通过单据id查找实例对象
//            查询的是act_hi_varinst表，出来的对象是其中的一条数据
            HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayId", Integer.parseInt(holidayId)).singleResult();
            //通过历史流程变量查询变量对象(获取流程实例ID)
            processInstanceId = hvi.getProcessInstanceId();
        }

//        我的任务中查看进度（最终是通过proc_inst_Id来查询的所以可以省去一个步骤）
        if (instId != null && !"".equals(instId)) {
            processInstanceId = instId;
        }

        //通过上面的实流程例ID，获取hi_procinst历史任务实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        if (historicProcessInstance != null) {
            //获取流程定义信息
//            通过上面的流程实例ID信息，查询到流程定义ID
            ProcessDefinition pd = repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
            // 获取流程定义的实体（包含了流程中的任务节点信息，连线信息）
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) pd;
            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            //hi_activi_inst获取审批人的名字
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            // 已经激活的节点ID集合
            //激活的节点（1.任务已经完成；2.任务已经开始，但还未结束）
            List mapList = new ArrayList();
            //获取已经激活的节点ID
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                //getActivityId方法获取已经激活的节点id
                ActivityImpl activityImpl = processDefinition.findActivity(activityInstance.getActivityId());
                //获取当前节点在图片中的坐标位置，左上角坐标及长宽
                int x = activityImpl.getX();
                int y = activityImpl.getY();
                int height = activityImpl.getHeight();
                int width = activityImpl.getWidth();
//                每个节点都是一个map
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("x", x);
                map.put("y", y);
                map.put("height", height);
                map.put("width", width);
                mapList.add(map);
                //        model.addAttribute("hstudentService",hstudentService);
//        <td> ${hstudentService.hs_assigneeName(t.userId)}</td>
            }
            model.addAttribute("pd", pd);
            model.addAttribute("mapList", mapList);
        }

        return "ljy/holiday_stu/hs_myapply_lookimg";
    }


    //    我的请假单:查看批注
    @RequestMapping("/hs_myapply_lookComment")
    public String lookComment(String holidayId, Model model) {
        //通过holidayId查询历史变量实例对象act_hi_varinst
        System.out.println("holidayId:的值：" + holidayId);
//里面类型不一致，必须得转换成Integer
        HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayId", Integer.parseInt(holidayId)).singleResult();
        System.out.println("hvi中的值：" + hvi.getProcessInstanceId());
        //获取流程实例id （查询历史批注），adult中审批时存入了一些数据在表中act_hi_comment
        List<Comment> commentList = taskService.getProcessInstanceComments(hvi.getProcessInstanceId());
//        为了显示申请人的名字
        List list = new ArrayList();
        for (int i = 0; i < commentList.size(); i++) {
            Map map = new HashMap();
            map.put("id", commentList.get(i).getId());
            map.put("fullMessage", commentList.get(i).getFullMessage());
            map.put("time", commentList.get(i).getTime());
            map.put("userId", hstudentService.hs_assigneeName(commentList.get(i).getUserId()));
            list.add(map);
//            取到ID
            System.out.println("员工姓名：" + hstudentService.hs_assigneeName(commentList.get(i).getUserId()));
        }
        model.addAttribute("commentList", list);
        return "ljy/holiday_stu/hs_myapply_comment";
    }


    //    教师端：我的任务
    @RequestMapping("/hs_myTask_list")
    public String myTask(HttpSession session, Model model) {
//        查询到用户登录时的名字
//        String actionid=session.getAttribute("actionid").toString();
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
//        StudentVO studentVO = (StudentVO) session.getAttribute("student");
//       申请人的动态姓名
//        String stuName = studentVO.getStuName();
//      根据名字去查询任务列表(act_ru_task:proc_inst_id,proc_def_id,执行人)
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(empVO.getEmpId().toString()).list();

        List list = new ArrayList();
        for (int i = 0; i < taskList.size(); i++) {
            Map map = new HashMap();
            map.put("id", taskList.get(i).getId());
            map.put("processInstanceId", taskList.get(i).getProcessInstanceId());


            map.put("processDefinitionId", taskList.get(i).getProcessDefinitionId());
            map.put("name", taskList.get(i).getName());
            map.put("createTime", taskList.get(i).getCreateTime());
            map.put("assignee", hstudentService.hs_assigneeName(taskList.get(i).getAssignee()));
            list.add(map);
//            取到ID
            System.out.println("员工姓名：" + hstudentService.hs_assigneeName(taskList.get(i).getAssignee()));
        }

        System.out.println("taskList:" + taskList.toString());
        model.addAttribute("taskList", list);
        System.out.println("我的任务");
        return "ljy/holiday_stu/hs_mytask_list";
    }


    //查看详情改为我的任务
    @ResponseBody
    @RequestMapping("/xiangqing")
    public Map xiangqing(HttpSession session, String taskId) {
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(empVO.getEmpId().toString()).list();


        int holidayId = Integer.parseInt(taskService.getVariable(taskId, "holidayId").toString());
        Map map = new HashMap();
        map.put("msg", 0);
        map.put("code", 0);
        map.put("count", hstudentService.hs_byid_lists_count(holidayId));
        map.put("data", hstudentService.hs_byid_list(holidayId));
        return map;
    }

    //    我的任务中查看详情审核意见
    @RequestMapping("/hs_mytask_taskaudit")
    public String taskDetail(String taskId, String instId, Model model) {
        //根据流程实例ID查询流程实例
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instId).singleResult();
        //根据从页面中传来的任务ID（Act_ru_task中查出的） 查询任务实例（act_hi_taskinst）
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();


        //获取历史审批信息(act_hi_comment)
        List<Comment> commentList = taskService.getProcessInstanceComments(instId);
        List list = new ArrayList();
        for (int i = 0; i < commentList.size(); i++) {
            Map map = new HashMap();
            map.put("id", commentList.get(i).getId());
            map.put("fullMessage", commentList.get(i).getFullMessage());
            map.put("time", commentList.get(i).getTime());
            map.put("userId", hstudentService.hs_assigneeName(commentList.get(i).getUserId()));
            list.add(map);
//            取到ID
            System.out.println("员工姓名：" + hstudentService.hs_assigneeName(commentList.get(i).getUserId()));
        }
        //获取流程定义id
        String processDefineId = task.getProcessDefinitionId();
        //查询流程定义实体对象
        ProcessDefinitionEntity pdentity = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processDefineId);
        //获取当前活动id（act_hi_actiinst）
        String activeId = pi.getActivityId();
        System.out.println("当前活动ID " + activeId);
        //获取当前活动(usertask2),通过上面查询到的实体对象，和活动id
        ActivityImpl impl = pdentity.findActivity(activeId);
        //获取当前活动的连线，下一个执行人是谁，由谁来审批(此List不能直接在页面上遍历)
        List<PvmTransition> pvmlist = impl.getOutgoingTransitions();
        List plist = new ArrayList();
        for (PvmTransition pvm : pvmlist) {
            Map map = new HashMap();
            if (pvm.getProperty("name") == null) {//如果没有设置连线名称，给一个默认的选项
                map.put("id", 0);
                map.put("name", "审批");
            } else {
                map.put("id", pvm.getId());
                map.put("name", pvm.getProperty("name"));
            }
            plist.add(map);
        }


        //获取jobID，
        int holidayId = Integer.parseInt(taskService.getVariable(taskId, "holidayId").toString());
        //根据单据ID查询对象，为了输出里面的信息
        Map holidayStudent = (Map) hstudentService.hs_byid_lists(holidayId).get(0);


        String startTime = (String) holidayStudent.get("startTime");
        String endTime = (String) holidayStudent.get("endTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd 天 HH 小时");
        try {
            Date date = new Date();
            date.setTime(DatesUtil.dateDiff(simpleDateFormat.parse(startTime), simpleDateFormat.parse(endTime))-(3600*8000));
            String format = simpleDateFormat2.format(date);
            System.out.println("sadasdas:"+format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("holidayStudent:" + holidayStudent.toString());


//        JobsVo job = services.seljobbyid(jobId);

        model.addAttribute("taskId", taskId);
        model.addAttribute("commentList", list);
        model.addAttribute("plist", plist);
        model.addAttribute("holidayStudent", holidayStudent);
        System.out.println("holidayStudent:" + holidayStudent.toString());
        return "ljy/holiday_stu/hs_mytask_audit";
    }

    //    提交审批
    @RequestMapping("/hs_mytask_complete")
    public String hs_mytask_complete(Integer studentId, Integer holidayId, String taskId, String flow, String comment, HttpSession session) {
        //根据task任务ID得到任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //通过任务对象获取流程实例ID(act_ru_task里面有proc_inst_id)
        String processInstId = task.getProcessInstanceId();
        //根据单据ID查询单据对象,为了下面的修改信息

        HolidayStudent holidayStudentVo = (HolidayStudent) hstudentService.hs_byid_list(holidayId);
//        JobsVo job = services.seljobbyid(jobId);

        //获取用户名称
//
//        StudentVO studentVO = (StudentVO) session.getAttribute("student");
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
//        String userId = session.getAttribute("actionid").toString();
        //设置当前任务办理人（主要是备注表）Act_hi_comment中含有user_id
        Authentication.setAuthenticatedUserId(empVO.getEmpId().toString());

        //把设置备注信息(任务ID，实例ID，页面上的备注)加入act_hi_comment
        taskService.addComment(taskId, processInstId, comment);
        System.out.println("studentId:asssssss" + studentId);
        //添加任务变量
        Map variable = new HashMap();
        variable.put("flow", flow);
//任课老师
//        variable.put("teacher", hstudentService.hs_assignee_teacher(studentVO.getStudId()));
//        班主任
        variable.put("classTeacher", hstudentService.hs_assignee_classTeacher(studentId));
        System.out.println("班主任的ID：" + hstudentService.hs_assignee_classTeacher(studentId));
//校长
        variable.put("schoolMaster", hstudentService.hs_assignee_schoolmaster());

        //完成当前任务(act_ruvarible中有taskid)
        taskService.complete(taskId, variable);

        //根据流程实例获取实例对象(完成流程的实例依然会存放在数据库中 但是查询出来是null的)
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).singleResult();
        if (processInstance == null) {

            if (flow.equals("拒绝")) {
                //修改审批状态
                holidayStudentVo.setStatus("2");//3、审批不通过
            } else {
                //修改审批状态
                holidayStudentVo.setStatus("1");//2、审批通过
            }
            hstudentService.hs_update(holidayStudentVo);
        }
        return "redirect:hs_myTask_list";
    }

}
