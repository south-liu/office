<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/13
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传流程定义</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>
<form action="${pageContext.request.contextPath}/hstudent/hs_uploadProcess" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadfile" id="uploadfile" lay-verify="required"
           class="layui-input"
           style="width: 200px;height: 30px">
    <button type="submit" class="layui-btn">提交</button>
</form>

</body>
</html>
