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
    <title>员工考核详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--顶部按钮--%>
<div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
    <button class="layui-btn layui-btn-sm" id="fan" onclick="fan()" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>

<%--添加表单--%>
<table width="100%" cellpadding="2" bgcolor="#e6e6e6" id="test" style="border-collapse: inherit; border-spacing: 1px">
    <tr bgcolor="#F2F2F2" style="height: 38px">
        <th><font size="2" color="#666">编号</font></th>
        <th><font size="2" color="#666">考核内容</font></th>
        <th><font size="2" color="#666">员工姓名</font></th>
        <th><font size="2" color="#666">考核分数</font></th>
        <th><font size="2" color="#666">考核时间</font></th>
        <th><font size="2" color="#666">录入人</font></th>
        <th><font size="2" color="#666">说明</font></th>
    </tr>
    
    <tr bgcolor="#FFF" style="height: 38px;text-align: center">
        <td><font size="2" color="#666">${aduitLog.aduitLogId}</font></td>
        <td><font size="2" color="#666">${aduitModel.aduitName}</font></td>
        <td><font size="2" color="#666">${emp.empName}</font></td>
        <td><font size="2" color="#666">${aduitLog.score}</font></td>
        <td><font size="2" color="#666">${aduitLog.auditDate}</font></td>
        <td><font size="2" color="#666">${aduitLog.auditPerson}</font></td>
        <td><font size="2" color="#666">${aduitLog.remark}</font></td>
    </tr>
</table>

    <div>
        <img id="demo1" src="${pageContext.request.contextPath}${aduitLog.image}" style="width: 595px; height: 423px; border: dotted">
    </div>

<script>
    //头部上传按钮点击事件
    function fan(){
        location.href='${pageContext.request.contextPath}/MY/toaduitlog';
    }

    $('#demo1').attr('src', ${pageContext.request.contextPath} + ${aduitLog.image}); //图片链接（base64）

</script>
</body>
</html>