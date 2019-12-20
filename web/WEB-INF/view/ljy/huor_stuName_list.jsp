<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/19
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍中的学生详情</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-btn-llb {
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }

    .layui-btn-llbb {
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body>
<table class="layui-hide" id="myTab" lay-filter="fTab"></table>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url: '${pageContext.request.contextPath}/bedroom/huor_student?huor=${huor}',
            //toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            totalRow: true,
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '宿舍详情表',
            cols: [[
                {field: 'stuno', title: '学生学号', sort: true, align: 'center'},
                {field: 'stuName', title: '学生姓名', sort: true, align: 'center'},
                {field: 'age', title: '学生年龄 ', align: 'center'},
                {field: 'addr', title: '家庭地址', align: 'center'}
            ]],
            page: true
         });
    });
</script>
</body>
</html>
