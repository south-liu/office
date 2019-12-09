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
    <title>考试成绩</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">在读学期:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="term" id="term">
                    <option value="">请选择学期</option>
                    <c:forEach items="${term}" var="list">
                        <option value="${list.termId}">${list.termName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">班级名称:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="sclass" id="sclass">
                    <option value="">请选择班级</option>
                    <c:forEach items="${sclass}" var="list">
                        <option value="${list.className}">${list.className}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">考试类别:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="leibie" id="leibie">
                    <option value="">请选择类别</option>
                    <option value="1">笔试</option>
                    <option value="2">机试</option>
                    <option value="3">模拟面试</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">课程名称:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="course" id="course">
                    <option value="">请选择课程</option>
                    <c:forEach items="${course}" var="list">
                        <option value="${list.courseId}">${list.courseName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button class="layui-btn layui-btn-sm" lay-event="sousuo">搜索</button>
    </div>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/scorelist',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '楼栋管理表',
            cols: [[
                {field:'scoreId',align:'center', title:'编号', width:120, fixed: 'left', unresize: true, sort: true},
                {field:'stuName',align:'center', title:'学生姓名', width:120},
                {field:'score',align:'center', title:'分数', width:120},
                {field:'rescore',align:'center', title:'补考分数', width:120},
                {field:'courseName',align:'center', title:'课程名称', width:120},
                {field:'testType',align:'center', title:'考试类别', width:120},
                {field:'termName',align:'center', title:'考试学期', width:120},
                {field:'scoreTime',align:'center', title:'考试时间', width:120},
                {field:'empName',align:'center', title:'录入人员', width:120},
                {field:'remark',align:'center', title:'备注', width:120},
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    location.href='${pageContext.request.contextPath}/MY/toadd';
                    break;
                case 'sousuo':
                    var empName = $("#empName").val().trim();
                    var deptName = $("#deptName").val().trim();
                    var date = $("#date").val().trim();
                    var date1 = $("#date1").val().trim();
                    if (empName == "" && deptName == "" && date == "" && date1 == ""){
                        location.href='${pageContext.request.contextPath}/MY/toweekly_list';
                    }else{
                        var lindex = layer.load();
                        $.ajax({
                            type:"post",
                            url:"${pageContext.request.contextPath}/MY/toweekly_list2",
                            async:true,
                            dataType:"text",
                            data:{empName:empName, deptName:deptName, date:date, date1:date1},
                        });
                    }


                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'sel'){
                var lindex = layer.load();
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/MY/updfloor",
                    async:true,
                    dataType:"text",
                    data:{floorId:data.floorId},
                    success:function(data){
                        layer.close(lindex);
                        obj.del();
                        layer.close(index);
                        layer.msg('已删除!', {
                            icon: 1,
                            time: 1000
                        });
                    },
                    error:function () {
                        layer.close(lindex);
                        layer.msg("服务器错误");
                    }
                });
            }
        });
    });
</script>
</body>
</html>