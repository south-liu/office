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
    <title>添加周报</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px 0 0 0;">
    <legend>添加本周周报</legend>
    <jsp:include page="../public/head.jsp"></jsp:include>
</fieldset>
    <form class="layui-form" action="${pageContext.request.contextPath}/MY/addmyweekly" method="post">

        <input type="hidden" name="empId" value="${emp.empId}"/>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="width: 120px;text-align:left;margin: 0px 15px 0px 100px">本周情况描述:</label>
            <div class="layui-input-block" style="margin-right: 210px">
                <textarea placeholder="请输入本周情况描述" lay-verify="required" autocomplete="off" name="weekCur" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="width: 120px;text-align:left;margin: 0px 15px 0px 100px">问题学生情况反馈:</label>
            <div class="layui-input-block" style="margin-right: 210px">
                <textarea placeholder="请输入问题学生情况反馈" lay-verify="required" name="studentQuestion" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="width: 120px;text-align:left;margin: 0px 15px 0px 100px">意见建议:</label>
            <div class="layui-input-block" style="margin-right: 210px">
                <textarea placeholder="请输入意见或建议" lay-verify="required" name="idea" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label" style="width: 120px;text-align:left;margin: 0px 15px 0px 100px">下周工作计划:</label>
            <div class="layui-input-block" style="margin-right: 210px">
                <textarea placeholder="请输入下周工作计划" lay-verify="required" name="weekNext" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn"  lay-submit="" lay-filter="demo1">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="fanhui()">取消</button>
            </div>
        </div>
    </form>

    <script>
        function fanhui() {
            location.href="${pageContext.request.contextPath}/MY/tomyweekly_list";
        }

        layui.use(['form', 'layedit', 'laydate'], function(){

        });
    </script>

</body>
</html>
