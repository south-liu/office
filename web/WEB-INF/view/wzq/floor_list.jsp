<%--
  Created by IntelliJ IDEA.
  User: 南城
  Date: 2019/11/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>楼栋管理</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle" style="font-size: 15px; color: #FFF;"></i>添加楼栋</button>
    </div>
</script>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/floorlist',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '楼栋管理表',
            cols: [[
                {field:'floorId',align:'center', title:'楼栋编号', width:120, fixed: 'left', unresize: true, sort: true},
                {field:'floorName',align:'center', title:'楼栋名称', width:120, edit: 'text'},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo', width:100}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加楼栋',
                        type:1,
                        content:'<div style="margin-top: 20px" class="layui-inline">\n' +
                                    '<label class="layui-form-label">楼栋名称</label>\n' +
                                    '<div class="layui-input-inline">\n' +
                                        '<input type="text" id="loudong" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                                    '</div>\n' +
                                '</div>'
                        ,
                        btn: ['确定', '取消'],
                        yes: function(index, layero){
                            var loudong = $('#loudong').val().trim();
                            if (loudong == '') {
                                layer.msg('请输入楼栋名称!', {
                                    icon: 2,  //显示的图片
                                    time: 1000  //暂停1秒
                                });
                            }else {
                                var lindex = layer.load();
                                $.ajax({
                                    type:"post",
                                    url:"${pageContext.request.contextPath}/MY/addfloor",
                                    async:true,
                                    dataType:"text",
                                    data:{floorName:loudong},
                                    success:function(data){
                                        layer.close(lindex);
                                        layer.msg('添加成功!', {
                                            icon: 1,
                                            time: 1000
                                        },function () {
                                            layer.close(index);
//                                            location.reload();
                                            table.reload('test', {
                                                url: '${pageContext.request.contextPath}/MY/floorlist'
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
                        area: ['400px', '180px']
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示按钮
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('您确定要删除该楼栋吗？', function(index){
                    //发异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/MY/updfloor",
                        async:true,
                        dataType:"text",
                        data:{floorId:data.floorId},
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

        //监听单元格编辑(学期名称)
        table.on('edit(test)', function(obj){
            var lindex = layer.load();
            var data = obj.data //得得到所在行的的对应列的值
                ,value = obj.value //得到修改后的值
                ,field = obj.field; //得到字段
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/MY/delfloor",
                async:true,
                dataType:"text",
                data:{floorId:data.floorId,floorName:data.floorName},
                success:function(data){

                    layer.close(lindex);
                    layer.msg('修改成功');
                },
                error:function () {
                    layer.close(lindex);
                    layer.msg("服务器错误");
                }
            });
        });
    });
</script>
</body>
</html>