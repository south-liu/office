<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/16
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考评标准</title>

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
        <input type="hidden" name="evaluationId">
        <div class="layui-form-item">
            <label class="layui-form-label">考评名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="evaluationName" lay-verify="required" lay-reqtext="考评名称是必填项，岂能为空？" placeholder="请输入考评名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">考评分值：</label>
            <div class="layui-input-inline">
                <input type="text" name="score" lay-verify="required|number" lay-reqtext="考评分值是必填项，岂能为空？" placeholder="请输入考评分值"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">考评类型：</label>
            <div class="layui-input-block">
                <input type="radio" name="evaluationType" value="1" title="授课老师" checked>
                <input type="radio" name="evaluationType" value="2" title="班主任">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">说明：</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入说明" name="remark" class="layui-textarea" style="width:300px;"></textarea>
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
    layui.use(['table', 'layer', 'form'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;

        var formArea = ['500px', '400px'];// 编辑和添加表单的宽高

        /*var addFormTitle = '添加考评';
        var addFormURL = '







        ${pageContext.request.contextPath}/evaluation-standard/add';

        var editFormTitle = '编辑考评';
        var editFormURL = '







        ${pageContext.request.contextPath}/evaluation-standard/add';*/

        var addOptions = {
            title: '添加考评',
            url: '${pageContext.request.contextPath}/evaluation-standard/add'
        };
        var editOptions = {
            title: '编辑考评',
            url: '${pageContext.request.contextPath}/evaluation-standard/update',
            detailUrl: '${pageContext.request.contextPath}/evaluation-standard/detail'
        };
        var deleteOptions = {
            url: '${pageContext.request.contextPath}/evaluation-standard/delete',
            multiUrl: '${pageContext.request.contextPath}/evaluation-standard/deleteMulti'
        };
        var queryData = {
            url: '${pageContext.request.contextPath}/evaluation-standard/allData'
        };

        // 表单的添加
        function showAddForm() {
            var windowIndex = layer.open({
                type: 1,
                title: addOptions.title,
                resize: false,
                area: formArea,
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    $('#actionForm').attr('action', addOptions.url);
                },
                end: function () {// 层销毁或关闭后触发的回调
                    // 清空表单
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

                        if (data.code == 0 && data.result > 0) {// 添加成功
                            layer.msg(data.msg, {
                                icon: 1,
                                time: 1000
                            });
                            layer.close(windowIndex);// 关闭表单窗口
                            table.reload('dataTable', {});// 重载表格
                        } else if (data.code == 1) {// 失败
                            layer.msg(data.msg, {
                                icon: 2,
                                time: 1000
                            })
                        } else if (data.code == 2) {// 名称已存在
                            layer.msg(data.msg, {
                                icon: 3,
                                time: 1000
                            })
                        }
                    },
                    error: function () {
                        layer.close(loadIndex);
                        layer.msg('服务器错误！', {time: 1000});
                    }
                });
                return false;
            })
        }

        // 表单的编辑
        function showEditForm(dataObject) {
            var windowIndex = layer.open({
                type: 1,
                title: editOptions.title,
                resize: false,
                area: formArea,
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    layero.hide();
                    var loadIndex = layer.load();
                    $('#actionForm').attr('action', editOptions.url);

                    // 通过id获取数据并填充到表单中
                    $.get(editOptions.detailUrl, {evaluationId: dataObject.evaluationId}, function (data) {
                        form.val('_form', {
                            "evaluationId": data.evaluationId,
                            "evaluationName": data.evaluationName,
                            "evaluationType": data.evaluationType,
                            "score": data.score,
                            "remark": data.remark
                        });
                        layer.close(loadIndex);
                        layero.show();
                    }, 'json');
                },
                end: function () {// 层销毁或关闭后触发的回调
                    $('#actionForm').find('input[name="evaluationId"]').removeAttr('value');
                    // 清空表单
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


                        if (data.code == 0 && data.result > 0) {// 修改成功
                            layer.msg(data.msg, {
                                icon: 1,
                                time: 1000
                            });
                            layer.close(windowIndex);// 关闭表单窗口
                            table.reload('dataTable', {});// 重载表格
                        } else if (data.code == 1) {// 失败
                            layer.msg(data.msg, {
                                icon: 2,
                                time: 1000
                            })
                        }
                    },
                    error: function () {
                        layer.close(loadIndex);
                        layer.msg('服务器错误！', {time: 1000});
                    }
                });
                return false;
            })
        }

        // 删除数据
        function deleteData(obj) {
            layer.confirm('确定删除该行吗？', function (index) {
                var loadIndex = layer.load();
                // 传输id到后台删除数据
                $.post(deleteOptions.url, {evaluationId: obj.data.evaluationId}, function (data) {
                    layer.close(loadIndex);
                    layer.close(index);

                    if (data.code == 0 && data.result > 0) {// result：返回删除的id
                        layer.msg(data.msg, {
                            icon: 1,
                            time: 1000
                        });
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        table.reload('dataTable', {});// 重载表格
                    } else if (data.code == 1) {// 失败
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 1000
                        })
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
                var loadIndex = layer.load();
                var ids = [];
                $.each(checkStatus.data, function (i, element) {
                    ids.push(element.evaluationId);
                });
                // 传输id到后台删除数据
                $.post(deleteOptions.multiUrl, {evaluationIds: ids}, function (data) {
                    layer.close(loadIndex);
                    layer.close(index);

                    if (data.code == 0 && data.result > 0) {// result：返回删除的id
                        layer.msg(data.msg, {
                            icon: 1,
                            time: 1000
                        });
                        table.reload('dataTable', {});// 重载表格
                    } else if (data.code == 1) {// 失败
                        layer.msg(data.msg, {
                            icon: 2,
                            time: 1000
                        })
                    }
                }, 'json');
            });
        }

        // 渲染数据表格
        table.render({
            elem: '#dataTable'
            , toolbar: '#top_toolbar'
            , defaultToolbar: []
            , url: queryData.url
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , groups: 5 //只显示 1 个连续页码
                , first: false //不显示首页
                , last: false //不显示尾页
            }
            , cols: [[
                {field: 'checkbox', type: 'checkbox', fixed: 'left', width: 40}
                , {field: 'evaluationId', width: 80, title: 'ID', sort: true}
                , {field: 'evaluationName', title: '考评名称'}
                , {field: 'score', title: '考评分值'}
                , {
                    field: 'evaluationType', title: '考评类型', templet: function (d) {
                        if (d.evaluationType == 1) {
                            return '授课老师';
                        } else if (d.evaluationType == 2) {
                            return '班主任';
                        }
                    }
                }
                , {field: 'remark', title: '考评说明', minWidth: 150}
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
