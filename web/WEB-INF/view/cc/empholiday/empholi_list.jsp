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
    <title>员工请假</title>
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


<div class="layui-card-body ">
    <form class="layui-form layui-col-space5" action="" onsubmit="return false;" lay-filter="example">
        <div class="layui-inline layui-show-xs-block">
            <input type="text" id="empName" name="empName"  placeholder="员工姓名" autocomplete="off" class="layui-input">
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
        url:'${pageContext.request.contextPath}/empholi/holi_list',
        id:'myTable'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{field:'Id', sort: true, fixed: true,align: 'center'}">ID</th>
        <th lay-data="{field:'empName',align: 'center'}" >员工姓名</th>
        <th lay-data="{field:'sum',align: 'center'}">请假次数</th>
        <th lay-data="{field:'totalitySum',align: 'center'}">请假总天数</th>
        <th lay-data="{field:'hour',align: 'center'}">请假总小时</th>
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
                        content:'${pageContext.request.contextPath}/empholi/toptcls?empId='+obj.data.Id+'&sum='+obj.data.sum+'&mon='+month.mouth,
                        btnAlign: 'c',
                        area: ['900px', '400px'],
                        resize:false
                    });
                    break;
            };
        });

        //搜索
        $('#seacch').click(function () {
            var empName = $('#empName').val().trim();
            var data = form.val('example');

            table.reload('myTable',{
                url:'${pageContext.request.contextPath}/empholi/pageListWhere',
                where:{
                    empName:empName,
                    sta:data.mouth
                }
            });
        });
    });
</script>
</body>
</html>