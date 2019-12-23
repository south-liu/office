<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/6
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询班级管理</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>

<style type="text/css">
    .layui-btn-llb {
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }

    .layui-btn-llbb {
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>


<script type="text/html" id="toolbar">
    <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">届别年份:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="fallId" id="fallId" lay-search lay-filter="fTab2">
                    <option value="">请选择年份</option>
                    <c:forEach items="${studfall}" var="sf">
                        <option value="${sf.fallId}">${sf.level}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <%--<button class="layui-btn layui-btn-sm" lay-event="sousuo">搜索</button>--%>
        <button class="layui-btn layui-btn-sm" lay-event="addstuclass" onclick="addstuclass()">添加</button>
    </div>
</script>


<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table', 'form'], function () {
            var table = layui.table;
            var form = layui.form;

            var mytable = table.render({
                elem: '#myTab',
                url: '${pageContext.request.contextPath}/studentclass/studentclasslist',
                toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
                defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }],
                title: '员工数据表',
                cols: [[
                    {type: 'checkbox', fixed: 'left'},
                    {field: 'classId', title: '序号', fixed: 'left', unresize: true, sort: true, align: 'center', width: 90},
                    {field: 'classNo', title: '班级编号 ', edit: 'text', align: 'center', width: 90},
                    {field: 'className', title: '班级名称', align: 'center', width: 100},
                    {field: 'empName', title: '授课老师', align: 'center', width: 100},
                    {field: 'classTeacher', title: '班主任', align: 'center', width: 100},
                    {field: 'classTypeName', title: '班级类别', align: 'center', width: 100},
                    {field: 'level', title: '界别年份', align: 'center', width: 100},
                    {field: 'deptName', title: '系名称', align: 'center', width: 100},
                    {field: 'majorName', title: '专业名称', align: 'center', width: 110},
                    {field: 'remark', title: '说明', edit: 'text', align: 'center', width: 120},
                    {fixed: 'right', title: '操作', toolbar: '#bar', align: 'center',width:120}
                ]],
                page: true
            });


            table.on('tool(fTab)', function (obj) {
                var event = obj.event;
                if (event === 'edit') {
                    var index = layer.open({
                        title: '修改',
                        type: 2,
                        content: '${pageContext.request.contextPath}/studentclass/gotostudentclassupd?classId=' + obj.data.classId,
                        btnAlign: 'c',
                        area: ['780px', '480px']
                    });

                } else if (event === 'del') {
                    layer.confirm('确认删除', function (index) {
                        console.log(index);
                        $.post('${pageContext.request.contextPath}/studentclass/studentclassdelete', {classId: obj.data.classId}, function (data) {
                            console.log(data);
                            layer.msg('删除成功！');
                            obj.del();
                        }, 'json');
                        layer.close(index);
                    });
                }
            });

            form.on('select(fTab2)', function (data) {
//            console.log(data.elem); //得到select原始DOM对象
                console.log(data.value); //得到被选中的值
//            console.log(data.othis); //得到美化后的DOM对象
                if (data.value == null || data.value == '') {
                    return;
                }
                <%--$.get('${pageContext.request.contextPath}/studentclass/studentclassdelete', {classId: obj.data.classId}, function (data) {--%>
                <%--console.log(data);--%>
                <%--layer.msg('删除成功！');--%>
                <%--obj.del();--%>
                <%--}, 'json');--%>

                console.log($(data.elem).text());
                console.log($(data.elem));
                table.reload('myTab', {
                    url: '${pageContext.request.contextPath}/studentclass/studentclasschoose'
                    , where: {falled: data.value} //设定异步数据接口的额外参数
                    //,height: 300
                });

            });
            table.on('toolbar(fTab)', function (obj) {
                var event = obj.event;
                console.log(event)

            })


        }
    );

    //添加班级
    function addstuclass() {
        layer.open({
            title: '添加班级',
            type: 2,
            content: ['${pageContext.request.contextPath}/studentclass/gotostudentclassadd', 'no'],
            area: ['900px', '480px'],
            maxmin:true,
            resize: false
        })
    }


</script>
</body>
</html>
