<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/23
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的谈心</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<table class="layui-table"
       lay-data="{url:'${pageContext.request.contextPath}/mychat/my_chatrecordlist', page:true, id:'myTable',toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'chatId', width:80, sort: true, fixed: true,align:'center'}">ID</th>
        <th lay-data="{field:'stuName', width:135}">学生名称</th>
        <th lay-data="{field:'empName', width:135}">员工名称</th>
        <th lay-data="{field:'address', width:135}">地址</th>
        <th lay-data="{field:'sayScon', width:300}">谈心内容</th>
        <th lay-data="{field:'chatDate', width:135, sort: true}">时间</th>
        <th lay-data="{fixed: 'right', width:182, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData" onclick="addchatRecord()">添加记录</button>
    </div>
</script>


<script>
    <%--初始化table--%>
    layui.use(['table', 'laydate'], function () {
        var table = layui.table;
        var laydate = layui.laydate;

        table.on('tool(fTable)', function (obj) {
                var event = obj.event;
                console.log(obj.data.classTypeName);
                if (event === 'detail') {
                    layer.alert(JSON.stringify(obj.data));
                } else if (event === 'del') {
                    layer.confirm('确认删除', function (index) {
                        console.log(index);
                        $.post('${pageContext.request.contextPath}/chatRecord/chatRecorddelete', {chatId: obj.data.chatId}, function (data) {
                            console.log(data);
                            layer.msg('删除成功！');
                            location.reload();
                        }, 'json');
                        layer.close(index);
                    });
                }
            }
        )
    });

    function addchatRecord() {
        layer.open({
            title: '添加试讲日期',
            type: 2,
            content: ['${pageContext.request.contextPath}/chatRecord/gotochatRecord_add', 'no'],
            area: ['480px', '550px'],
            resize: false
        })
    }
</script>
</body>
</html>
