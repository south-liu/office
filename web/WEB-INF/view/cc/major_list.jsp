<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/6
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../public/head.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>院系管理</title>
</head>
<style>
    .layui-form-label {
        float: left;
        display: block;
        padding: 9px 15px;
        width: 44px;
        font-weight: 400;
        line-height: 20px;
        text-align: right;
    }
</style>
<body>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<table class="layui-table"
       lay-data="{
        url:'${pageContext.request.contextPath}/major/majorlist',
        page:true,
        cellMinWidth: 80,
        id:'myTable',
        toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'majorId', sort: true, fixed: true}">序号</th>
        <th lay-data="{field:'majorName'}" >专业名称</th>
        <th lay-data="{field:'collegeDeptName'}" >系名称</th>
        <th lay-data="{field:'remark'}">说明</th>
        <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="upd">修改</a>
</script>
<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        //头工具栏事件
        table.on('toolbar(fTable)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加',
                        type:2,
                        content:'${pageContext.request.contextPath}/major/toaddmajor',
                        btnAlign: 'c',
                        area: ['460px', '400px'],
                        resize:false,
                    });
                    break;
            };
        });

        table.on('tool(fTable)', function(obj){
            switch(obj.event){
                case 'upd':
                    var index = layer.open({
                        title:'修改',
                        type:2,
                        content:'${pageContext.request.contextPath}/major/toupdmajor?majorId='+obj.data.majorId,
                        btnAlign: 'c',
                        area: ['460px', '400px'],
                        resize:false,
                    });
                    break;
                case 'del':
                    layer.confirm('确认删除', function (index) {
                        $.post('${pageContext.request.contextPath}/major/deletelist', {majorId: obj.data.majorId},function (data) {
                            layer.msg('删除成功！');
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/major/majorlist'
                            });
                        }, 'json');
                        layer.close(index);
                    });

            };
        });

    });
</script>
</body>
</html>