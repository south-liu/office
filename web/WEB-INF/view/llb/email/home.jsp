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
    <title>发送邮件</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addEmail">写邮件</button>
    </div>
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="see">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div class="layui-tab layui-tab-card">
    <ul class="layui-tab-title">
        <li class="layui-this">收到的邮件</li>
        <li>发送的邮件</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-hide" id="myTab" lay-filter="fTab"></table>
        </div>
        <div class="layui-tab-item">
            <table class="layui-hide" id="myTab2" lay-filter="fTab2"></table>
        </div>
    </div>
</div>


<script>
    layui.use(['table','element'], function(){
        var table = layui.table;
        var element = layui.element;


        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/email/pageListReceiveEmail',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '接收的邮件',
            cols: [[
                {field:'emailReceiverId', title:'编号', sort: true,align: 'center'},
                {field:'empName', title:'发送人',align: 'center'},
                {field:'title', title:'标题',align: 'center'},
                {field:'sendTime', title:'接收时间',align: 'center',sort: true},
                {field:'isRead', title:'是否已读', align: 'center',templet:function (res) {
                        if (res.isRead==0){
                            return '未读';
                        } else {
                            return '已读';
                        }
                    }},
                {fixed: 'right', title:'操作',align: 'center',toolbar: '#bar'}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addEmail':
                    layer.load();
                    location.href='${pageContext.request.contextPath}/email/toAddEmail';
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'see'){
                layer.load();
                location.href='${pageContext.request.contextPath}/email/viewEmail?emailId='+data.emailId+'&emailReceiverId='+data.emailReceiverId;
            } else if(obj.event === 'del'){
                layer.msg('del');
                layer.confirm('确认删除此邮件吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/email/delEmail" ,
                        data: {emailReceiverId:data.emailReceiverId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                                window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            }
        });


        table.render({
            elem: '#myTab2',
            url:'${pageContext.request.contextPath}/email/pageListMySendEmail',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '发送的邮件',
            cols: [[
                {field:'emailReceiverId', title:'编号', sort: true,align: 'center'},
                {field:'empName', title:'接收人',align: 'center'},
                {field:'title', title:'标题',align: 'center'},
                {field:'sendTime', title:'发送时间',align: 'center',sort: true},
                {field:'isRead', title:'是否已读', align: 'center',templet:function (res) {
                        if (res.isRead==0){
                            return '未读';
                        } else {
                            return '已读';
                        }
                    }},
                {fixed: 'right', title:'操作',align: 'center',toolbar: '#bar'}
            ]],
            page: true
        });
        //头工具栏事件
        table.on('toolbar(fTab2)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addEmail':
                    layer.load();
                    location.href='${pageContext.request.contextPath}/email/toAddEmail';
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(fTab2)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'see'){
                layer.load();
                location.href='${pageContext.request.contextPath}/email/viewEmail?emailId='+data.emailId+'&emailReceiverId='+data.emailReceiverId;
            } else if(obj.event === 'del'){
                layer.msg('del');
                layer.confirm('确认删除此邮件吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/email/delEmail" ,
                        data: {emailReceiverId:data.emailReceiverId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                                window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            }
        });

    })
</script>
</body>
</html>
