<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/13
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>问题反馈</title>
    <jsp:include page="../../public/head.jsp"/>
    <style type="text/css">
        .laytable-cell-1-0-4 {
            width: 80px;
            height: 60px;
        }
    </style>
</head>
<body>
<table class="layui-hide" id="myTable" lay-filter="fTable">
</table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="upd">查看详情</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem:'#myTable',
            url:'${pageContext.request.contextPath}/feedback/stufeed_list',
            page:true,
            cellMinWidth: 80,
            toolbar:'#toolbarDemo',//头部工具栏
            title:'问题反馈',
            cols:[[
                {field:'feedbackId', title:'编号', sort: true,align: 'center'},
                {field:'empname', title:'姓名',align: 'center'},
                {field:'feedbackTime', title:'反馈时间',align: 'center'},
                {field:'remark', title:'建议',align: 'center'},
                {field:'image',width:80, title:'图片',align: 'center',templet:'<div><img  style="width: 80px;height: 80px" src="${pageContext.request.contextPath}/{{d.image}}"></img></div>'},
                {field:'status', title:'是否处理',align: 'center',templet: function(res){
                        if (res.status ==1) {
                            return '未处理'
                        } else {
                            return '已处理';
                        }
                    }},
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
                        content:'${pageContext.request.contextPath}/feedback/toadd',
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
                    layer.load();
                    window.location.href = '${pageContext.request.contextPath}/feedback/toMsg?feedbackId='+obj.data.feedbackId;
                    break;
                case 'del':
                    layer.confirm('确认删除', function (index) {
                        $.post('${pageContext.request.contextPath}/feedback/deletelist', {feedbackId: obj.data.feedbackId},function (data) {
                            layer.msg('删除成功！');
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/feedback/feed_list'
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
