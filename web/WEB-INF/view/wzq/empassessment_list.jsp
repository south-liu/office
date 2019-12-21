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
    <title>员工考核报表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<div class="layui-card-body">
    <form class="layui-form" action="" onsubmit="return false;">
        <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">员工姓名:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <input type="text" name="empName" id="empName" style="width: 120px" placeholder="请输入员工姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">部门名称:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="deptName" id="deptName">
                        <option value="">请选择部门</option>
                        <c:forEach items="${emp}" var="list">
                            <option value="${list.deptName}">${list.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <button class="layui-btn layui-btn-sm" id="sousuo" lay-event="sousuo" style="margin-left: 20px"><i class="layui-icon layui-icon-search" style="font-size: 15px; color: #FFF;"></i>搜索</button>

            <div class="layui-inline" style="margin-left: 110px">
                <label class="layui-form-label" style="width: 300px; font-size: 20px">
                    当前审核时间:
                    <span id="_span"></span>
                </label>
            </div>

            <div class="layui-inline" style="margin-left:150px">
                <label class="layui-form-label" style="width: 90px">审核时间:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="date1" id="date1" lay-filter="TB">
                        <option value="">请选择时间</option>
                        <c:forEach items="${aduitlog}" var="list">
                            <option value="${list.dates}">${list.dates}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>
    </form>
</div>

<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="cha">查看详情</a>
</script>

<script type="text/javascript">

    // var date = new Date();
    // $('#_span').text(date.getFullYear()+'年'+(date.getMonth() + 1)+'月');

    //初始当前审核时间，获取初始传过来的值
    $('#_span').text('${date}')

    layui.use(['table', 'form'], function(){
        var table = layui.table,
            form = layui.form;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/RF/empassessment_list?date=${date}',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '员工考核报表',
            cols: [[
                {field:'empId',align:'center', title:'员工编号', fixed: 'left', unresize: true, sort: true},
                {field:'empName',align:'center', title:'员工姓名'},
                {field:'deptName',align:'center', title:'部门名称'},
                {field:'sex',align:'center', title:'性别'},
                {field:'phone',align:'center', title:'手机号码'},
                {field:'zongscore',align:'center', title:'考核总分', templet:function (res) {
                        if (res.zongscore == null){
                            return 100;
                        }else {
                            return res.zongscore;
                        }
                    }},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo', width:160}
            ]],
            page: true
        });

        //搜索按钮（这样写可以多次查询且只刷新表格）【把头部按钮单独提出来放在一个div中而不是加入数据表格中】
        $("#sousuo").click(function () {
            var empName = $("#empName").val().trim();
            var deptName = $("#deptName").val().trim();
            var date1 = $("#date1").val().trim();
            if (empName == "" && deptName == "" && date1 == ""){
               location.href='${pageContext.request.contextPath}/RF/toempassessment';
            }else{
                table.reload('test', {
                    url: '${pageContext.request.contextPath}/RF/empassessment_list_sousuo'
                    ,where: {
                        empName:empName,
                        deptName:deptName,
                        date1:date1
                    } //设定异步数据接口的额外参数
                });
            }
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;

            if(obj.event === 'cha'){
                var date1 = $("#date1").val().trim();
                <%--获取URL参数:${param.date1}--%>
                if (date1 != ""){
                    location.href="${pageContext.request.contextPath}/RF/toempassessment_aduitlog_list?empId=" + data.empId + "&date=" + date1;
                }else {
                    location.href='${pageContext.request.contextPath}/RF/toempassessment_aduitlog_list?empId=' + data.empId + "&date=${date}";
                }
            }
        });

        form.on('select(TB)', function (data) {
            if (data.value == null || data.value == ""){
                return;
            }else {
                //当选择审核时间下拉框时替换显示当前时间的值
                $('#_span').text($('#date1').val());
                table.reload('test',{
                    url:'${pageContext.request.contextPath}/RF/empassessment_list_sousuo',
                    where:{
                        empName:"",
                        deptName:"",
                        date1:data.value
                    }
                })
            }
        })
    });
</script>
</body>
</html>