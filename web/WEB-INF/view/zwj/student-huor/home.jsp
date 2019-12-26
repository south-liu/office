<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/6
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>学生宿舍管理</title>

    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
</head>
<body>

<table class="layui-hide" id="dataTable" lay-filter="table_filter" lay-data="{id:'dataTable'}"
       style="margin:0px;"></table>
<%--表格顶部工具栏--%>
<script type="text/html" id="top_toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
    </div>
</script>
<%--数据最右边的操作栏--%>
<script type="text/html" id="action_toolbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<%--操作表单--%>
<div id="actionBox" style="display: none;margin-top:20px;">
    <form class="layui-form" method="post" id="actionForm" lay-filter="_form">
        <input type="hidden" name="hourId">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">宿舍楼栋：</label>
                <div class="layui-input-inline">
                    <select name="floorId" lay-verify="required">
                        <option value="">请选择宿舍楼栋</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">宿舍房号：</label>
            <div class="layui-input-inline">
                <input type="text" name="huorName" lay-verify="required|number" lay-reqtext="宿舍房号是必填项，岂能为空？"
                       placeholder="请输入宿舍房号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">床位数：</label>
            <div class="layui-input-inline">
                <input type="text" name="numberBeds" placeholder="请输入床位数" lay-verify="number" autocomplete="off"
                       class="layui-input"
                       value="0">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">排序编号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="huoeIddsc" placeholder="请输入排序编号" lay-verify="number" autocomplete="off"
                           class="layui-input"
                           value="0">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">宿舍地址：</label>
                <div class="layui-input-block">
                    <input type="text" name="address" autocomplete="off" placeholder="请输入宿舍地址"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submitForm">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript">
    layui.use('table', function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;

        // 表单的添加
        function showAddForm() {
            var _select = $('#actionForm').find('select[name="floorId"]');// 楼栋的下拉框

            var loadIndex = layer.load();
            var windowIndex = layer.open({
                type: 1,
                title: '添加',
                resize: false,
                area: ['380px', '400px'],
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    layero.hide();
                    $('#actionForm').attr('action', '${pageContext.request.contextPath}/student-huor/add');

                    // 添加表单显示之前，先查询所有楼栋
                    $.get('${pageContext.request.contextPath}/student-huor/addTheRequiredData', {}, function (data) {
                        if (data.code == 0) {
                            _select.empty();
                            _select.append('<option value="">请选择宿舍楼栋</option>');
                            $.each(data.requiredData.allFloor, function (i, element) {
                                _select.append('<option value="' + element.floorId + '">' + element.floorName + '</option>');
                            });
                            form.render('select');

                            layer.close(loadIndex);
                            layero.show();
                        } else {
                            layer.msg(data.msg, {icon: 2, time: 1000});
                        }
                    }, 'json');
                },
                end: function () {// 层销毁或关闭后触发的回调
                    // 清空表单数据
                    _select.empty();
                    $('#actionBox').children('form')[0].reset();
                }
            });
            // 通过提交按钮的lay-filter="submitForm"属性，触发表单中提交时的回调函数
            form.on('submit(submitForm)', function (data) {
                var loadIndex = layer.load();
                var dataForm = data.form;
                $.ajax({
                    method: dataForm.method,
                    url: dataForm.action,
                    data: data.field,
                    dataType: 'json',
                    success: function (data, textStatus) {
                        layer.close(loadIndex);

                        if (data.code == 0) {// 添加成功
                            layer.msg(data.msg, {
                                icon: 1,
                                time: 1000
                            });
                            layer.close(windowIndex);// 关闭表单窗口
                            table.reload('dataTable', {});// 重载表格
                        } else if (data.code == 1) {// 宿舍房号已存在
                            layer.msg(data.msg, {
                                icon: 3,
                                time: 2000
                            })
                        } else {
                            layer.msg(data.msg, {
                                icon: 2,
                                time: 1000
                            })
                        }
                    }
                });
                return false;
            })
        }

        // 表单的编辑
        function showEditForm(objData) {
            var loadIndex = layer.load();
            var windowIndex = layer.open({
                type: 1,
                title: '编辑',
                resize: false,
                area: ['380px', '400px'],
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    layero.hide();
                    $('#actionForm').attr('action', '${pageContext.request.contextPath}/student-huor/update');

                    // 通过id获取数据并填充到表单中
                    $.get('${pageContext.request.contextPath}/student-huor/detail', {hourId: objData.hourId}, function (data) {
                        if (data.code == 0) {
                            var studentHuor = data.studentHuor;// 宿舍信息
                            var floorList = data.floorList;// 所有楼栋

                            if (floorList.length > 0) {
                                var _select = $('#actionForm').find('select[name="floorId"]');
                                _select.empty();
                                _select.append('<option value="">请选择宿舍楼栋</option>');
                                $.each(floorList, function (i, element) {
                                    _select.append('<option value="' + element.floorId + '">' + element.floorName + '</option>');
                                });
                            }

                            // 表单控件赋值
                            form.val('_form', {
                                'hourId': studentHuor.hourId,
                                'floorId': studentHuor.floorId,
                                'huorName': studentHuor.huorName,
                                'numberBeds': studentHuor.numberBeds,
                                'huoeIddsc': studentHuor.huoeIddsc,
                                'address': studentHuor.address
                            });
                            form.render();// 刷新所有表单控件

                            layer.close(loadIndex);
                            layero.show();
                        } else {
                            layer.msg(data.msg, {icon: 2, time: 2000});
                        }
                    }, 'json');
                },
                end: function () {// 层销毁或关闭后触发的回调
                    // 清空表单
                    $('#actionForm').find('input[name="hourId"]').removeAttr('value');
                    $('#actionBox').children('form')[0].reset();
                }
            });
            // 通过提交按钮的lay-filter="submitForm"属性，触发表单中提交时的回调函数
            form.on('submit(submitForm)', function (data) {
                var loadIndex = layer.load();
                var dataForm = data.form;
                $.ajax({
                    method: dataForm.method,
                    url: dataForm.action,
                    data: data.field,
                    dataType: 'json',
                    success: function (data, textStatus) {
                        layer.close(loadIndex);

                        if (data.code == 0) {
                            layer.msg(data.msg, {icon: 1, time: 1000});

                            layer.close(windowIndex);// 关闭表单窗口
                            table.reload('dataTable', {});// 重载表格
                        } else if (data.code == 1) {
                            layer.msg(data.msg, {icon: 3, time: 2000});
                        } else {
                            layer.msg(data.msg, {icon: 2, time: 1000});
                        }
                    },
                    error: function () {
                        layer.msg('服务器错误！', {time: 1000});
                        layer.close(loadIndex);
                    }
                });
                return false;
            })
        }

        // 删除数据
        function deleteData(obj) {
            layer.confirm('删除该房号将移除在内的所有学生，确定要删除吗？', function (index) {
                $.post('${pageContext.request.contextPath}/student-huor/delete', {hourId: obj.data.hourId}, function (data) {
                    layer.close(index);

                    if (data.code == 0) {
                        layer.msg(data.msg, {
                            icon: 1,
                            time: 1000
                        });
                        table.reload('dataTable', {curr: 1});// 重载表格
                    } else {
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 1000
                        });
                    }
                }, 'json');
            });
        }

        // 删除多条
        function deleteMulti(checkStatus) {
            var hintMsg;// 提示信息
            if (checkStatus.data.length <= 0) {
                layer.msg('请选中至少一行！', {icon: 0, time: 2000});
                return;
            } else if (checkStatus.data.length == 1) {
                hintMsg = '确定删除该行吗？';
            } else {
                hintMsg = '确定删除' + checkStatus.data.length + '行吗？';
            }
            layer.confirm(hintMsg, function (index) {
                var ids = [];
                $.each(checkStatus.data, function (i, element) {
                    ids.push(element.hourId);
                });
                // 传输id到后台删除数据
                $.post('${pageContext.request.contextPath}/student-huor/deleteMulti', {hourId: ids}, function
                    (data) {
                    // 返回删除数组的长度
                    if (data > 0) {
                        // 删除成功
                        layer.msg('删除成功', {
                            icon: 1,
                            time: 1000
                        });
                        table.reload('dataTable', {curr: 1});// 重载表格
                    }
                });
                layer.close(index);
            });
        }

        // 渲染数据表格
        table.render({
            elem: '#dataTable'
            , toolbar: '#top_toolbar'
            , defaultToolbar: []
            , url: '${pageContext.request.contextPath}/student-huor/allData'
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , groups: 5 //只显示 1 个连续页码
                , first: false //不显示首页
                , last: false //不显示尾页
            }
            , cols: [[
                {field: 'checkbox', type: 'checkbox', fixed: 'left', width: 40}
                , {field: 'hourId', width: 80, title: 'ID', sort: true}
                , {field: 'huoeIddsc', title: '序号', sort: true}
                , {field: 'huorName', title: '宿舍房号', sort: true}
                , {field: 'floorName', title: '宿舍楼栋'}
                , {field: 'numberBeds', title: '床位数', sort: true}
                , {field: 'address', title: '宿舍地址'}
                , {field: 'count', title: '宿舍人数', sort: true}
                , {fixed: 'right', width: 200, title: '操作', align: 'center', toolbar: '#action_toolbar'}
            ]]
        });

        // 监听头部工具栏事件
        table.on('toolbar(table_filter)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var event = obj.event;

            if (event == 'add') {
                showAddForm();// 展示添加表单的编辑框
            } else if (event == 'delete') {// 删除多选
                var checkStatus = table.checkStatus('dataTable');

                deleteMulti(checkStatus);// 删除多行
            }
        });

        // 监听行中工具栏事件
        table.on('tool(table_filter)', function (obj) {
            var event = obj.event;
            if (event == 'edit') {
                showEditForm(obj.data);// 编辑数据
            } else if (event == 'del') {
                deleteData(obj);// 删除数据
            }
        });
    });
</script>
</body>
</html>
