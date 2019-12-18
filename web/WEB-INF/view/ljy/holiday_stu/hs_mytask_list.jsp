<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/15
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的任务</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>
<table class="layui-table" lay-even="" lay-skin="row">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th colspan="8" style="text-align: center" ><h2>我的任务列表</h2></th>
    </tr>
    <tr>
        <th>任务ID</th>
        <th>实例ID</th>
        <th>流程ID</th>
        <th>任务名称</th>
        <th>任务执行人</th>
        <th>任务创建时间</th>
        <th colspan="6" style="text-align: center">相关操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${taskList}" var="t">
        <tr bgcolor="white">
            <td>${t.id }</td>
            <td>${t.processInstanceId }</td>
            <td>${t.processDefinitionId }</td>
            <td>${t.name }</td>
            <td>${t.assignee }</td>
            <td>${t.createTime}</td>
            <td><a href="/hstudent/hs_mytask_taskaudit?taskId=${t.id}&instId=${t.processInstanceId}">查看详情</a></td>
            <td><a href="${pageContext.request.contextPath}/hstudent/hs_myapply_lookapplyImg?instId=${t.processInstanceId}" target="_blank">办理进度</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>