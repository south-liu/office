<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/11
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的审批</title>
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

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="fanhui">返回</button>
    </div>
</script>


<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="tongguo">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="butongguo">不通过</a>
</script>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        var mytable = table.render({
            elem: '#myTab',
            url: '${pageContext.request.contextPath}/checkwork/mychecklist',
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
                {
                    field: 'checkworkId',
                    title: 'ID',
                    fixed: 'left',
                    unresize: true,
                    sort: true,
                    align: 'center',
                    width: 60,
                    hide: true
                },
                {field: 'noCardTime', title: '未打卡时间', align: 'center', width: 150},
                {field: 'why', title: '原因说明',  width: 150},
                {field: 'chairman', title: '审核人', align: 'center', width: 100},
                {field: 'checkTime', title: '审核时间 ', align: 'center', width: 120},
                {field: 'remark', title: '审核说明 ',  width: 150},
//                {field: 'status', title: '审核状态', align: 'center', width: 100},

                {
                    field: 'status', title: '状态', align: 'center', templet: function (res) {
                    if (res.status == 0) {
                        return '<span   class="layui-btn-llb layui-btn-normal layui-btn-mini layui-btn-disabled">未审核</span>'
                    } else if (res.status == 1) {
                        return '<span class="layui-btn-llb layui-btn-llbb layui-btn-normal layui-btn-mini">审核已通过</span>';
                    } else if (res.status == 2) {
                        return '<span  class="layui-btn-llb layui-btn-llbb layui-btn-danger layui-btn-mini">审核未通过</span>';
                    }
                }
                },
                {fixed: 'right', title: '操作', toolbar: '#bar', align: 'center'}
            ]],
            page: true
        });

        //toolbar监听顶部的工具栏
        table.on('toolbar(fTab)', function (obj) {
            var event = obj.event;
            if (event === 'fanhui') {
               history.go(-1);
            }
        });

        table.on('edit(fTab)', function (obj) {
//            console.log(obj)
            var lindex = layer.load();
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段名
//            layer.msg('[ID: '+ data.checkworkId +'] ' + field + '更改为：'+ value);
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/checkwork/checkwork_update",
                async: true,
                dataType: "text",
                <%--data:{empId:${empId},eduId:data.eduId,schoolName:data.schoolName,degree:data.degree,startDate:data.startDate,endDate:data.endDate,remark:data.remark},--%>
                data: {checkworkId: data.checkworkId, remark: data.remark,},
                success: function (data) {
                    layer.close(lindex);
                    layer.msg('修改成功!', {
                        icon: 1,
                        time: 1000
                    }, function () {
                        table.reload('myTab', {
                            url: '${pageContext.request.contextPath}/checkwork/mychecklist'
                        });
                    });
                },
                error: function () {
                    layer.close(lindex);
                    layer.msg("服务器错误", {
                        icon: 1,
                        time: 1000
                    });
                }
            });
        });

        //监听行工具事件
        table.on('tool(fTab)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'tongguo') {
                if (data.status == 0) {
//                    添加审核说明部分
                    layer.open({
                        title: '审核说明',
                        type: 1,
                        content: '<div style="margin-top: 10px" class="layui-inline">\n' +

                        '  <div style="margin: 20px 0px ">' +
                        '          <label class="layui-form-label">审核说明：</label>\n' +
                        '        <div class="layui-input-inline">\n' +
                        '            <input type="text" id="remark" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                        '        </div>' +
                        '       </div> \n' +
                        '        </div> \n',

                        btn:['确定','取消'],
                        yes:function (index,layero) {

                            layer.confirm('确定通过审核吗', function () {
                                var lod = layer.load();
                                $.ajax({
                                    type: "POST",
                                    dataType: "json",
                                    url: "${pageContext.request.contextPath}/checkwork/updStatus",
                                    data: {checkworkId: data.checkworkId, status: 1,remark:$('#remark').val().trim()},
                                    success: function (result) {
                                        layer.close(lod);
                                        layer.msg('审核成功', {
                                            time: 1000
                                        }, function () {
                                            window.location.reload();
                                        });
                                    },
                                    error: function () {
                                        layer.close(lod);
                                        layer.msg('服务器错误');
                                    }
                                });
                            });
                        },
                        btnAlign:'c',
                        area:['350px','200px']
                    })

                } else {
                    layer.msg('审核已完成！');
                }

            } else if (obj.event === 'butongguo') {
                if (data.status == 0) {
//                    添加审核说明部分
                    layer.open({
                        title: '审核说明',
                        type: 1,
                        content: '<div style="margin-top: 10px" class="layui-inline">\n' +

                        '  <div style="margin: 20px 0px ">' +
                        '          <label class="layui-form-label">审核说明：</label>\n' +
                        '        <div class="layui-input-inline">\n' +
                        '            <input type="text" id="remark" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                        '        </div>' +
                        '       </div> \n' +
                        '        </div> \n',

                        btn:['确定','取消'],
                        yes:function (index,layero) {

                            layer.confirm('确定不通过审核吗', function () {
                                var lod = layer.load();
                                $.ajax({
                                    type: "POST",
                                    dataType: "json",
                                    url: "${pageContext.request.contextPath}/checkwork/updStatus",
                                    data: {checkworkId: data.checkworkId, status: 2,remark:$('#remark').val().trim()},
                                    success: function (result) {
                                        layer.close(lod);
                                        layer.msg('启用成功', {
                                            time: 1000
                                        }, function () {
                                            window.location.reload();
                                        });
                                    },
                                    error: function () {
                                        layer.close(lod);
                                        layer.msg('服务器错误');
                                    }
                                });
                            });
                        },
                        btnAlign:'c',
                        area:['350px','200px']
                    })

                } else {
                    layer.msg('审核已完成！');
                }
            }

        });
    });


    <%--function tonguo(obj, empId) {--%>
    <%--layer.confirm('确定通过审核吗', function () {--%>
    <%--var lod = layer.load();--%>
    <%--$.ajax({--%>
    <%--type: "POST",--%>
    <%--dataType: "json",--%>
    <%--url: "${pageContext.request.contextPath}/checkwork/updStatus",--%>
    <%--data: {empId: empId, status: 1},--%>
    <%--success: function (result) {--%>
    <%--layer.close(lod);--%>
    <%--layer.msg('启用成功', {--%>
    <%--time: 1000--%>
    <%--}, function () {--%>
    <%--window.location.reload();--%>
    <%--});--%>
    <%--},--%>
    <%--error: function () {--%>
    <%--layer.close(lod);--%>
    <%--layer.msg('服务器错误');--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>
    <%--};--%>
    <%--function butonguo(obj, empId) {--%>
    <%--layer.confirm('确定不通过审核吗', function () {--%>
    <%--var lod = layer.load();--%>
    <%--$.ajax({--%>
    <%--type: "POST",--%>
    <%--dataType: "json",--%>
    <%--url: "${pageContext.request.contextPath}/checkwork/updStatus",--%>
    <%--data: {empId: empId, status: 0},--%>
    <%--success: function (result) {--%>
    <%--layer.close(lod);--%>
    <%--layer.msg('禁用成功', {--%>
    <%--time: 1000--%>
    <%--}, function () {--%>
    <%--window.location.reload();--%>
    <%--});--%>
    <%--},--%>
    <%--error: function () {--%>
    <%--layer.close(lod);--%>
    <%--layer.msg('服务器错误');--%>
    <%--}--%>
    <%--});--%>
    <%--})--%>
    //    }

</script>
</body>
</html>
