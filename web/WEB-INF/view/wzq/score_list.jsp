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
    <title>考试成绩</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--顶部按钮--%>
<div class="layui-card-body">
    <form class="layui-form" action="" onsubmit="return false;">
        <div class="layui-form-item" style="padding-top: 8px;margin-bottom: 0px">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">在读学期:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="term" id="term">
                        <option value="">请选择学期</option>
                        <c:forEach items="${term}" var="list">
                            <option value="${list.termName}">${list.termName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">班级名称:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="sclass" id="sclass">
                        <option value="">请选择班级</option>
                        <c:forEach items="${sclass}" var="list">
                            <option value="${list.className}">${list.className}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">考试类别:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="leibie" id="leibie">
                        <option value="">请选择类别</option>
                        <option value="1">笔试</option>
                        <option value="2">机试</option>
                        <option value="3">模拟面试</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 90px">课程名称:</label>
                <div class="layui-input-inline" style="width: 120px">
                    <select name="course" id="course">
                        <option value="">请选择课程</option>
                        <c:forEach items="${course}" var="list">
                            <option value="${list.courseName}">${list.courseName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="layui-btn layui-btn-sm" id="sousuo" lay-event="sousuo"><i class="layui-icon layui-icon-search" style="font-size: 15px; color: #FFF;"></i>搜索</button>
        </div>
    </form>
</div>

<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>



<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/scorelist',
            // toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板(一般放置按钮、搜索框)
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '楼栋管理表',
            cols: [[
                {field:'scoreId',align:'center', title:'编号', fixed: 'left', unresize: true, sort: true},
                {field:'stuName',align:'center', title:'学生姓名'},
                {field:'score',align:'center', title:'分数'},
                {field:'rescore',align:'center', title:'补考分数'},
                {field:'courseName',align:'center', title:'课程名称'},
                {field:'testType',align:'center', title:'考试类别', templet: function (res) {
                        if (res.testType == 1){
                            return '<span>笔试</span>'
                        }
                        if (res.testType == 2){
                            return '<span>机试</span>'
                        }
                        if (res.testType == 3){
                            return '<span>模拟面试</span>'
                        }
                    }},
                {field:'termName',align:'center', title:'考试学期'},
                {field:'scoreTime',align:'center', title:'考试时间'},
                {field:'empName',align:'center', title:'录入人员'},
                {field:'remark',align:'center', title:'备注'},
            ]],
            page: true
        });

        //搜索按钮（这样写可以多次查询且只刷新表格）【把头部按钮单独提出来放在一个div中而不是加入数据表格中】
        $("#sousuo").click(function () {
            var term = $("#term").val().trim();
            var sclass = $("#sclass").val().trim();
            var leibie = $("#leibie").val().trim();
            var course = $("#course").val().trim();
            if (term == "" && sclass == "" && leibie == "" && course == ""){
                location.href='${pageContext.request.contextPath}/MY/toscore_list';
            }else{
                table.reload('test', {
                    url: '${pageContext.request.contextPath}/MY/selscorelist'
                    ,where: {
                        term:term,
                        sclass:sclass,
                        leibie:leibie,
                        course:course
                    } //设定异步数据接口的额外参数
                });
            }
        })
    });
</script>
</body>
</html>