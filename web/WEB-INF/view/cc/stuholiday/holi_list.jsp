<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/17
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../public/head.jsp"/>
<html>
<head>
    <title>学生请假</title>
</head>
<style>
    .layui-form-label {
        float: left;
        display: block;
        padding: 9px 15px;
        width: 44px;
        font-weight: 400;
        line-height: 20px;
        text-align: right;
    }
    .layui-form-switch {
         margin-top: 0px;
    }
    input#stuName {
        width: 90px;
    }
</style>
<body>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<div class="layui-card-body ">
    <form class="layui-form layui-col-space5" action="" onsubmit="return false;" lay-filter="example">
        <div class="layui-inline layui-show-xs-block">
            <input type="text" id="stuName" name="stuName"  placeholder="学生姓名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline layui-show-xs-block">
            <input type="radio" name="mouth" value="1" title="本月" checked="">
            <input type="radio" name="mouth" value="2" title="全部月份">
            <input type="radio" name="mouth" value="3" title="上月">
        </div>
        <div class="layui-inline layui-show-xs-block">
            <button class="layui-btn" id="seacch" ><i class="layui-icon">&#xe615;</i></button>
        </div>
    </form>
</div>

<table class="layui-table"
       lay-data="{
        url:'${pageContext.request.contextPath}/stuholi/holi_list',
        page:true,
        cellMinWidth: 80,
        id:'myTable'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'studentId', sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'stuName'}" >学生姓名</th>
        <th lay-data="{field:'sum'}">请假次数</th>
        <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn  layui-btn-xs" lay-event="particulars">详情</a>
</script>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        table.on('tool(fTable)', function (obj) {
            var month = form.val('example');
            switch(obj.event){
                case 'particulars':
                    var index = layer.open({
                        title:'请假详情',
                        type:2,
                        content:'${pageContext.request.contextPath}/stuholi/toptcls?stuId='+obj.data.studentId+'&sum='+obj.data.sum+'&mon='+month.mouth,
                        btnAlign: 'c',
                        area: ['900px', '400px'],
                        resize:false
                    });
                    break;
            };
        });

        //搜索
        $('#seacch').click(function () {
            var stuName = $('#stuName').val().trim();
            var data = form.val('example');

            table.reload('myTable',{
                url:'${pageContext.request.contextPath}/stuholi/pageListWhere',
                where:{
                    stuName:stuName,
                    sta:data.mouth
                }
            });
        });
    });
</script>
</body>
</html>