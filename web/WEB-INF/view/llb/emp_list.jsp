<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
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
        <button class="layui-btn layui-btn-sm" lay-event="addEmp">添加员工</button>
        <button class="layui-btn layui-btn-sm" lay-event="delEmps">删除选中</button>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="jobInfo">工作经历</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="eduInfo">教育经历</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="famInfo">家庭信息</a>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/emp/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '员工数据表',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field:'empId', title:'编号', sort: true,align: 'center'},
                {field:'empName', title:'员工姓名 ',align: 'center'},
                {field:'deptName', title:'部门',align: 'center',templet: function(res){
                        if (res.deptName == '' || res.deptName == null) {
                            return '无'
                        } else {
                            return res.deptName;
                        }
                    }},
                {field:'postName', title:'职务',align: 'center'},
                {field:'sex', title:'性别',sort: true,align: 'center'},
                {field:'phone', title:'手机号码', align: 'center'},
                {field:'address', title:'家庭地址',align: 'center'},
                {field:'status', title:'状态',align: 'center', templet: function(res){
                    if (res.status == 0) {
                        return '<span class="layui-btn-llb layui-btn-normal layui-btn-mini layui-btn-disabled">已禁用</span>'
                    } else {
                        return '<span class="layui-btn-llb layui-btn-llbb layui-btn-normal layui-btn-mini">已启用</span>';
                    }
                }},
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center'},
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addEmp':
                    layer.open({
                        title:'添加员工',
                        type:2,
                        content:'${pageContext.request.contextPath}/emp/toAdd',
                        area: ['720px', '500px'],
                        resize:false,
                        maxmin:true
                    });
                    break;
                case 'delEmps':
                    var ids = [];
                    var emps = checkStatus.data;
                    for (var i = 0;i < length;i++) {
                        ids.push(emps[i].empId);
                    }
                    var idstr = ids.join(',');
                    console.log(idstr);
                    if (length != 0) {
                        layer.confirm('确认删除吗', function(index){
                            var lod = layer.load();
                            $.ajax({
                                type: "POST",
                                dataType: "json",
                                url: "${pageContext.request.contextPath}/emp/delEmp" ,
                                data: {idstr:idstr},
                                success: function (result) {
                                    layer.close(lod);
                                    layer.msg('删除成功',{
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
                        });
                    } else {
                        layer.msg('请选择一个员工');
                    }
                    break;
                case 'jobInfo':
                    if (length == 1) {
                        window.open('${pageContext.request.contextPath}/CJEF/tojob?empId='+checkStatus.data[0].empId,'target');
                    } else {
                        layer.msg('请选择一个员工');
                    }
                    break;
                case 'eduInfo':
                    if (length == 1) {
                        window.open('${pageContext.request.contextPath}/education/gotoeducationlist?empId='+checkStatus.data[0].empId,'target');
                    } else {
                        layer.msg('请选择一个员工');
                    }
                    break;
                case 'famInfo':
                    if (length == 1) {
                        window.open('${pageContext.request.contextPath}/CJEF/tofam?empId='+checkStatus.data[0].empId,'target');
                    } else {
                        layer.msg('请选择一个员工');
                    }
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
                layer.confirm('确认删除此员工吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/emp/delEmp" ,
                        data: {idstr:data.empId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
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
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    title:'修改员工',
                    type:2,
                    content:'${pageContext.request.contextPath}/emp/toEdit?empId='+data.empId,
                    area: ['720px', '500px'],
                    resize:false,
                    maxmin:true
                });
            }
        });
    });
</script>
</body>
</html>
