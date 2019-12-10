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
    <title>班级修改页面</title>
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
<form class="layui-form" action="" lay-filter="student">
    <div class="layui-form-item">
        <label class="layui-form-label">班级编号：</label>
        <div class="layui-input-inline">
            <input type="text" name="classNo" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">班级名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="className" lay-verify="required" placeholder="请输入" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">授课老师：</label>
        <div class="layui-input-block">
            <select name="teacher" lay-filter="teacher">
                <option value="">请选择授课老师</option>
                <c:forEach items="${emplist}" var="emp">
                    <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">班主任：</label>
        <div class="layui-input-block">
            <select name="classTeacher" lay-filter="classTeacher">
                <option value="">请选择班主任</option>
                <c:forEach items="${emplist}" var="emp">
                    <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">班级类别：</label>
        <div class="layui-input-block">
            <select name="classType" lay-filter="classType">
                <option value="">请选择班级类别</option>
                <c:forEach items="${cltylist}" var="clty">
                    <option value="${clty.classTypeId}">${clty.classTypeName}</option>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">系名称：</label>
        <div class="layui-input-block">
            <select name="deptId" lay-filter="deptType">
                <option value="">请选择所属系名称</option>
                <c:forEach items="${deptlist}" var="dept">
                    <option value="${dept.deptId}">${dept.deptName}</option>
                </c:forEach>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">专业名称：</label>
        <div class="layui-input-block">
            <select name="majorId" lay-filter="deptType">
                <option value="">请选择专业类别</option>
                <c:forEach items="${majorlist}" var="major">
                    <option value="${major.majorId}">${major.majorName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">界别年份：</label>
        <div class="layui-input-block">
            <select name="falled" lay-filter="deptType">
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
            var lod = layer.load();
            //部门数据
            $.ajax({
                url: "${pageContext.request.contextPath}/studentclass/studentclassupd",
                type: "post",
                async: true,
                dataType: "json",
                data: {
                    classId:${stucla.classId},
                    classNo: fd.classNo,
                    className: fd.className,
                    classTeacher: fd.classTeacher,
                    classType: fd.classType,
                    deptId: fd.deptId,
                    fallId: fd.fallId,
                    majorId: fd.majorId,
                    teacher: fd.teacher,
                    remark: fd.remark
                },
                success: function (data) {
                    layer.close(lod);
                    layer.msg('修改成功', {
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

        form.val('student', {
            "classNo": "${stucla.classNo}",
            "className": "${stucla.className}",
            "classTeacher": "${stucla.classTeacher}",
            "classType": "${stucla.classType}",
            "deptId": "${stucla.deptId}",
            "falled": "${stucla.falled}",
            "majorId": "${stucla.majorId}",
            "remark": "${stucla.remark}",
            "teacher": "${stucla.teacher}"
        })
    });
</script>
</body>
</html>
