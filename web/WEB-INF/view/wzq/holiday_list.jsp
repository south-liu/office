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
    <title>员工请假</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
        <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle" style="font-size: 15px; color: #FFF;"></i>申请请假</button>
        <button class="layui-btn layui-btn-sm" lay-event="task"><i class="layui-icon layui-icon-tree" style="font-size: 15px; color: #FFF;"></i>我的任务</button>
        <button class="layui-btn layui-btn-sm" lay-event="ltask"><i class="layui-icon layui-icon-rate-solid" style="font-size: 15px; color: #FFF;"></i>历史任务</button>
    </div>
</script>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="cha">查看批注</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/TP/myholidaylist?empId=${emp.empId}',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '我的请假申请表',
            cols: [[
                {field:'holidayId',align:'center', title:'编号', width:80, fixed: 'left', unresize: true, sort: true},
                {field:'empName',align:'center', title:'请假人', width:120},
                {field:'dayStr',align:'center', title:'请假时长', width:120},
                {field:'startTime',align:'center', title:'开始时间', width:160},
                {field:'endTime',align:'center', title:'结束时间', width:160},
                {field:'remark',align:'center', title:'请假事由', width:120},
                {field:'type',align:'center', title:'类型', width:120},
                {field:'status',align:'center', title:'状态', width:120, templet: function (res) {
                        if (res.status == 1){
                            return "审批中";
                        }
                        if (res.status == 2){
                            return "已完成";
                        }
                        if (res.status == 3){
                            return "不批准";
                        }
                    }},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo', width:160}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加请假申请',
                        type:2,
                        content:["${pageContext.request.contextPath}/TP/toaddholiday", "no"],  <%-- ${param.studId}获取URL框中段递过来的参数 --%>
                        area:['460px', "480px"],
                        resize:false  //不能鼠标拖动改变大小
                    });
                    break;
                case 'task':
                    var lod = layer.load();
                    location.href='${pageContext.request.contextPath}/TP/mytaskholidaylist?empId=${emp.empId}';
                    break;
                case 'ltask':
                    var lod = layer.load();
                    location.href='${pageContext.request.contextPath}/TP/historyTask?empId=${emp.empId}';
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;

            if(obj.event === 'cha'){
                var index = layer.open({
                    title:'批注',
                    type:2,
                    content:["${pageContext.request.contextPath}/TP/pizhuholiday?holidayId=" + data.holidayId, "no"],  <%-- ${param.studId}获取URL框中段递过来的参数 --%>
                    area:['500px', "300px"],
                    resize:false  //不能鼠标拖动改变大小
                });
                <%--var lod = layer.load();--%>
                <%--location.href='${pageContext.request.contextPath}/TP/pizhuholiday?holidayId=' + data.holidayId;--%>
            }

        });
    });
</script>
</body>
</html>