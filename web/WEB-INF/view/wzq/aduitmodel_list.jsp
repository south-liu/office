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
    <title>考核指标</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle" style="font-size: 15px; color: #FFF;"></i>添加指标</button>
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
            url:'${pageContext.request.contextPath}/MY/aduitmodel_list',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print'],
            title: '考核指标表',
            cols: [[
                {field:'aduitModelId',align:'center', title:'编号', fixed: 'left', unresize: true, sort: true},
                {field:'aduitName',align:'center', title:'考核内容'},
                {field:'scores',align:'center', title:'考核分数'},
                {field:'deptName',align:'center',title:'部门名称'},
                {field:'remark',align:'center',title:'说明'},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo'}
            ]],
            page: true
        });



        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加考核指标',
                        type:2,
                        content:["${pageContext.request.contextPath}/MY/toaddaduitmodel", "no"],
                        area:['430px', "400px"],
                        resize:false  //不能鼠标拖动改变大小
                    });
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('您确定要删除该考核指标吗？', function(index){
                    //发异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/MY/deladuitmodel",
                        async:true,
                        dataType:"text",
                        data:{aduitModelId:data.aduitModelId},
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
    });
</script>
</body>
</html>