<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/14
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的任务</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
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


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看批注</a>
    <a class="layui-btn layui-btn-xs" lay-event="img" target="_blank">办理进度</a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData" onclick="addapply()">添加请假单</button>
    </div>
</script>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;
//        console.log(holidayId)
        var mytable = table.render({
                elem: '#myTab',
                url: '${pageContext.request.contextPath}/hstudent/hs_myapply_list',
                toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
                defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示',
                    layEvent: 'LAYTABLE_TIPS',
                    icon: 'layui-icon-tips'
                }],
                title: '我的请假申请',
                cols: [[
                    {type: 'checkbox', fixed: 'left'},
                    {
                        field: 'holidayId',
                        title: 'ID',
                        fixed: 'left',
                        unresize: true,
                        sort: true,
                        align: 'center',
                        width: 90
                    },
                    {field: 'stuName', title: '学生姓名', width: 90},
                    {
                        field: 'holidayDay', title: '请假天数', width: 90, templet: function (res) {
                        return res.holidayDay + ' 天'
                    }
                    },
                    {
                        field: 'holidayHour', title: '请假小时', width: 90, templet: function (res) {
                        return res.holidayHour + ' 小时'
                    }
                    },
                    {field: 'startTime', title: '开始时间', align: 'center', width: 120},
                    {field: 'endTime', title: '结束时间 ', align: 'center', width: 120},
                    {field: 'remark', title: '请假原因 ', width: 150},
                    {field: 'title', title: 'title ', width: 120},


                    {
                        field: 'status', title: '状态', align: 'center', width: 100, templet: function (res) {
//                            console.log(res)
                        if (res.status == 0) {
                            return '<span   class="layui-btn-llb layui-btn-normal layui-btn-mini layui-btn-disabled">申请中</span>'
                        } else if (res.status == 1) {
                            return '<span class="layui-btn-llb layui-btn-llbb layui-btn-normal layui-btn-mini">申请已批准</span>';
                        } else if (res.status == 2) {
                            return '<span  class="layui-btn-llb layui-btn-llbb layui-btn-danger layui-btn-mini">申请未批准</span>';
                        }
                    }
                    },
                    {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}


                ]],
                page: true
            })
        ;

        //toolbar监听顶部的工具栏
        table.on('toolbar(fTab)', function (obj) {
            var event = obj.event;
            if (event === 'delCheckData') {
                var checkStatus = table.checkStatus('myTab');
                deleteCheckData(checkStatus);
            }
            <%--} else if (event === 'mycheck') {--%>
            <%--//window.open('${pageContext.request.contextPath}/checkwork/gotomycheck','target');--%>
            <%--location.href = '${pageContext.request.contextPath}/checkwork/gotomycheck', 'target';--%>

            <%--}--%>
        });

//监听右边的工具栏
        table.on('tool(fTab)', function (obj) {
            var event = obj.event;
//            查看批注
            if (event === 'detail') {
                location.href = '${pageContext.request.contextPath}/hstudent/hs_myapply_lookComment?holidayId=' + obj.data.holidayId;
                <%--window.open('${pageContext.request.contextPath}/hstudent/hs_myapply_lookComment?holidayId=' + obj.data.holidayId)--%>

            }
//            办理进度
            else if (event === 'img') {
                window.open('${pageContext.request.contextPath}/hstudent/hs_myapply_lookapplyImg?holidayId=' + obj.data.holidayId)
            }
        });
    });


    function addapply() {
        layer.open({
            title: '申请请假',
            type: 2,
            content: ['${pageContext.request.contextPath}/hstudent/goto_hs_apply', 'no'],
            area: ['600px', '450px'],
            resize: false
        })
    }
</script>
</body>
</html>
