<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传流程</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px 0 0 0;">
    <legend>注意：上传流程可能使得系统流程需要更改，请谨慎使用！</legend>
    <jsp:include page="../public/head.jsp"></jsp:include>
</fieldset>
    <form class="layui-form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/TP/addliucheng" method="post">

        <input type="file" name="pdFile" lay-verify="required" style="height: 80px; margin-top: 50px; margin-left: 515px;"/>

        <div class="layui-form-item" style="margin-left: 410px;">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn"  lay-submit="" lay-filter="demo1">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="fanhui()">取消</button>
            </div>
        </div>
    </form>

    <script>
        function fanhui() {
            history.go(-1);
        }

        layui.use(['form', 'layedit', 'laydate'], function(){

        });
    </script>

</body>
</html>
