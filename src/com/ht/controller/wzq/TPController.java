package com.ht.controller.wzq;

import com.ht.service.llb.IDeptService;
import com.ht.service.wzq.TPService;
import com.ht.vo.DeptVO;
import com.ht.vo.EmpVO;
import com.ht.vo.HolidayVO;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by shkstart on 2019/12/13.
 */
@Controller
@RequestMapping("/TP")
public class TPController {


    @Resource
    private ProcessEngine processEngine;

    @Resource
    private TaskService taskService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private HistoryService historyService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    TPService tps;

    @Resource
    private IDeptService deptService;


    //------------------流程管理-----------------------

    //查看流程
    @RequestMapping("/toliucheng_list")
    public String toliucheng_list(Model model){
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> list = query.list();
        model.addAttribute("liucheng", list);
        return "wzq/liucheng_list";
    }
    //去上传流程
    @RequestMapping("/toaddliucheng")
    public String toaddliucheng(){
        return "wzq/liucheng_add";
    }
    //上传流程
    @RequestMapping("/addliucheng")
    public String addliucheng(MultipartFile pdFile){
        try {
            //创建临时file对象
            File file = File.createTempFile("temp", null);

            //把MultipartFile对象转换成java.io.FileUI对象
            pdFile.transferTo(file);

            //部署Zip文件，将流程上传到Activit框架
            Deployment deployment = repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(file))).deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:toliucheng_list";
    }
    //删除流程
    @RequestMapping("/delliucheng")
    @ResponseBody
    public String delliucheng(String id){
        repositoryService.deleteDeployment(id, true);
        return "success";
    }
    //查看流程图
    @RequestMapping("/liuchengimgs")
    public String liuchengimgs(String did, String imageName, HttpServletResponse response){
        InputStream in = repositoryService.getResourceAsStream(did, imageName);

        try {
            OutputStream out = response.getOutputStream();

            //把图片的输入流程写入response的输出流中。
            byte[] b = new byte[1024];
            for (int len = -1; (len = in.read(b)) != -1; ){
                out.write(b, 0, len);
            }

            //关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //下载流程图
    @RequestMapping("/dowliucheng")
    public String dowliucheng(String id, HttpServletResponse response){
        try {

            //设置response的头参数，attachment就是附件， filename = 文件名称
            response.setHeader("Content-disposition", "attachment;filename=" + id + ".zip");

            //下载的文件类型是.zip文件
            response.setContentType("application/x-zip-compressed");

            //------------------------------------------------------------------------------------------------

            //流程定义对象
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();

            //部署id
            String deploymentId = processDefinition.getDeploymentId();

            //bpmn资源文件名称
            String resourceName_bpmn = processDefinition.getResourceName();

            //bpmn资源文件出入流
            InputStream inputStream_bpmn = repositoryService.getResourceAsStream(deploymentId, resourceName_bpmn);

            //.png文件名称
            String resourceName_png = processDefinition.getDiagramResourceName();

            //png资源文件输入流
            InputStream inputStream_png = repositoryService.getResourceAsStream(deploymentId, resourceName_png);

            //----------------------------创建输出流，绑定到response对象------------------------

            OutputStream out = response.getOutputStream();

            //创建输出流对象，绑定到输出流
            ZipOutputStream zip = new ZipOutputStream(out);

            //流复制
            byte[] b = new byte[1024];
            int len = -1;

            //定义Zip压缩包中的文件对象（Zip实体）
            ZipEntry ze = new ZipEntry(resourceName_bpmn);

            //把创建的实体对象放到压缩包中
            zip.putNextEntry(ze);

            //文件内容拷贝
            while ((len = inputStream_bpmn.read(b, 0 , 1024)) != -1){
                zip.write(b, 0, b.length);
            }

            zip.closeEntry();

            //-----------------------------------------------------------------

            ZipEntry zel = new ZipEntry(resourceName_png);
            zip.putNextEntry(zel);
            while ((len = inputStream_png.read(b, 0, 1024)) != -1){
                zip.write(b, 0, b.length);
            }

            //关闭流
            inputStream_bpmn.close();
            inputStream_png.close();
            zip.finish();
            zip.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    //-----------------员工请假---------------------

    @RequestMapping("/toholiday_list")
    public String toholiday_list(){
        return "wzq/holiday_list";
    }
    //查看我的请假申请
    @RequestMapping("/myholidaylist")
    @ResponseBody
    public Map myholidaylist(Integer empId, int page, int limit){
        List list = tps.selholiday(empId, page, limit);
        Integer count = tps.selcountholiday(empId);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加请假申请
    @RequestMapping("/toaddholiday")
    public String toaddholiday(){
        return "wzq/holiday_add";
    }

    //添加请假申请并绑定流程
    @RequestMapping("/addholiday")
    @ResponseBody
    public String addholiday(HolidayVO holiday, HttpSession session){
        //登陆用户
        EmpVO empVO = (EmpVO) session.getAttribute("emp");

        //保存单据
        holiday.setEmpId(empVO.getEmpId());
        holiday.setStatus(1);
        tps.addholiday(holiday);

        //该员工所在部门的负责人
        String chairman = deptService.selById(empVO.getDeptId()).getChairman();

        //处理人
        String assignee = chairman;

        //如果申请人是部门负责人
        if (chairman.equals(empVO.getEmpName())){
            //上级部门Id
            int nextDeptId = deptService.selById(empVO.getDeptId()).getParentId();
            //如果是最高部门
            if (nextDeptId == 0) {
                List<DeptVO> all = deptService.allDept();
                for (DeptVO deptVO : all) {
                    if (deptVO.getParentId() == -1) {//查询出总部门的负责人
                        assignee = deptVO.getChairman();
                    }
                }
            } else {
                assignee = deptService.selById(nextDeptId).getChairman();
            }
        }

        //设置流程实例变量集合（目的是使单据和流程关联起来）
        Map<String, Object> map = new HashMap<>();
        map.put("empId", empVO.getEmpName());  //申请人
        map.put("day",holiday.getHolidayDay());
        map.put("assignee",assignee);
        map.put("holidayId",holiday.getHolidayId());


        //启动员工请假实例（通过流程定义的Key来启动一个实例）
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("empLeave", map);
        System.out.println("流程实例启动成功"+processInstance.getId());

        //根据流程实例ID获取当前实例正在执行的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByProcessInstanceId().desc().singleResult();

        //完成申请任务任务
        taskService.complete(task.getId(), map);

        return "success";
    }


    //查看我的审批任务
    @RequestMapping("/mytaskholidaylist")
    public String mytaskholidaylist(Integer empId, Model model){
        EmpVO emp = tps.selempName(empId);
        List<HolidayVO> holidayVOList = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(emp.getEmpName()).list();
        for (Task task : taskList) {
            Integer holidayId = (Integer) taskService.getVariable(task.getId(),"holidayId");
            HolidayVO holidayVO = tps.selHolidayById(holidayId);
            holidayVO.setTaskId(task.getId());
            holidayVOList.add(holidayVO);
        }

        List<Map> holidayMaps = new ArrayList<>();

        for (HolidayVO holidayVO : holidayVOList) {
            Map map = new HashMap();
            map.put("holidayId",holidayVO.getHolidayId());
            map.put("empName",tps.selempName(holidayVO.getEmpId()).getEmpName());
            map.put("dayStr",holidayVO.getDayStr());
            map.put("startTime",holidayVO.getStartTime());
            map.put("endTime",holidayVO.getEmpId());
            map.put("type",holidayVO.getType());
            map.put("status",holidayVO.getStatus());
            map.put("remark",holidayVO.getRemark());
            map.put("taskId",holidayVO.getTaskId());
            holidayMaps.add(map);
        }

        model.addAttribute("holidayList", holidayMaps);
        return "wzq/holiday_task";
    }

    //提交审批
    @RequestMapping("/comholiday")
    @ResponseBody
    public String comholiday(Integer holidayId, String taskId, String flow,String comment,HttpSession session){

        EmpVO empVO = (EmpVO) session.getAttribute("emp");

        //根据任务ID得到对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        //通过任务对象获取流程实例ID
        String processInstId = task.getProcessInstanceId();

        //根据单据ID查询单据对象
        HolidayVO holiday = tps.selholiday(holidayId);

        //设置当前任务办理人
        Authentication.setAuthenticatedUserId(empVO.getEmpName());

        //设置备注信息（任务ID、实例ID、页面上的备注）
        taskService.addComment(taskId, processInstId, comment);

        String assignee = "";
        //上级部门Id
        int nextDeptId = deptService.selById(empVO.getDeptId()).getParentId();
        //如果是最高部门
        if (nextDeptId == 0) {
            List<DeptVO> all = deptService.allDept();
            for (DeptVO deptVO : all) {
                if (deptVO.getParentId() == -1) {//查询出总部门的负责人
                    assignee = deptVO.getChairman();
                }
            }
        } else {
            assignee = deptService.selById(nextDeptId).getChairman();
        }

        //添加任务变量
        Map variable = new HashMap();
        variable.put("flow", flow);
        //设置办理人
        variable.put("assignee", assignee);

        //完成当前任务
        taskService.complete(taskId, variable);

        //根据流程实例获取实例对象（完成流程的实例依然会存放在数据库中，但是查询出来的是null的）
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstId).singleResult();
        if (processInstance == null){
            if ("拒绝".equals(flow)){
                //修改状态
                holiday.setStatus(3);  //状态 1:审批中 2：已完成 3：不批准
            }else if ("同意".equals(flow)){
                //修改状态
                holiday.setStatus(2);  //状态 1:审批中 2：已完成 3：不批准
            }
            tps.updholiday(holiday);
        }
        return "success";
    }

    //查看批注
    @RequestMapping("/pizhuholiday")
    public String pizhuholiday(Integer holidayId, Model model){
        //通过holidayId查询历史变量对象
        HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("holidayId", holidayId).singleResult();

        //获取流程实例ID（查询历史批注）
        List<Map> commentMaps = new ArrayList<>();
        List<Comment> commentList = taskService.getProcessInstanceComments(hvi.getProcessInstanceId());
        for (Comment comment : commentList) {
            Map map = new HashMap();
            map.put("time",comment.getTime().toLocaleString());
            map.put("userId",comment.getUserId());
            map.put("fullMessage",comment.getFullMessage());
            commentMaps.add(map);
        }
        model.addAttribute("commentList", commentMaps);
        return "wzq/holiday_comm";
    }

}
