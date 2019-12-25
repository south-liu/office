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
    <title>周报管理</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--顶部按钮--%>
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
                        <c:forEach items="${list}" var="list">
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
            <button class="layui-btn layui-btn-sm" id="sousuo" lay-event="sousuo"><i class="layui-icon layui-icon-search" style="font-size: 15px; color: #FFF;"></i>搜索</button>
        </div>
    </form>
</div>

<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<%--<script type="text/html" id="toolbarDemo">--%>
<%--    <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">--%>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label" style="width: 90px">员工姓名:</label>--%>
<%--            <div class="layui-input-inline" style="width: 120px">--%>
<%--                <input type="text" name="empName" id="empName" style="width: 120px" placeholder="请输入员工姓名" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label" style="width: 90px">部门名称:</label>--%>
<%--            <div class="layui-input-inline" style="width: 120px">--%>
<%--                <select name="deptName" id="deptName">--%>
<%--                    <option value="">请选择部门</option>--%>
<%--                    <c:forEach items="${list}" var="list">--%>
<%--                        <option value="${list.deptName}">${list.deptName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-inline">--%>
<%--            <label class="layui-form-label" style="width: 90px">日期范围:</label>--%>
<%--            <div class="layui-input-inline" style="width: 100px;">--%>
<%--                <input type="text" name="workDay1"  id="date"  placeholder="yyyy-MM-dd" class="layui-input">--%>
<%--            </div>--%>
<%--            <div class="layui-form-mid">-</div>--%>
<%--            <div class="layui-input-inline" style="width: 100px;">--%>
<%--                <input type="text" name="workDay2"  id="date1"  placeholder="yyyy-MM-dd" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <button class="layui-btn layui-btn-sm" lay-event="sousuo"><i class="layui-icon layui-icon-search" style="font-size: 15px; color: #FFF;"></i>搜索</button>--%>
<%--    </div>--%>
<%--</script>--%>

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
    <a class="layui-btn layui-btn-xs" lay-event="cha">查看</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/weelist',
            // toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '周报管理表',
            cols: [[
                {field:'weeklyId',align:'center', title:'周报编号', fixed: 'left', unresize: true, sort: true},
                {field:'empName',align:'center', title:'员工姓名'},
                {field:'workDay',align:'center', title:'填写日期'},
                {field:'weekCur',align:'center', title:'本周情况描述'},
                {field:'studentQuestion',align:'center', title:'问题学生情况反馈'},
                {field:'idea',align:'center', title:'意见建议'},
                {field:'weekNext',align:'center', title:'下周工作计划'},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo'}
            ]],
            page: true
        });

        //搜索按钮（这样写可以多次查询且只刷新表格）【把头部按钮单独提出来放在一个div中而不是加入数据表格中】
        $("#sousuo").click(function () {
            var empName = $("#empName").val().trim();
            var deptName = $("#deptName").val().trim();
            var date = $("#date").val().trim();
            var date1 = $("#date1").val().trim();
            if (empName == "" && deptName == "" && date == "" && date1 == ""){
                location.href='${pageContext.request.contextPath}/MY/toweekly_list';
            }else{
                table.reload('test', {
                    url: '${pageContext.request.contextPath}/MY/weelist2'
                    ,where: {
                        empName:empName,
                        deptName:deptName,
                        date:date,
                        date1:date1
                    } //设定异步数据接口的额外参数
                });
            }
        })

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'cha'){
                location.href='${pageContext.request.contextPath}/MY/toweekly_list_table?weeklyId=' + data.weeklyId + "&empId=" + data.empId;
            }
            if (obj.event == 'del'){
                layer.confirm('您确定要删除该周报吗？', function(index){
                    //异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/MY/delweekly",
                        async:true,  //true为异步请求，false为同步请求。
                        dataType:"text",  //请求返回的数据类型格式。
                        data:{weeklyId:data.weeklyId},
                        success:function(data) {
                            layer.close(lindex);
                            obj.del();
                            layer.close(index);
                            layer.msg('已删除！', {
                                icon:1,
                                time:1000
                            });
                        },
                        error:function () {
                            layer.close(lindex);
                            layer.msg("服务器错误！");
                        }
                    });
                });
            }
        });
    });
</script>
</body>
</html>