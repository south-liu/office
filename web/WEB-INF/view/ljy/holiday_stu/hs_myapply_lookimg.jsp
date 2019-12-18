<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/15
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看办理进度的图片</title>
</head>
<body>
<!-- 1.根据流程定义的ID和名字获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="viewProcessImage?did=${pd.deploymentId}&imageName=${pd.diagramResourceName}">

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<c:forEach items="${mapList}" var="d">
    <div style="position: absolute;border:2px solid red;top:${d.y-2}px;left: ${d.x-2}px;width: ${d.width}px;height:${d.height}px;"></div>
</c:forEach>
</body>
</html>
