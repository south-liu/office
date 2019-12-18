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
    <title>历史批注</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<table width="100%" cellpadding="2" bgcolor="#e6e6e6" id="test" style="border-collapse: inherit; border-spacing: 1px">
    <tr bgcolor="#F2F2F2" style="height: 38px">
        <th><font size="2" color="#666">审批时间</font></th>
        <th><font size="2" color="#666">审批人</font></th>
        <th><font size="2" color="#666">批注内容</font></th>
    </tr>
    <c:forEach items="${commentList}" var="list">
        <tr bgcolor="#FFF" style="height: 38px;text-align: center">
            <td><font size="2" color="#666">${list.time}</font></td>
            <td><font size="2" color="#666">${list.userId}</font></td>
            <td><font size="2" color="#666">${list.fullMessage}</font></td>
        </tr>
    </c:forEach>
</table>


<script>

</script>
</body>
</html>