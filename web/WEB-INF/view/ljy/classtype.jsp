<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/4
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级类别管理</title>
    <%--方式1：--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>--%>
    <%--<script src="${pageContext.request.contextPath}/layui/layui.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>--%>

    <%--方式2：--%>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>

<table class="layui-table"
       lay-data="{url:'${pageContext.request.contextPath}/classtype/classtypelist', page:true, id:'myTable',toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
<%--        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>--%>
        <th lay-data="{field:'classTypeId', sort: true,  align:'center'}">ID</th>
        <th lay-data="{field:'classTypeName', align:'center'}">班级类别名称</th>
        <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
<%--    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">添加类型</button>
    </div>
</script>

<script>
    <%--初始化table--%>
    layui.use(['table'], function () {
        var table = layui.table;
        table.on('tool(fTable)', function (obj) {
            var event = obj.event;
            console.log(obj.data.classTypeName);
            if (event === 'detail') {
                layer.alert(JSON.stringify(obj.data));
            } else if (event === 'edit') {
//                弹出层
                layer.open({
                    title: '修改班级类型',
                    type: 1,
//                    直接拼接文本框
                    content: '<div style="margin-top: 10px" class="layui-inline">\n' +
                    '        <label class="layui-form-label">类别名称：</label>\n' +
                    '        <div class="layui-input-inline">\n' + '<input type="hidden" id="classTypeId"/>' +
                    '            <input type="text" id="classTypeName" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>',

                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
//                            选中输入框的值，把值赋值给对象
                        var classTypeName = $('#classTypeName').val().trim();
//                        直接从数据中获取
                        var classTypeId = obj.data.classTypeId;

//                            判断是否为空
                        if (classTypeName == '') {
                            layer.msg('请输入你要修改的类别名称!', {
                                icon: 2,
                                time: 1000
                            });
                        } else {
                            var lindex = layer.load();
                            $.ajax({
                                    type: "post",
                                    url: "${pageContext.request.contextPath}/classtype/updateclasstype",
                                    async: true,
                                    dataType: "text",
                                    data: {classTypeId: classTypeId, classTypeName: classTypeName},
                                    success: function (data) {
                                        layer.close(lindex);
                                        layer.msg('修改成功!', {
                                            icon: 1,
                                            time: 1000
                                        }, function () {
                                            layer.close(index);
//                                         location.reload();
                                            table.reload('myTable', {
                                                url: '${pageContext.request.contextPath}/classtype/classtypelist'
                                            });
                                        });
                                    },
                                    error: function () {
                                        layer.close(lindex);
                                        layer.msg("服务器错误");
                                    }
                                }
                            );
                        }
                    },
                    btnAlign: 'c',
                    area: ['400px', '180px']
                });
//                把选中的值放到弹出框中去
                $('#classTypeName').val(obj.data.classTypeName);
                $('#classTypeId').val(obj.data.classTypeId);

            } else if (event === 'del') {
                layer.confirm('确认删除', function (index) {
                    console.log(index);
                    $.post('${pageContext.request.contextPath}/classtype/delclasstype', {classTypeId: obj.data.classTypeId}, function (data) {
                        console.log(data);
                        layer.msg('删除成功！');
                        obj.del();
                        <%--var tableIns = table.render({--%>
                        <%--elem: '#myTable'--%>
                        <%--,url: '${pageContext.request.contextPath}/classtype/classtypelist' //设置异步接口--%>
                        <%--});--%>
                    }, 'json');
                    layer.close(index);
                });
            }
        });

        //监听事件
        table.on('toolbar(fTable)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == 'getCheckData') {
                layer.open({
                    title: '添加班级类型',
                    type: 1,
                    content: '<div style="margin-top: 10px" class="layui-inline">\n' +
                    '        <label class="layui-form-label">类别名称：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="classTypeName" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '        </div>\n' +
                    '    </div>',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
//                            选中输入框
                        var classTypeName = $('#classTypeName').val().trim();
//                            判断是否为空
                        if (classTypeName == '') {
                            layer.msg('请输入类别名称!', {
                                icon: 2,
                                time: 1000
                            });
                        } else {
                            var lindex = layer.load();
                            $.ajax({
                                type: "post",
                                url: "${pageContext.request.contextPath}/classtype/addclasstype",
                                async: true,
                                dataType: "text",
                                data: {classTypeName: classTypeName},
                                success: function (data) {
                                    layer.close(lindex);
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    }, function () {
                                        layer.close(index);
                                        // location.reload();
                                        table.reload('myTable', {
                                            url: '${pageContext.request.contextPath}/classtype/classtypelist'
                                        });
                                    });
                                },
                                error: function () {
                                    layer.close(lindex);
                                    layer.msg("服务器错误");
                                }
                            });
                        }
                    },
                    btnAlign: 'c',
                    area: ['400px', '180px']

//                        类型为2代表iframe模式
                    <%--content: '${pageContext.request.contextPath}/classtype/gotoaddclasstype',--%>
                    <%--type:2,--%>
                    <%--area: ['500px', '500px']--%>
                });
            }
        });
    });
</script>
</body>
</html>
