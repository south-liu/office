<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/11
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>未打卡说明列表</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
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


<table class="layui-hide" id="myTab" lay-filter="fTab"></table>


<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData" onclick="addcheckwork()">添加记录</button>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="mycheck">我的审批</a>
    </div>
</script>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        var mytable = table.render({
            elem: '#myTab',
            url: '${pageContext.request.contextPath}/checkwork/checkworklist',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '员工数据表',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'empName', title: '姓名', fixed: 'left', unresize: true, sort: true, align: 'center', width: 90},
                {field: 'noCardTime', title: '未打卡时间', align: 'center', width: 120},
                {field: 'why', title: '原因说明',  width: 250},
                {field: 'chairman', title: '审核人', align: 'center', width: 100},
                {field: 'checkTime', title: '审核时间 ',  align: 'center', width: 120},
                {field: 'remark', title: '审核说明 ',width: 250},
//                {field: 'status', title: '审核状态', align: 'center', width: 100},

                {
                    field: 'status', title: '状态', align: 'center', templet: function (res) {
                    if (res.status == 0) {
                        return '<span   class="layui-btn-llb layui-btn-normal layui-btn-mini layui-btn-disabled">未审核</span>'
                    } else if (res.status == 1) {
                        return '<span class="layui-btn-llb layui-btn-llbb layui-btn-normal layui-btn-mini">审核已通过</span>';
                    } else if (res.status == 2) {
                        return '<span  class="layui-btn-llb layui-btn-llbb layui-btn-normal layui-btn-mini">审核未通过</span>';
                    }
                }
                },
            ]],
            page: true
        });

        //toolbar监听顶部的工具栏
        table.on('toolbar(fTab)', function (obj) {
            var event = obj.event;
            if (event === 'delCheckData') {
                var checkStatus = table.checkStatus('myTab');
                deleteCheckData(checkStatus);
            } else if (event === 'mycheck') {
                //window.open('${pageContext.request.contextPath}/checkwork/gotomycheck','target');
                location.href='${pageContext.request.contextPath}/checkwork/gotomycheck','target';

            }
        });
    });


    function addcheckwork() {
        layer.open({
            title: '添加说明',
            type: 2,
            content: ['${pageContext.request.contextPath}/checkwork/gotocheckworkadd', 'no'],
            area: ['800px', '400px'],
            resize: false
        })
    }
</script>
</body>
</html>
