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
<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">员工姓名:</label>
            <div class="layui-input-inline" style="width: 120px">
                <input type="text" name="empName" id="empName" style="width: 120px" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">部门名称:</label>
            <div class="layui-input-inline" style="width: 120px">
                <select name="deptName" id="deptName">
                    <option value="">请选择部门</option>
                    <c:forEach items="${dept}" var="list">
                        <option value="${list.deptName}">${list.deptName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 90px">日期范围:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="workDay1"  id="date"  placeholder="yyyy-MM-dd" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="workDay2"  id="date1"  placeholder="yyyy-MM-dd" class="layui-input">
            </div>
        </div>
        <button class="layui-btn layui-btn-sm" lay-event="sousuo">搜索</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<%--日期弹出窗口--%>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });
    });
</script>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="cha">查看图片</a>
</script>

<script>
    layui.use(('table'), function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/aduitloglist',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '员工考核表',
            cols: [[
                {field:'aduitLogId',align:'center', title:'编号', width:120, fixed: 'left', unresize: true, sort: true},
                {field:'aduitName',align:'center', title:'考核内容', width:120},
                {field:'empName',align:'center', title:'员工姓名', width:120},
                {field:'score',align:'center', title:'考核分数', width:120},
                {field:'auditDate',align:'center', title:'考核时间', width:120},
                {field:'auditPerson',align:'center', title:'录入人', width:120},
                {field:'remark',align:'center', title:'说明', width:120},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo', width:150}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加考核指标',
                        type:2,
                        content:["${pageContext.request.contextPath}/MY/toaddaduitlog", "no"],
                        area:['480px', "500px"],
                        resize:false  //不能鼠标拖动改变大小
                    });
                    break;
                case 'sousuo':
                    var empName = $("#empName").val().trim();
                    var deptName = $("#deptName").val().trim();
                    var date = $("#date").val().trim();
                    var date1 = $("#date1").val().trim();
                    if (empName == "" && deptName == "" && date == "" && date1 == ""){
                        location.href='${pageContext.request.contextPath}/MY/toaduitlog';
                    }else{
                        table.reload('test', {
                            url: '${pageContext.request.contextPath}/MY/searchaduitlog'
                            ,where: {
                                empName:empName,
                                deptName:deptName,
                                date:date,
                                date1:date1
                            } //设定异步数据接口的额外参数
                        });
                    }


                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('您确定要删除该考核指标吗？', function(index){
                    //发异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/MY/deladuitlog",
                        async:true,
                        dataType:"text",
                        data:{aduitLogId:data.aduitLogId},
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
                });
            }
        });
    });
</script>
</body>
</html>