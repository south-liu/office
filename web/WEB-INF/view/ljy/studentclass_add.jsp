<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/6
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>班级添加页面</title>
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
<body style="padding:30px 0px 30px 44px;">
<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">班级编号：</label>
        <div class="layui-input-inline">
            <input type="text" name="classNo" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>

        <label class="layui-form-label">班级名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="className" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">授课老师：</label>
        <div class="layui-input-inline">
            <select name="teacher" id="teacher" lay-filter="teacher">
                <option value="">请选择授课老师</option>
                <c:forEach items="${emplist}" var="emp">
                    <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
        <label class="layui-form-label">班主任：</label>
        <div class="layui-input-inline">
            <select name="classTeacher" id="classTeacher" lay-filter="classTeacher">
                <option value="">请选择班主任</option>
                <c:forEach items="${emplist}" var="emp">
                    <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">班级类别：</label>
        <div class="layui-input-inline">
            <select name="classType" id="classType" lay-filter="classType">
                <option value="">请选择班级类别</option>
                <c:forEach items="${cltylist}" var="clty">
                    <option value="${clty.classTypeId}">${clty.classTypeName}</option>
                </c:forEach>
            </select>
        </div>

        <label class="layui-form-label">系名称：</label>
        <div class="layui-input-inline">
            <select name="deptId" id="deptId" lay-filter="deptType">
                <option value="">请选择所属系名称</option>
                <c:forEach items="${deptlist}" var="dept">
                    <option value="${dept.collegeDeptId}">${dept.collegeDeptName}</option>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">专业名称：</label>
        <div class="layui-input-inline">
            <select name="majorId" id="majorId" lay-filter="deptType">
                <option value="">请选择专业类别</option>
                <c:forEach items="${majorlist}" var="major">
                    <option value="${major.majorId}">${major.majorName}</option>
                </c:forEach>
            </select>
        </div>
        <label class="layui-form-label">界别年份：</label>
        <div class="layui-input-inline">
            <select name="falled" id="falled" lay-filter="deptType">
                <option value="">请选择年份</option>
                <c:forEach items="${studfall}" var="sf">
                    <option value="${sf.fallId}">${sf.level}</option>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明：</label>
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
    layui.use(['form', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer


        //监听提交
        form.on('submit(sub)', function (data) {
            console.log(data.field);
            var fd = data.field;

            var teacher = $('#teacher').val().trim();
            var classTeacher = $('#classTeacher').val().trim();
            var classType = $('#classType').val().trim();
            var deptId = $('#deptId').val().trim();
            var majorId = $('#majorId').val().trim();
            var falled = $('#falled').val().trim();

            if (teacher == '' || classTeacher == '' || classType == '' || deptId == '' || majorId == '' || falled == '') {
                layer.msg('请输入全部信息!', {
                    icon: 2,
                    time: 1000
                });
            }else{
                var lod = layer.load();
                //班级管理数据
                $.ajax({
                    url: "${pageContext.request.contextPath}/studentclass/studentclassadd",
                    type: "post",
                    async: true,
                    dataType: "json",
                    data: {
                        classNo: fd.classNo,
                        className: fd.className,
                        classTeacher: fd.classTeacher,
                        classType: fd.classType,
                        deptId: fd.deptId,
                        falled: fd.falled,
                        majorId: fd.majorId,
                        teacher: fd.teacher,
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
            }


            return false;
        });
    });
</script>
</body>
</html>
