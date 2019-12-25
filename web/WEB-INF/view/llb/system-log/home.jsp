<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统日志</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>

<div class="layui-card-body ">
    <form class="layui-form layui-col-space5" action="" onsubmit="return false;">
        <div class="layui-inline layui-show-xs-block">
            <input type="text" id="startTime"  placeholder="开始时间" class="layui-input">
        </div>
        <div class="layui-inline layui-show-xs-block">
            <input type="text" id="endTime" placeholder="结束时间" class="layui-input">
        </div>
        <div class="layui-inline layui-show-xs-block">
            <button class="layui-btn" id="seacch" ><i class="layui-icon">&#xe615;</i></button>
        </div>
    </form>
</div>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script>
    layui.use(['table','laydate'], function(){
        var table = layui.table;
        var laydate = layui.laydate;

        laydate.render({
            elem:'#startTime'
        });
        laydate.render({
            elem:'#endTime'
        });

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/systemLog/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '系统日志表',
            cols: [[
                {field:'logId', title:'编号', sort: true,align: 'center'},
                {field:'empName', title:'操作人',align: 'center'},
                {field:'ipAddr', title:'IP地址',align: 'center'},
                {field:'optime', title:'操作时间',align: 'center'},
                {field:'tables', title:'操作的数据表', align: 'center'},
                {field: 'msg', title:'操作内容',align: 'center'}
            ]],
            page: true
        });

        //搜索
        $('#seacch').click(function () {
            var startTime = $('#startTime').val().trim();
            var endTime = $('#endTime').val().trim();
            table.reload('myTab',{
                url:'${pageContext.request.contextPath}/systemLog/pageListWhere',
                where:{
                    startTime:startTime,
                    endTime:endTime
                }
            });
        });
    })
</script>
</body>
</html>
