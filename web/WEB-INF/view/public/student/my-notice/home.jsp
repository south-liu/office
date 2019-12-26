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
    <title>公共发布</title>
    <jsp:include page="../../../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-btn-llb{
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }
    .layui-btn-llbb{
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body style="padding:30px">

<div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);">返回</button>
</div>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="see">查看</a>
</script>

<script>
    layui.use(['table','upload'], function(){
        var table = layui.table;
        var upload = layui.upload;

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/studentSide/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '公告表',
            cols: [[
                {field:'noticeId', title:'编号', sort: true,align: 'center'},
                {field:'title', title:'标题 ',align: 'center'},
                /*{field:'noticeType', title:'类别',align: 'center',templet:function (res) {
                        if (res.noticeType == 1){
                            return '所有人';
                        } else if (res.noticeType == 2) {
                            return '员工';
                        } else if (res.noticeType == 3) {
                            return '学生';
                        } else {
                            return 'error';
                        }
                    }},*/
                {field:'empName', title:'发布人',align: 'center'},
                {field:'noticeIime', title:'发布时间',sort: true, align: 'center'},
                /*{field:'', title:'已读人数',align: 'center',width:100},
                {field:'', title:'未读人数',align: 'center',width:100},*/
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center',width:240}
            ]],
            page: true
        });

        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'see'){
                layer.load();
                location.href='${pageContext.request.contextPath}/studentSide/viewNotice?noticeId='+data.noticeId;
            }
        });
    })
</script>
</body>
</html>
