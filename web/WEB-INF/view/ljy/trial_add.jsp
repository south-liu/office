<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/9
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加培训页面</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-input-block {
        margin-left: 110px;
        min-height: 36px;
        width: 190px;
    }

    .layui-form-label {
        float: left;
        display: block;
        padding: 9px 15px;
        width: 100px;
        font-weight: 400;
        line-height: 20px;
        text-align: right;
    }

    .layui-input-block {
        margin-left: 130px;
        min-height: 36px;
        width: 190px;
    }
</style>
<body style="padding: 30px;">
<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">试讲日期：</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="date" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">时间（星期）：</label>
        <div class="layui-input-block">
            <select name="time" lay-filter="teacher">
                <option value="">请选择一个星期</option>
                <option value="星期一">星期一</option>
                <option value="星期二">星期二</option>
                <option value="星期三">星期三</option>
                <option value="星期四">星期四</option>
                <option value="星期五">星期五</option>
                <option value="星期六">星期六</option>
                <option value="星期日">星期日</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课程/章节：</label>
        <div class="layui-input-block">
            <select name="courseId" lay-filter="classTeacher">
                <option value="">请选择课程章节</option>
                <c:forEach items="${courselist}" var="course">
                <option value="${course.courseId}">${course.courseName}</option>
                </c:forEach>
            </select>


        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">授课类型：</label>
        <div class="layui-input-block">
            <select name="type" lay-filter="classTeacher">
                <option value="">请选择授课类型</option>
                <c:forEach items="${coursetypelist}" var="coursetype">
                <option value="${coursetype.courseTypeId}">${coursetype.courseTypeName}</option>
                </c:forEach>
            </select>

        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">授课老师：</label>
        <div class="layui-input-block">
            <select name="empId" lay-filter="classTeacher">
                <option value="">请选择授课老师</option>
                <c:forEach items="${emplist}" var="emp">
                <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="sub">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });


        //监听提交
        form.on('submit(sub)', function (data) {
            console.log(data.field);
            var fd = data.field;
            var lod = layer.load();
            //部门数据
            $.ajax({
                url: "${pageContext.request.contextPath}/trial/trialadd",
                type: "post",
                async: true,
                dataType: "json",
                data: {
                    date: fd.date,
                    time: fd.time,
                    courseId: fd.courseId,
                    empId: fd.empId,
                    type:fd.type,
                    remark: fd.remark
                },
                success: function (data) {
                    layer.close(lod);
                    layer.msg('添加成功', {
                        time: 1000
                    }, function () {
                        //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //parent.layer.close(index); //再执行关闭
                        window.parent.location.reload();
                    });
                },
                error: function () {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
