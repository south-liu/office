<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/6
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>值班管理</title>
    <jsp:include page="../public/head.jsp"/>
</head>
<body>
<table class="layui-hide" id="myTable" lay-filter="fTable"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="upd">修改</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem:'#myTable',
            url:'${pageContext.request.contextPath}/weekang/ang_list',
            page:true,
            cellMinWidth: 80,
            toolbar:'#toolbarDemo',//头部工具栏
            title:'值班管理',
            cols:[[
                {type: 'checkbox', fixed: 'left'},
                {field:'weekArrangeId', title:'ID', sort: true,align: 'center'},
                {field:'orderId', title:'排序编号', sort: true,align: 'center'},
                {field:'week', title:'星期',align: 'center'},
                {field:'weekArrangeName', title:'排班名称',align: 'center'},
                {field:'empName', title:'员工',align: 'center'},
                {field:'ranges', title:'值班要求',align: 'center'},
                {field:'duty', title:'总值班',align: 'center',templet: function(res){
                        if (res.duty ==0) {
                            return '否'
                        } else {
                            return '是';
                        }
                    }},
                {field:'remark', title:'说明',align: 'center'},
                {fixed: 'right', title:'操作', toolbar: '#barDemo',align: 'center'},
            ]],
            page:true
        });

        //头工具栏事件
        table.on('toolbar(fTable)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加',
                        type:2,
                        content:'${pageContext.request.contextPath}/weekang/toaddang',
                        btnAlign: 'c',
                        area: ['460px', '650px'],
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
                        content:'${pageContext.request.contextPath}/weekang/toupdang?weekArrangeId='+obj.data.weekArrangeId,
                        btnAlign: 'c',
                        area: ['460px', '500px'],
                        resize:false,
                    });
                    break;
                case 'del':
                    layer.confirm('确认删除', function (index) {
                        $.post('${pageContext.request.contextPath}/weekang/deletelist', {angId: obj.data.weekArrangeId},function (data) {
                            layer.msg('删除成功！');
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/weekang/ang_list'
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