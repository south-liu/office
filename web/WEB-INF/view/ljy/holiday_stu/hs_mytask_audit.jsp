<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/17
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <input type="hidden" value="${holidayStudent.studentId}" name="studentId" >

<table class="layui-table" lay-even="" lay-skin="row" lay-size="sm" >
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th colspan="6" style="text-align: center"><h2>审核请假申请</h2></th>
    </tr>

    </thead>
    <tbody>
    <tr>
        <td align="center">请假人ID：</td>
        <td>${holidayStudent.studentId}</td>
    </tr>
    <tr>
        <td align="center">请假人：</td>
        <td>${holidayStudent.stuName}</td>
    </tr>
    <tr>
        <td align="center">请假时长：</td>
        <td>${holidayStudent.holidayDay} 天 ${holidayStudent.holidayHour} 小时</td>
    </tr>
    <tr>
        <td align="center">请假事由：</td>
        <td>${holidayStudent.remark}</td>
    </tr>
    <tr>
        <td align="center">意见：</td>
        <td>
            <select name="flow" style="width: 50px" >
                <option value="同意">同意</option>
                <option value="拒绝">拒绝</option>
            </select>
        </td>
    </tr>

    <tr>
        <td align="center">备注:</td>
        <td><textarea style="width:1200px" name="comment" class="layui-textarea"></textarea></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center"><button type="submit" class="layui-btn" style="text-align: center">审批</button></td>
    </tr>
    </tbody>
</table>
</form>
<table class="layui-table" lay-even="" lay-skin="row" lay-size="sm">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th colspan="6" style="text-align: center"><h2>历史审批信息</h2></th>
    </tr>
    <tr>
        <th>ID</th>
        <th>审批时间</th>
        <th>审批人</th>
        <th>批注内容</th>
    </tr>
    </thead>
<tbody>
<c:forEach items="${commentList}" var="t">
    <tr bgcolor="white">
        <td>${t.id }</td>
        <td>${t.time} </td>
        <td>${t.userId }</td>
        <td>${t.fullMessage }</td>
    </tr>
</c:forEach>

</tbody>

</table>


<script>
    layui.use('form',function () {
        var form=layui.form
    })

</script>
</body>
</html>
