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
    <title>员工考核</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--顶部按钮--%>
<div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
    <button class="layui-btn layui-btn-sm" id="fan" onclick="fan()" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>

<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="cha">查看图片</a>
</script>

<script>
    layui.use(('table'), function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/RF/empassessment_aduitlog_list?empId=${empId}&date=${date}',
            // toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            totalRow: true,
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '员工考核表',
            cols: [[
                {field:'aduitLogId',align:'center', title:'编号', fixed: 'left', unresize: true, sort: true, totalRowText: '合计'},
                {field:'aduitName',align:'center', title:'考核内容'},
                {field:'empName',align:'center', title:'员工姓名'},
                {field:'score',align:'center', title:'考核分数', totalRow: true},
                {field:'auditDate',align:'center', title:'考核时间'},
                {field:'auditPerson',align:'center', title:'录入人'},
                {field:'remark',align:'center', title:'说明'},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo'}
            ]],
            page: true
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'cha'){
                var index = layer.open({
                    title:'查看详情',
                    type:2,
                    content:["${pageContext.request.contextPath}/MY/toaduitlog_list_id?aduitLogId=" + data.aduitLogId + "&aduitModelId=" + data.aduitModelId + "&empId=" + data.empId, "no"],
                    area:['600px', "600px"],
                    resize:false  //不能鼠标拖动改变大小
                });
            }
        });
    });

    //头部上传按钮点击事件
    function fan(){
        location.href='${pageContext.request.contextPath}/RF/toempassessmentlist?date=${param.date}';
    }
</script>
</body>
</html>