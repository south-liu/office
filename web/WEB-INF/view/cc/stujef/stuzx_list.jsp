<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/5
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../public/head.jsp"/>
<html>
<head>
    <title>在校情况</title>
</head>
<body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="fanhui">返回</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<table class="layui-table"
       lay-data="{
        height:530,
        url:'${pageContext.request.contextPath}/stujef/zxlist?stuId=${stuId}',
        page:true,
        cellMinWidth: 80,
        id:'myTable',
        toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <input id="stuId" value="${stuId}" type="hidden">
    <input id="empId" value="${empId}" type="hidden">
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'happenId', sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'stuName'}" >学生姓名</th>
        <th lay-data="{field:'happening'}">情况记录</th>
        <th lay-data="{field:'opTime'}">记录时间</th>
        <th lay-data="{field:'empName'}">记录人</th>
        <th lay-data="{fixed:'right', align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        <a class="layui-btn layui-btn-xs" lay-event="upd">修改</a>
    </script>

    <script>
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            var form = layui.form;

            table.on('tool(fTable)', function (obj) {
                switch(obj.event){
                    case 'upd':
                        var index = layer.open({
                            title:'修改',
                            type:2,
                            content:'${pageContext.request.contextPath}/stujef/toupdzx?happenId='+obj.data.happenId,
                            btnAlign: 'c',
                            area: ['460px', '300px'],
                            resize:false
                        });
                        break;
                    case 'del':
                        layer.confirm('确认删除', function (index) {
                            $.post('${pageContext.request.contextPath}/stujef/deletezx', {happenId: obj.data.happenId},function (data) {
                                layer.msg('删除成功！');
                                table.reload('myTable', {
                                    url: '${pageContext.request.contextPath}/stujef/zxlist?stuId=${stuId}'
                                });
                            }, 'json');
                            layer.close(index);
                        });

                };
            });

            //头工具栏事件
            table.on('toolbar(fTable)', function(obj){
                switch(obj.event){
                    case 'add':
                        var index = layer.open({
                            title:'添加',
                            type:2,
                            content:'${pageContext.request.contextPath}/stujef/toaddzx?stuId='+$('#stuId').val(),
                            btnAlign: 'c',
                            area: ['460px', '300px'],
                            resize:false
                        });
                        break;
                    case 'fanhui':
                        history.go(-1);
                        break;
                };
            });
        });
    </script>
</table>
</body>
</html>
