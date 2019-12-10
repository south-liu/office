<%--
  Created by IntelliJ IDEA.
  User: CC
  Date: 2019/12/5
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../public/head.jsp"/>
<html>
<head>
    <title>教育经历</title>
</head>
<body>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<table class="layui-table"
       lay-data="{
        height:530,
        url:'${pageContext.request.contextPath}/stujef/edulist?stuId=${stuId}',
        page:true,
        cellMinWidth: 80,
        id:'myTable',
        toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <input id="stuId" value="${stuId}" type="hidden">
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'eduId', sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'school',edit:'text'}" >就读学校</th>
        <th lay-data="{field:'beginDate',edit:'text'}">开始时间</th>
        <th lay-data="{field:'endDate',edit:'text'}">结束时间</th>
        <th lay-data="{fixed:'right', align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
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
                        $.post('${pageContext.request.contextPath}/stujef/deleteedu', {eduId: obj.data.eduId},function (data) {
                            layer.msg('删除成功！');
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/stujef/edulist?stuId=${stuId}'
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
                            type:2,
                            content:'${pageContext.request.contextPath}/stujef/toaddedu?stuId='+$('#stuId').val(),
                            btnAlign: 'c',
                            area: ['460px', '420px'],
                            resize:false
                        });
                        break;
                };
            });
            table.on('edit(fTable)', function(obj){
                var value = obj.value //得到修改后的值
                    ,data = obj.data //得到所在行所有键值
                    ,field = obj.field; //得到字段
                layer.msg(field + '修改成功');
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/stujef/updedu",
                    async:true,
                    dataType:"text",
                    data:{eduId:data.eduId,stuId:$('#stuId').val(),school:data.school,beginDate:data.beginDate,endDate:data.endDate},
                    success:function(data){
                        layer.close(lindex);
                        layer.msg('修改成功!', {
                            icon: 1,
                            time: 1000
                        },function () {
                            layer.close(index);
                            // location.reload();
                            table.reload('myTable', {
                                url: '${pageContext.request.contextPath}/stujef/edulist'
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
</table>
</body>
</html>