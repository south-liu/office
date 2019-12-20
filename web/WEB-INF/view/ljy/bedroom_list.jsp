<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/19
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宿舍报表</title>
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
<div class="layui-card-body ">
    <form class="layui-form layui-col-space5" action="" onsubmit="return false;">

        <div class="layui-inline layui-show-xs-block">
            <select id="clazz" name="clazz">
                <option value="">选择班级</option>
                <c:forEach items="${clazzs}" var="cl">
                    <option value="${cl.classId}">${cl.className}</option>
                </c:forEach>
            </select>
        </div>

        <div class="layui-inline layui-show-xs-block">
            <select id="floor" name="floor">
                <option value="">楼栋名称</option>
                <c:forEach items="${floor}" var="f">
                    <option value="${f.floorId}">${f.floorName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-inline layui-show-xs-block">
            <select id="huor" name="huor">
                <option value="">宿舍房号</option>
                <c:forEach items="${huors}" var="h">
                    <option value="${h.hourId}">${h.huorName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="layui-inline layui-show-xs-block">
            <button class="layui-btn" id="search"><i class="layui-icon">&#xe615;</i></button>
        </div>
    </form>
</div>
<table class="layui-hide" id="myTab" lay-filter="fTab"></table>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="detail">宿舍成员</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url: '${pageContext.request.contextPath}/bedroom/bedroom_list',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            totalRow: true,
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '班级数据表',
            cols: [[
                {
                    field: 'hourId',
                    title: '宿舍ID',
                    sort: true,
                    hide: true,
                    align: 'center',

                },
                {field: 'className', title: '班级名称', sort: true, align: 'center', totalRowText: '合计'},
                {field: 'floorName', title: '楼栋名称 ', align: 'center'},
                {field: 'huorName', title: '宿舍房号', sort: true, align: 'center'},
                {field: 'huor', title: '宿舍id', sort: true,hide:true, align: 'center'},
                {field: 'address', title: '宿舍地址', align: 'center'},
                {field: 'count', title: '宿舍人数', align: 'center', totalRow: true},
                {field: 'numberBeds', title: '宿舍床位', align: 'center', totalRow: true},
                {
                    field: 'nullbeds',
                    title: '剩余床位',
                    align: 'center',
                    totalRow: true,
                    templet: function (res) {
                        return res.numberBeds - res.count;
                    }
                },
                {fixed: 'right', title: '操作', toolbar: '#bar', align: 'center'}
            ]],
            page: true
        });

        $('#search').click(function () {
            var clazz = $('#clazz').val().trim();
            var floor = $('#floor').val().trim();
            var huor = $('#huor').val().trim();
            table.reload('myTab', {
                url: '${pageContext.request.contextPath}/bedroom/bedroom_search_list',
                where: {
                    clazz: clazz,
                    floor: floor,
                    huor: huor
                }
            });
        });
        table.on('tool(fTab)', function (obj) {
            var data = obj.data;
            console.log(data.huor)
            if (obj.event === 'detail') {
                layer.open({
                    title: '学生详情',
                    type: 2,
                    content: '${pageContext.request.contextPath}/bedroom/goto_huor_studName?huor='+data.huor,
                    area: ['600px','300px'],
                    resize: false,
                    maxmin: true
                });

            }
        })

    });
</script>

</body>
</html>
