<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/11
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>设备维修管理</title>
    <jsp:include page="../../public/head.jsp"/>
</head>
<style>
    .llayui-btn-disabled {
        border: 1px solid #e6e6e6;
        background-color: #FBFBFB;
        color: #C9C9C9;
        opacity: 1;
    }
</style>
<body>
<table class="layui-hide" id="myTable" lay-filter="fTable"></table>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem:'#myTable',
            url:'${pageContext.request.contextPath}/repair/sturepair_list',
            page:true,
            cellMinWidth: 80,
            toolbar:'#toolbarDemo',//头部工具栏
            title:'报修申请',
            cols:[[
                {type: 'checkbox', fixed: 'left'},
                {field:'equipmentId', title:'ID', sort: true,align: 'center'},
                {field:'equipmentType', title:'设备名称',align: 'center'},
                {field:'proposer', title:'申请人',align: 'center'},
                {field:'classOrDept', title:'班级或部门',align: 'center'},
                {field:'startTime', title:'开始时间',align: 'center'},
                {field:'endTime', title:'结束时间',align: 'center'},
                {field:'status', title:'维修状态',align: 'center',templet: function(res){
                        if (res.status ==0) {
                            return '<span  class="layui-btn-zt layui-btn-zty layui-btn-normal layui-btn-mini llayui-btn-disabled">未完成</span>'
                        } else {
                            return '<button type="button" class="layui-btn layui-btn-xs">已经完成</button>';
                        }
                    }},
                {field:'remark', title:'备注',align: 'center'},
                {fixed: 'right', title:'操作', toolbar: '#barDemo',align: 'center'},
            ]],
            page:true
        });

        //头工具栏事件
        table.on('toolbar(fTable)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加',
                        type:2,
                        content:'${pageContext.request.contextPath}/repair/tostuadd',
                        btnAlign: 'c',
                        area: ['460px', '400px'],
                        resize:false,
                    });
                    break;
            };
        });
        table.on('tool(fTable)', function(obj){
            switch(obj.event){
                case 'del':
                    layer.confirm('确认删除', function (index) {
                        $.post('${pageContext.request.contextPath}/repair/deletelist', {equipmentId: obj.data.equipmentId},function (data) {
                            layer.msg('删除成功！');
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/repair/repair_list'
                            });
                        }, 'json');
                        layer.close(index);
                    });
            };
        });
    });

    function zt(obj,equipmentId){
        layer.confirm('是否修改状态为已完成',function () {
            var lod = layer.load();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${pageContext.request.contextPath}/repair/updlist" ,
                data: {equipmentId:equipmentId,status:1},
                success: function (result) {
                    layer.close(lod);
                    layer.msg('修改成功',{
                        time:1000
                    },function () {
                        window.location.reload();
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
        })
    };
</script>
</body>
</html>