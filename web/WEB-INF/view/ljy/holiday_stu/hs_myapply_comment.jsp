<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/15
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>查看批注</title>
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
        <th colspan="4" style="text-align: center" ><h2>审批信息列表</h2></th>
    </tr>
    <tr>
        <th>ID</th>
        <th>审批时间</th>
        <th>审批人</th>
        <th>批注</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${commentList}" var="t">
        <tr bgcolor="white">
            <td>${t.id }</td>
            <td><fmt:formatDate value="${t.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${t.userId }</td>
            <td>${t.fullMessage }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
