<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/9
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>欢迎</title>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<style type="text/css">
    .llayui-btn {
        display: inline-block;
        height: 38px;
        line-height: 38px;
        padding: 0 220px;
        background-color: #009688;
        color: #fff;
        white-space: nowrap;
        text-align: center;
        font-size: 14px;
        border: none;
        border-radius: 2px;
        cursor: pointer;
    }
    .llayui-btn-primary {
        border: 1px solid #C9C9C9;
        background-color: #fff;
        color: #555;
    }
</style>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>我的事项</legend>
</fieldset>

<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">待办事项</div>
                <div class="layui-card-body">
                    <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/TP/mytaskholidaylist?empId=${emp.empId}';">
                        <i class="layui-icon layui-icon-tips"></i> 员工请假待审批(${myEmpHolidayTask})
                    </a><br>
                    <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/hstudent/hs_myTask_list';">
                        <i class="layui-icon layui-icon-tips"></i> 学生请假待审批(0)
                    </a><br>
                    <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/checkwork/gotomycheck';">
                        <i class="layui-icon layui-icon-tips"></i> 未打卡待审批(${countCheckWork})
                    </a><br>
                    <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/MY/tomyweekly_list';">
                        <i class="layui-icon layui-icon-log"></i> 本周工作周报(${myweekly})&nbsp;&nbsp;<font color="red">周日17:00前提交</font>
                    </a><br>
                    <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/mychat/tomychatlist';">
                        <i class="layui-icon layui-icon-chat"></i> 月谈心记录(${mychat})&nbsp;&nbsp;<font color="red">每月需完成5个</font>
                    </a><br>
                </div>
            </div>
        </div>

        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">通知公告</div>
                <div class="layui-card-body">

                    <c:forEach items="${myNotice}" var="notice" begin="0" end="3">

                        <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/mynotice/viewNotice?noticeId=${notice.noticeId}';">
                            <i class="layui-icon layui-icon-tips"></i> ${notice.title}
                            <span style="float: right;">${notice.empName}&nbsp;&nbsp;${notice.noticeIime}</span>
                        </a><br>

                    </c:forEach>

                    <a style="font-weight: 700;" href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/mynotice/toNoticeList';">
                         所有公告 <i class="layui-icon layui-icon-next"></i>
                    </a><br>

                </div>
            </div>
        </div>

        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header"><span id="titSpan"></span></div>
                <div class="layui-card-body">
                    <span id="conSpan"></span>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    
    $(function () {
        $.get('https://v1.hitokoto.cn/',{},function (data) {
            //console.log(data);
            $('#titSpan').text(data.from);
            $('#conSpan').text(data.hitokoto);
        },'json');
    })
    
    layui.use(['element', 'layer'], function(){
        var element = layui.element;
        var layer = layui.layer;

        //监听折叠
        element.on('collapse(test)', function(data){
            layer.msg('展开状态：'+ data.show);
        });
    });
</script>
</body>
</html>
