<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/4
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../public/head.jsp"/>
<html>
<head>
    <title>院系管理</title>
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
</style>
<body>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<table class="layui-table"
       lay-data="{width: 975,
        height:530,
        url:'${pageContext.request.contextPath}/Chengcollege/collegelist',
        page:true,
        cellMinWidth: 80,
        id:'myTable',
        toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'collegeDeptId', sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'collegeDeptName',edit:'text'}" >系名称</th>
        <th lay-data="{field:'remark',edit:'text'}">说明</th>
        <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        table.on('tool(fTable)', function (obj) {
            var event = obj.event;
            if (event === 'del') {
                layer.confirm('确认删除', function (index) {
                    $.post('${pageContext.request.contextPath}/Chengcollege/deletelist', {collegeDeptId: obj.data.collegeDeptId},function (data) {
                        layer.msg('删除成功！');
                        table.reload('myTable', {
                            url: '${pageContext.request.contextPath}/Chengcollege/collegelist'
                        });
                    }, 'json');
                    layer.close(index);

                });
            }
        });

        //头工具栏事件
        table.on('toolbar(fTable)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加',
                        type:1,
                        content:'<div style="padding: 30px 63px 30px 34px;" class="layui-inline" >\n' +
                            '        <label class="layui-form-label">系名称</label>\n' +
                            '        <div class="layui-input-inline">\n' +
                            '            <input type="text" id="collegeDeptName" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                            '        </div><div style="height: 30px"></div>' +
                            '        <label class="layui-form-label">说明</label>\n' +
                            '        <div class="layui-input-inline">\n' +
                            '            <textarea type="text" id="remark" lay-verify="required" placeholder="请输入院系说明" class="layui-textarea"></textarea>\n' +
                            '        </div>\n' +
                            '    </div>',
                        btn: ['确定', '取消'],
                        yes: function(index, layero){
                            var collegeDeptName = $('#collegeDeptName').val().trim();
                            var remark = $('#remark').val().trim();
                            if (collegeDeptName == ''||remark == '') {
                                layer.msg('请完整填写信息!',{
                                    icon: 2,
                                    time: 1000
                                });
                            } else {
                                var lindex = layer.load();
                                $.ajax({
                                    type:"post",
                                    url:"${pageContext.request.contextPath}/Chengcollege/addlist",
                                    async:true,
                                    dataType:"text",
                                    data:{collegeDeptName:collegeDeptName,remark:remark},
                                    success:function(data){
                                        layer.close(lindex);
                                        layer.msg('添加成功!', {
                                            icon: 1,
                                            time: 1000
                                        },function () {
                                            layer.close(index);
                                            // location.reload();
                                            table.reload('myTable', {
                                                url: '${pageContext.request.contextPath}/Chengcollege/collegelist'
                                            });
                                        });
                                    },
                                    error:function () {
                                        layer.close(lindex);
                                        layer.msg("服务器错误");
                                    }
                                });
                            }
                        },
                        btnAlign: 'c',
                    });
                    break;
            };
        });
        table.on('edit(fTable)', function(obj){
                var value = obj.value //得到修改后的值
                    ,data = obj.data //得到所在行所有键值
                    ,field = obj.field; //得到字段
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/Chengcollege/updlist",
                    async:true,
                    dataType:"text",
                    data:{collegeDeptId:data.collegeDeptId,collegeDeptName:data.collegeDeptName,remark:data.remark},
                    success:function(data){
                        layer.close(lindex);
                        layer.msg('修改成功!', {
                            icon: 1,
                            time: 1000
                        },function () {
                            layer.close(index);
                            // location.reload();
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/Chengcollege/collegelist'
                            });
                        });
                    },
                    error:function () {
                        layer.close(lindex);
                        layer.msg("服务器错误", {
                            icon: 1,
                            time: 1000
                        });
                }
            });
            });

    });
</script>
</body>
</html>