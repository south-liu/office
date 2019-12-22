<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/19
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>系统报表</title>
</head>
<style>
    * {margin: 0;padding: 0;}
    .panel {overflow: hidden;font-size: 12px;}
    a {text-decoration: none !important;color: #404040;}
</style>
<body>
<div class="panel" style="display: block;width: auto;">
    <div style="padding: 45px 0;width: 1090px;height: 519px;">
        <table width="100%" align="center">
            <tbody>
            <tr height="120">
                <td align="center">
                    <a href="${pageContext.request.contextPath}/RF/toempassessment" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        日常考核统计
                    </a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/stuholi/tolist" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        学生请假统计
                    </a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/empholi/tolist" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        员工请假统计
                    </a>
                </td>
            </tr>
            <tr height="120">
                <td align="center">
                    <a href="${pageContext.request.contextPath}/bedroom/goto_bedroom_list" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        宿舍统计报表
                    </a>
                </td>
                <td align="center">
                    <a href="" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        历史值班安排表
                    </a>
                </td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/RF/tocheckwork_rf" target="_target">
                        <img src="../../images/report_normal.gif" border="0">
                        <br>
                        未打卡说明统计
                    </a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>