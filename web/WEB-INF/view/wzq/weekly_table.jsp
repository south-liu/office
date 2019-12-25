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
    <title>查看我的周报</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>

<div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
    <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>

<%--添加表单--%>
<div style="margin-top: 20px; margin-bottom: 10px">
    <span style="font-family:宋体; font-size: 30px; margin-left: 575px; font-weight: bold">宏图软件工作周报</span>
</div>

<div style="margin-bottom: 10px">
    <span style="font-family: 宋体; font-size: 14px; margin-left: 430px;">部门：</span>

    <span style="font-family: 宋体; font-size: 14px;">${depts.deptName}</span>


    <span style="font-family: 宋体; font-size: 14px; margin-left: 85px">姓名：</span>

    <span style="font-family: 宋体; font-size: 14px;">${depts.eName}</span>

    <span style="font-family: 宋体; font-size: 14px; margin-left: 85px">时间：</span>

    <span style="font-family: 宋体; font-size: 14px;">${weekly.workDay}</span>
</div>

<table width="40%" cellpadding="2" bgcolor="black" id="test" style="border-collapse: inherit; border-spacing: 1px; text-align: center; margin-left: 420px">
    <tr bgcolor="#FFF">
        <td rowspan="3" style="width: 50px">
            <p style="font-family: 宋体; font-size: 14px; height: 40px">本</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">周</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">总</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">结</p>
        </td>
        <td style="height: 200px; width: 50px">
            <p style="font-family: 宋体; font-size: 14px; height: 25px">本</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">周</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">情</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">况</p>
        </td>
        <td colspan="5" style="height: 200px; vertical-align: top; text-align: left">
            <span style="font-family: 宋体; font-size: 14px;">
                ${weekly.weekCur}
            </span>
        </td>
    </tr>
    <tr bgcolor="#FFF">
        <td style="height: 70px; width: 50px">
            <span style="font-family: 宋体; font-size: 14px;">问</span>
            <span style="font-family: 宋体; font-size: 14px;">题</span>
            <span style="font-family: 宋体; font-size: 14px;">学</span>
            <span style="font-family: 宋体; font-size: 14px;">生</span>
        </td>
        <td colspan="5" style="height: 70px; vertical-align: top; text-align: left">
            <span style="font-family: 宋体; font-size: 14px;">
                ${weekly.studentQuestion}
            </span>
        </td>
    </tr>
    <tr bgcolor="#FFF">
        <td style="height: 90px; width: 50px">
            <span style="font-family: 宋体; font-size: 14px;">意</span>
            <span style="font-family: 宋体; font-size: 14px;">见</span>
            <span style="font-family: 宋体; font-size: 14px;">建</span>
            <span style="font-family: 宋体; font-size: 14px;">议</span>
        </td>
        <td colspan="5" style="height: 70px; vertical-align: top; text-align: left">
            <span style="font-family: 宋体; font-size: 14px;">
                ${weekly.idea}
            </span>
        </td>
    </tr>
    <tr bgcolor="#FFF">
        <td style="height: 270px; width: 50px">
            <p style="font-family: 宋体; font-size: 14px; height: 40px">下</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">周</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">计</p>
            <p style="font-family: 宋体; font-size: 14px; height: 40px">划</p>
        </td>
        <td style="width: 50px">
            <p style="font-family: 宋体; font-size: 14px; height: 25px">下</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">周</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">计</p>
            <p style="font-family: 宋体; font-size: 14px; height: 25px">划</p>
        </td>
        <td colspan="5" style="height: 270px; vertical-align: top; text-align: left">
            <span style="font-family: 宋体; font-size: 14px;">
                ${weekly.weekNext}
            </span>
        </td>
    </tr>
</table>

</body>
</html>