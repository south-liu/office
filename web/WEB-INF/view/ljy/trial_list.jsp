<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/9
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试讲内容</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<table class="layui-table"
       lay-data="{url:'${pageContext.request.contextPath}/trial/triallist', page:true, id:'myTable',toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'trialId', sort: true}">序号</th>
        <th lay-data="{field:'date',  sort: true}">日期</th>
        <th lay-data="{field:'time',  sort: true}">试讲时间</th>
        <th lay-data="{field:'courseName', sort: true}">课程/章节</th>
        <th lay-data="{field:'courseTypeName',sort: true}">授课类型</th>
        <th lay-data="{field:'empName',sort: true}">授课老师</th>
        <th lay-data="{field:'remark',sort: true}">备注</th>
        <th lay-data="{fixed: 'right',align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData" onclick="addtrial()">添加记录</button>
        <button class="layui-btn layui-btn-sm" lay-event="delCheckData">删除选中</button>
    </div>


</script>


<script>
    layui.use(['table'], function () {
        var table = layui.table;
//        tool监听右边工具栏
        table.on('tool(fTable)', function (obj) {
            var event = obj.event;
            if (event === 'del') {
                layer.confirm('确认删除', function (index) {
                    console.log(index);
                    $.post('${pageContext.request.contextPath}/trial/trialdelete', {trialId: obj.data.trialId}, function (data) {
                        console.log(data);
                        layer.msg('删除成功！');
//                        仅在页面上的删除
                        obj.del();
                    }, 'json');
                    layer.close(index);
                });
            }
        });
//toolbar监听顶部的工具栏
        table.on('toolbar(fTable)', function (obj) {
            var event = obj.event;
            if (event === 'delCheckData') {
                var checkStatus = table.checkStatus('myTable');
                deleteCheckData(checkStatus);
            }
        });
    })

    function addtrial() {
        layer.open({
            title: '添加试讲日期',
            type: 2,
            content: ['${pageContext.request.contextPath}/trial/gototrialadd', 'no'],
            area: ['780px', '480px'],
            resize: false
        })
    }


    //    删除多条
    function deleteCheckData(checkStatus) {
        var tipMsg;
        var table = layui.table;
        if (checkStatus.data.length <= 0) {
            layer.msg('请选中你要删除的数据', {icon: 0, time: 1500});
            return;
        } else if (checkStatus.data.length == 1) {
            tipMsg = '确定删除该行吗？';
        } else {
            tipMsg = ('确定删除' + checkStatus.data.length + '行吗？')
        }
        layer.confirm(tipMsg, function (index) {
            var ids = [];
//            循环遍历方法，把id赋值给ids
            $.each(checkStatus.data, function (i, element) {
                ids.push(element.trialId);
            });
            $.post('${pageContext.request.contextPath}/trial/trialdelete', {trialId: ids}, function (data) {
                if (data > 0) {
                    layer.msg('删除成功', {
                        icon: 1,
                        time: 1000
                    });
//                    table.reload('myTable');
                    window.location.reload();
                }
            });
            layer.close(index);
        })
    }

</script>
</body>
</html>
