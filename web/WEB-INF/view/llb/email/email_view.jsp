<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看公告</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding:30px">

    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);">返回</button>
    </div>

    <center>
        <h1 style="margin-bottom: 10px;">${email.title}</h1>
    </center>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>内容</legend>
    </fieldset>
    <blockquote class="layui-elem-quote layui-quote-nm">
        <div id="emailContent"></div>
    </blockquote>

    <c:if test="${email.fileName != null}">
        <fieldset class="layui-elem-field">
            <legend>附件下载</legend>
            <div class="layui-field-box">
                <a href="${pageContext.request.contextPath}/email/download.do?emailId=${email.emailId}">${email.fileName}</a>
            </div>
        </fieldset>
    </c:if>


<script type="text/javascript">

    $('#emailContent').html('${email.content}');

    layui.use(['form','layedit'],function () {
        var form = layui.form;
        var layedit = layui.layedit;

        var myEdit = layedit.build('myEdit',{

        });

    });
</script>
</body>
</html>
