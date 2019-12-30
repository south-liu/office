<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/15
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>我的任务</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>
<%--返回按钮--%>
<div class="layui-inline">
    <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>
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
        <th>ID</th>
        <%--<th>实例ID</th>--%>
        <%--<th>流程ID</th>--%>
        <th>执行职位</th>
        <th>执行人</th>
        <th>申请时间</th>
        <th colspan="6" style="text-align: center">相关操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${taskList}" var="t">
        <tr bgcolor="white">
            <td>${t.id }</td>
            <%--<td>${t.processInstanceId }</td>--%>
            <%--<td>${t.processDefinitionId }</td>--%>
            <td>${t.name }</td>
            <td>${t.assignee }</td>
            <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><a href="${pageContext.request.contextPath}/hstudent/hs_mytask_taskaudit?taskId=${t.id}&instId=${t.processInstanceId}">查看详情</a></td>
            <td><a href="${pageContext.request.contextPath}/hstudent/hs_myapply_lookapplyImg?instId=${t.processInstanceId}" target="_blank">办理进度</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
