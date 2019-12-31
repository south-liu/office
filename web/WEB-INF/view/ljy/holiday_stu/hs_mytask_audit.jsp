<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/17
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>审核请假</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>

<form class="layui-form" action="${pageContext.request.contextPath}/hstudent/hs_mytask_complete" method="post">

    <%--请假表的Id--%>
    <input type="hidden" name="holidayId" value="${holidayStudent.holidayId}">
    <%--任务Id--%>
    <input type="hidden" name="taskId" value="${taskId}">

    <%--请假人的ID--%>
    <input type="hidden" value="${holidayStudent.studentId}" name="studentId">

    <table width="100%" cellpadding="2" bgcolor="#e6e6e6" id="test"
           style="border-collapse: inherit; border-spacing: 1px">
        <tr>
            <th colspan="8" style="text-align: center;font-size: 20px">学生申请</th>
        </tr>
        <tr bgcolor="#F2F2F2" style="height: 38px">
            <th><font size="2" color="#666">请假人</font></th>
            <th><font size="2" color="#666">请假时长</font></th>
            <th><font size="2" color="#666">开始时间</font></th>
            <th><font size="2" color="#666">结束时间</font></th>
            <th><font size="2" color="#666">请假原因</font></th>
            <th><font size="2" color="#666">批注内容</font></th>
            <th><font size="2" color="#666">意见</font></th>
            <th><font size="2" color="#666">操作</font></th>
        </tr>

        <tr bgcolor="#FFF" style="height: 38px;text-align: center">
            <td><font size="2" color="#666">${holidayStudent.stuName}</font></td>
            <td><font size="2" color="#666">${holidayStudent.holidayDay} 天 ${holidayStudent.holidayHour} 小时</font></td>
            <td><font size="2" color="#666">${holidayStudent.startTime}</font></td>
            <td><font size="2" color="#666">${holidayStudent.endTime}</font></td>
            <td><font size="2" color="#666">${holidayStudent.remark}</font></td>
            <td><input name="comment" class="layui-input"></td>
            <td>
                <select name="flow" style="width: 50px">
                    <option value="同意">同意</option>
                    <option value="拒绝">拒绝</option>
                </select>
            </td>
            <td colspan="2" style="text-align: center"><button type="submit" class="layui-btn" style="text-align: center">提交</button></td>
        </tr>
    </table>
</form>

<table  width="100%" cellpadding="2" bgcolor="#e6e6e6" style="border-collapse: inherit; border-spacing: 1px;margin-top: 50px">
    <tr>
        <th colspan="8" style="text-align: center;font-size: 20px">历史审批</th>
    </tr>
    <tr bgcolor="#F2F2F2" style="height: 38px">
        <th><font size="2" color="#666">编号</font></th>
        <th><font size="2" color="#666">审批时间</font></th>
        <th><font size="2" color="#666">审批人</font></th>
        <th><font size="2" color="#666">批注内容</font></th>
    </tr>

    <c:forEach items="${commentList}" var="t">
        <tr bgcolor="#FFF" style="height: 38px;text-align: center">
            <td><font size="2" color="#666">${t.id}</font></td>
            <td><font size="2" color="#666"><fmt:formatDate value="${t.time}" pattern="yyyy-MM-dd HH:mm:ss"/></font></td>
            <td><font size="2" color="#666">${t.userId}</font></td>
            <td><font size="2" color="#666">${t.fullMessage}</font></td>
        </tr>
    </c:forEach>
</table>

<script>
    layui.use('form', function () {
        var form = layui.form
    })

</script>
</body>
</html>
