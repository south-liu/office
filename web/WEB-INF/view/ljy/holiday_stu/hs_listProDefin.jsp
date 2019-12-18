<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/13
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看流程列表</title>
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
        <th colspan="6" style="text-align: center"><h2>流程定义列表</h2></th>
    </tr>
    <tr>
        <th>流程ID</th>
        <th>流程名称</th>
        <th>流程KEY</th>
        <th colspan="3" style="text-align: center">相关操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${processList}" var="pd">
        <tr bgcolor="white">
            <td>${pd.id }</td>
            <td>${pd.name }</td>
            <td>${pd.key }</td>
                <%--deploymentId：获取的是bytearray下的数据--%>
            <td style="width: 100px"><a
                    href="viewProcessImage?did=${pd.deploymentId}&imageName=${pd.diagramResourceName}">查看流程图</a>
            <td style="width: 100px"><a href="toExport?id=${pd.id}">下载流程图</a>
            <td style="width: 100px"><a href="delProgressDefine?id=${pd.deploymentId}"
                                        onclick="return confirm('确认删除?')">删除流程</a></td>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
