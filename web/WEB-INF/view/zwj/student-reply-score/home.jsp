<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/9
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生答辩成绩</title>
    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/xm-select/dist/xm-select.js"></script>
    <style type="text/css">
        .layui-form-item .layui-form-label {
            width: 100px;
        }
    </style>
</head>
<body>

<%--搜索表单的筛选--%>
<form class="layui-form" id="searchForm">
    <div class="layui-form-item" style="margin-top: 15px;">
        <div class="layui-inline">
            <label class="layui-form-label">班级名称：</label>
            <div class="layui-input-inline">
                <select name="studentClassId" lay-verify="required" lay-search>
                    <option value="0">请选择班级名称</option>
                    <c:forEach var="studentClass" items="${studentClassList}">
                        <option value="${studentClass.classId}">${studentClass.className}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">项目名称：</label>
            <div class="layui-input-inline">
                <select name="projectId" lay-verify="required" lay-search>
                    <option value="0">请选择项目名称</option>
                    <c:forEach var="project" items="${projectList}">
                        <option value="${project.projectId}">${project.projectName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn" id="searchBtn">
                <i class="layui-icon">&#xe615;</i> 搜索
            </button>
        </div>
    </div>
</form>
<table class="layui-hide" id="dataTable" lay-filter="table_filter" lay-data="{id:'dataTable'}"
       style="margin:0px;"></table>

<%--表格顶部工具栏--%>
<script type="text/html" id="top_toolbar">
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
                <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
            </div>
        </div>
    </div>
</script>
<%--数据最右边的操作栏--%>
<script type="text/html" id="action_toolbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<%--操作表单--%>
<div id="actionBox" style="display: none;margin-top:20px;">
    <form class="layui-form" lay-filter="_form" method="post" id="actionForm">
        <input type="hidden" name="replyId">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称：</label>
            <div class="layui-input-inline">
                <select name="studentClassId" lay-verify="studentClassName" lay-filter="studentClassSelect">
                    <option value="0">请选择班级名称</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目成员：</label>
            <div class="layui-input-inline" style="width:518px;">
                <%--<select name="studentId" lay-verify="studentName" id="studentSelect">
                    <option value="0">请先选择班级</option>
                </select>--%>
                <div id="studentSelect"></div>
            </div>
            <br/>
            <div class="layui-form-mid layui-word-aux" id="tips" style="margin-left:130px;"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目名称：</label>
            <div class="layui-input-inline">
                <select name="projectId" lay-verify="projectName">
                    <option value="0">请选择项目名称</option>
                </select>
            </div>
            <label class="layui-form-label">任课老师：</label>
            <div class="layui-input-inline">
                <select name="empId" lay-verify="empName">
                    <option value="0">请选择任课老师</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">功能完善(50)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score1" lay-verify="required|number|score1" lay-reqtext="功能完善分数在0~50之间"
                       placeholder="请输入功能完善分数(0~50)" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">技术难度(10)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score2" lay-verify="required|number|score2" lay-reqtext="技术难度分数在0~10之间"
                       placeholder="请输入技术难度分数(0~10)" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">界面完美(10)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score3" lay-verify="required|number|score3" lay-reqtext="界面完美分数在0~10之间"
                       placeholder="请输入界面完美分数(0~10)" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">回答问题(10)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score4" lay-verify="required|number|score4" lay-reqtext="回答问题分数在0~10之间"
                       placeholder="请输入回答问题分数(0~10)" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">演示方法(10)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score5" lay-verify="required|number|score5" lay-reqtext="演示方法分数在0~10之间"
                       placeholder="请输入演示方法分数(0~10)" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">语言表达(10)：</label>
            <div class="layui-input-inline">
                <input type="text" name="score6" lay-verify="required|number|score6" lay-reqtext="语言表达分数在0~10之间"
                       placeholder="请输入语言表达分数(0~10)" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入备注" class="layui-textarea" style="width: 520px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <button class="layui-btn" lay-submit lay-filter="submitForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    // 配置下拉框 【选择学生】
    var studentSelect = xmSelect.render({
        el: '#studentSelect',
        layVerify: 'required',// layui验证表单类型
        name: 'studentIds',
        max: 6,// 最大可选数
        toolbar: {// 工具栏
            show: true,
            list: ['CLEAR']
        },
        filterable: true,// 开启过滤
        paging: true,// 开启分页
        pageSize: 5,// 每页显示数量
        maxMethod: function (selectedData, item) {// 超出最大时回调函数
            layui.layer.msg('最多可选六位成员！', {time: 1000});
        }
    });

    layui.use(['table', 'layer', 'form'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;

        var _area = ['800px', '600px'];// 添加与修改的公共宽高

        var addTitle = '添加答辩成绩';
        var addFormAction = '${pageContext.request.contextPath}/student-reply-score/add';

        var editTitle = '编辑答辩成绩';
        var editFormAction = '${pageContext.request.contextPath}/student-reply-score/update';

        var detailAJAXURL = '${pageContext.request.contextPath}/student-reply-score/detail';

        var deleteAJAXURL = '${pageContext.request.contextPath}/student-reply-score/delete';
        var deleteMultiAJAXURL = '${pageContext.request.contextPath}/student-reply-score/deleteMulti';

        var allDataAction = '${pageContext.request.contextPath}/student-reply-score/allData';// 表格渲染的请求URL

        // 表单自定义验证
        form.verify({
            studentClassName: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (value == 0) {
                    return '请选择录入的学生班级！';
                }
            },
            studentName: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (value == null) {// 等于null时，说明已选班级但没有学生
                    return '该班级没有学生，请先为该班级录入学生！';
                }
            },
            projectName: function (value, item) {
                if (value == 0) {
                    return '请选择的项目名称！';
                }
            },
            empName: function (value, item) {
                if (value == 0) {
                    return '请选择的任课老师！';
                }
            },
            score1: function (value, item) {
                if (value < 0 || value > 50) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            },
            score2: function (value, item) {
                if (value < 0 || value > 50) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            },
            score3: function (value, item) {
                if (value < 0 || value > 10) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            },
            score4: function (value, item) {
                if (value < 0 || value > 10) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            },
            score5: function (value, item) {
                if (value < 0 || value > 10) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            },
            score6: function (value, item) {
                if (value < 0 || value > 10) {
                    var text = $(item).attr('lay-reqtext');
                    if (text != undefined && text.length > 0) {
                        return text;
                    }
                }
            }
        });

        // 表单的添加
        function showAddForm() {
            var windowIndex = layer.open({
                type: 1,
                title: addTitle,
                resize: false,
                area: _area,
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    var loadIndex = layer.load();
                    layero.hide();
                    $('#actionForm').attr('action', addFormAction);
                    $('#tips').text('(选择两位或两位以上成员时为多人项目，则为每一位成员添加相同成绩)');

                    // 查询表单所需下拉可选值
                    $.get('${pageContext.request.contextPath}/student-reply-score/addTheNeed', {}, function (data) {
                        var select_studentClass = $('#actionForm').find('select[name="studentClassId"]');// 选择班级名称的下拉框
                        var select_project = $('#actionForm').find('select[name="projectId"]');// 选择项目的下拉框
                        var select_emp = $('#actionForm').find('select[name="empId"]');// 选择任课老师的下拉框

                        select_project.empty();// 清空下拉框
                        select_project.append('<option value="0">请选择项目名称</option>');// 默认的select html
                        $.each(data.projectList, function (i, element) {
                            select_project.append('<option value="' + element.projectId + '">' + element.projectName +
                                '</option>');
                        });

                        select_emp.empty();// 清空下拉框
                        select_emp.append('<option value="0">请选择任课老师</option>');// 默认的select html
                        $.each(data.empList, function (i, element) {
                            select_emp.append('<option value="' + element.empId + '">' + element.empName +
                                '</option>');
                        });

                        select_studentClass.empty();// 清空下拉框
                        select_studentClass.append('<option value="0">请选择班级名称</option>');// 默认的select html
                        $.each(data.studentClassList, function (i, element) {
                            select_studentClass.append('<option value="' + element.classId + '">' + element.className +
                                '</option>');
                        });

                        form.render('select');// 重新渲染所有的select
                        layer.close(loadIndex);// 关闭加载层
                        layero.show();
                    }, 'json');
                },
                end: function () {// 层销毁或关闭后触发的回调
                    $('#tips').text('');

                    // 清空学生下拉框
                    studentSelect.update({
                        data: [],
                        disabled: false
                    });
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

        // 表单的编辑
        function showEditForm(objData) {
            var windowIndex = layer.open({
                type: 1,
                title: editTitle,
                resize: false,
                area: _area,
                content: $('#actionBox'),
                success: function (layero, index) {// 弹出层弹出后回调函数
                    var loadIndex = layer.load();
                    layero.hide();
                    $('#actionForm').attr('action', editFormAction);

                    // 查询表单所需下拉可选值
                    $.get('${pageContext.request.contextPath}/student-reply-score/editTheNeed', {replyId: objData.replyId}, function (data) {
                        var select_student = $('#actionForm').find('select[name="studentId"]');// 选择学生的下拉框
                        var select_project = $('#actionForm').find('select[name="projectId"]');// 选择项目的下拉框
                        var select_emp = $('#actionForm').find('select[name="empId"]');// 选择任课老师的下拉框
                        var select_studentClass = $('#actionForm').find('select[name="studentClassId"]');// 选择班级名称的下拉框

                        var currentStudentClassId = data.currentStudentClass.classId;
                        select_studentClass.empty();// 清空下拉框
                        select_studentClass.append('<option value="0">请选择班级名称</option>');// 默认的select html
                        $.each(data.studentClassList, function (i, element) {
                            select_studentClass.append('<option ' + (currentStudentClassId == element.classId ?
                                'selected' : '') + ' value="' + element.classId + '">' +
                                element.className +
                                '</option>');
                        });
                        select_studentClass.attr('disabled', 'disabled');

                        // 渲染学生下拉框
                        var studentId = data.studentReplyScore.studentId;// 当前需要编辑信息的学生id
                        var studentData = [];
                        $.each(data.studentList, function (i, element) {
                            if (studentId == element.studId) {
                                studentData.push({name: element.stuName, value: element.studId, selected: true});
                            } else {
                                studentData.push({name: element.stuName, value: element.studId});
                            }
                        });
                        studentSelect.update({
                            data: studentData,
                            disabled: true
                        });

                        var projectId = data.studentReplyScore.projectId;
                        select_project.empty();// 清空下拉框
                        select_project.append('<option value="0">请选择项目名称</option>');// 默认的select html
                        $.each(data.projectList, function (i, element) {
                            select_project.append('<option ' + (projectId == element.projectId ? 'selected' : '') + ' value="' + element.projectId + '">' +
                                element.projectName +
                                '</option>');
                        });

                        var empId = data.studentReplyScore.empId;
                        select_emp.empty();// 清空下拉框
                        select_emp.append('<option value="0">请选择任课老师</option>');// 默认的select html
                        $.each(data.empList, function (i, element) {
                            select_emp.append('<option ' + (empId == element.empId ? 'selected' : '') + ' value="' + element.empId + '">' + element.empName +
                                '</option>');
                        });

                        form.val('_form', {
                            'replyId': data.studentReplyScore.replyId,
                            'score1': data.studentReplyScore.score1,
                            'score2': data.studentReplyScore.score2,
                            'score3': data.studentReplyScore.score3,
                            'score4': data.studentReplyScore.score4,
                            'score5': data.studentReplyScore.score5,
                            'score6': data.studentReplyScore.score6,
                            'score7': data.studentReplyScore.score7,
                            'remark': data.studentReplyScore.remark
                        });
                        form.render('select');// 重新渲染所有的select
                        layer.close(loadIndex);// 关闭加载层
                        layero.show();
                    }, 'json');
                },
                end: function () {// 层销毁或关闭后触发的回调
                    $('#actionForm').find('input[name="replyId"]').removeAttr('value');
                    $('#actionForm').find('select[name="studentClassId"]').removeAttr('disabled');

                    studentSelect.update({
                        data: [],
                        disabled: false
                    });
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

                        if (data.code == 0) {// 成功
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
                    error: function (request, textStatus) {
                        layer.msg('服务器错误！', {time: 1000});
                        layer.close(loadIndex);
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
                $.post(deleteAJAXURL, {replyId: obj.data.replyId}, function (data) {
                    layer.close(loadIndex);
                    layer.close(index);

                    if (data.code == 0) {// 成功
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

        // 为搜索按钮的添加事件
        $('#searchBtn').click(function (event) {
            var projectSelect = $('#searchForm').find('select[name="projectId"]');
            var studentClassSelect = $('#searchForm').find('select[name="studentClassId"]');

            table.reload('dataTable', {
                url: '${pageContext.request.contextPath}/student-reply-score/search',
                where: {
                    projectId: projectSelect.val(),
                    studentClassId: studentClassSelect.val()
                },
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

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
                    ids.push(element.replyId);
                });
                // 传输id到后台删除数据
                $.post(deleteMultiAJAXURL, {replyIds: ids}, function (data) {
                    layer.close(loadIndex);
                    layer.close(index);
                    if (data.code == 0) {// 成功
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
            // , defaultToolbar: []
            , url: allDataAction
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , groups: 5 //只显示 1 个连续页码
                , first: false //不显示首页
                , last: false //不显示尾页
            }
            , cols: [[
                {type: 'checkbox', fixed: 'left', width: 40}
                , {field: 'replyId', width: 80, title: 'ID', sort: true}
                , {field: 'stuName', width: 90, title: '学生姓名'}
                , {field: 'className', width: 120, title: '学生班级'}
                , {field: 'projectName', width: 120, title: '项目名称'}
                , {field: 'empName', width: 100, title: '任课老师'}
                , {field: 'gradeEmpName', width: 100, title: '打分老师'}
                , {field: 'score1', width: 130, title: '功能完善（50）'}
                , {field: 'score2', width: 130, title: '技术难度（10）'}
                , {field: 'score3', width: 130, title: '界面完美（10）'}
                , {field: 'score4', width: 130, title: '回答问题（10）'}
                , {field: 'score5', width: 130, title: '演示方法（10）'}
                , {field: 'score6', width: 130, title: '语言表达（10）'}
                , {field: 'score7', width: 120, title: '总分（100）'}
                , {field: 'remark', title: '备注', minWidth: 150}
                , {fixed: 'right', width: 120, title: '操作', align: 'center', toolbar: '#action_toolbar'}
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

        // 监听学生班级下拉
        form.on('select(studentClassSelect)', function (data) {
            var selectClassId = data.value;
            // var select_student = $('#actionForm').find('select[name="studentId"]');// 需要改变的下拉框
            if (selectClassId == 0) {
                select_student.empty();// 清空下拉框
                // 选择0 则不发送请求
                form.render('select');
                return;
            }
            var loadIndex = layer.load(1);
            $.get('${pageContext.request.contextPath}/student-reply-score/allStudentByStudentClassId', {studentClassId: selectClassId}, function (data) {
                // 渲染学生下拉框
                var studentData = [];
                $.each(data, function (i, element) {
                    studentData.push({name: element.stuName, value: element.studId});
                });
                studentSelect.update({
                    data: studentData
                });

                layer.close(loadIndex);
            }, 'json');
        });
    });
</script>
</body>
</html>