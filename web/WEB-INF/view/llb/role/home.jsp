<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色列表</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-btn-llb{
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }
    .layui-btn-llbb{
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addFile">添加角色</button>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="editRole">编辑权限</a>
    <a class="layui-btn layui-btn-xs" lay-event="grant">用户授权</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div id="addFile" style="display: none;padding: 20px 35px 20px 0px;">
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称：</label>
        <div class="layui-input-inline">
            <input type="text" id="characterName" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-input-block" style="margin-left: 208px;">
        <button type="submit" class="layui-btn" id="sub">确定添加</button>
    </div>
</div>

<script>
    layui.use(['table'], function(){
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/menu/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '角色表',
            cols: [[
                {field:'characterId', title:'编号', sort: true,align: 'center'},
                {field:'characterName', title:'角色名称 ',edit:'text',align: 'center'},
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center'}
            ]],
            page: true
        });


        table.on('edit(fTab)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            if (obj.value.trim() == '') {
                layer.msg('角色名称不能为空',{
                    time:1000,
                    icon:0
                },function () {
                    table.reload('myTab',{
                        url:'${pageContext.request.contextPath}/menu/pageList'
                    });
                });
                return false;
            }
            var lod = layer.load();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${pageContext.request.contextPath}/menu/editRole" ,
                data: {characterId:obj.data.characterId,characterName:obj.value},
                success: function (result) {
                    layer.close(lod);
                    layer.msg('修改成功',{
                        icon:1,
                        time:1000
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误',{
                        icon:2
                    });
                }
            });
        });


        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addFile':
                    layer.open({
                        title:'添加角色',
                        type:1,
                        content:$('#addFile'),
                        area: ['500px', '200px'],
                        resize:false,
                        // maxmin:true
                    });
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除此角色吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/menu/delRole" ,
                        data: {characterId:data.characterId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                               window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            } else if(obj.event === 'editRole'){
                layer.open({
                    title:'编辑权限',
                    type:2,
                    content:'${pageContext.request.contextPath}/menu/toEditPower?characterId='+data.characterId,
                    area: ['300px', '430px'],
                    resize:false
                });
            } else if (obj.event === 'grant') {
                layer.open({
                    title:'用户授权',
                    type:2,
                    content:'${pageContext.request.contextPath}/menu/toGrant?characterId='+data.characterId,
                    area: ['300px', '430px'],
                    resize:false
                });
            }
        });

        //点击添加
        $('#sub').click(function () {
            var characterName = $('#characterName').val().trim();
            if (characterName == ""){
                layer.msg('请填写角色名称',{
                    icon:0
                });
                return;
            }
            var lod = layer.load();
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/menu/addRole',
                async:true,
                dataType:'json',
                data:{characterName:characterName},
                success:function (data) {
                    layer.close(lod);
                    layer.msg('添加成功',{
                        icon:1,
                        time:2000
                    },function(){
                        location.reload();
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误，请稍后再试！',{
                        icon:2
                    });
                }
            });

        });
    })
</script>
</body>
</html>
