<%--
  Created by IntelliJ IDEA.
  User: 南城
  Date: 2019/11/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的任务</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<%--顶部按钮--%>
<div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
    <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>

<%--添加表单--%>
<table width="100%" cellpadding="2" bgcolor="#e6e6e6" id="test" style="border-collapse: inherit; border-spacing: 1px">
    <tr bgcolor="#F2F2F2" style="height: 38px">
        <th><font size="2" color="#666">编号</font></th>
        <th><font size="2" color="#666">请假人</font></th>
        <th><font size="2" color="#666">请假时长</font></th>
        <th><font size="2" color="#666">开始时间</font></th>
        <th><font size="2" color="#666">结束时间</font></th>
        <th><font size="2" color="#666">类型</font></th>
        <th><font size="2" color="#666">状态</font></th>
        <th><font size="2" color="#666">备注</font></th>
        <th><font size="2" color="#666">操作</font></th>
    </tr>
    <c:forEach items="${holidayList}" var="list">
        <tr bgcolor="#FFF" style="height: 38px;text-align: center">
            <td><font size="2" color="#666">${list.holidayId}</font></td>
            <td><font size="2" color="#666">${list.empName}</font></td>
            <td><font size="2" color="#666">${list.dayStr}</font></td>
            <td><font size="2" color="#666">${list.startTime}</font></td>
            <td><font size="2" color="#666">${list.endTime}</font></td>
            <td><font size="2" color="#666">${list.type}</font></td>
            <td>
                <font size="2" color="#666">
                    <c:if test="${list.status == 1}">审批中</c:if>
                    <c:if test="${list.status == 2}">已完成</c:if>
                    <c:if test="${list.status == 3}">不批准</c:if>

                </font>
            </td>
            <td><font size="2" color="#666">${list.remark}</font></td>
            <td>
                <a class="layui-btn layui-btn-xs" onclick="seeComm(${list.holidayId});">查看批注</a>
            </td>
        </tr>
    </c:forEach>
</table>


<script>
    function seeComm(holidayId) {
        layer.open({
            title: '批注',
            type: 2,
            content: '${pageContext.request.contextPath}/TP/pizhuholiday?holidayId='+holidayId,
            resize:false,
            area:['500px','300px']
        })
    };
</script>
</body>
</html>