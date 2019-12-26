<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/16
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>考评管理</title>

    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
</head>
<body>

<form class="layui-form" style="margin-top:10px;">
    <div class="layui-inline">
        <label class="layui-form-label">考评教师：</label>
        <div class="layui-input-inline">
            <select name="empId">
                <option value="">请选择考评教师</option>
                <optgroup label="班主任">
                    <c:forEach var="emp" items="${classTeacherEmp}">
                        <c:if test="${emp.postName == '班主任'}">
                            <option value="${emp.empId}">${emp.empName}</option>
                        </c:if>
                    </c:forEach>
                </optgroup>
                <optgroup label="授课老师">
                    <c:forEach var="emp" items="${classTeacherEmp}">
                        <c:if test="${emp.postName == '授课老师'}">
                            <option value="${emp.empId}">${emp.empName}</option>
                        </c:if>
                    </c:forEach>
                </optgroup>
            </select>
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">开始时间：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="startTime" id="searchStartTime" placeholder="考评开始时间">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">结束时间：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="endTime" id="searchEndTime" placeholder="考评结束时间">
        </div>
    </div>
    <div class="layui-inline">
        <button class="layui-btn" lay-submit lay-filter="searchForm">
            <i class="layui-icon">&#xe615;</i> 搜索
        </button>
    </div>
</form>
<table class="layui-hide" id="dataTable" lay-filter="table_filter"
       style="margin:0px;"></table>

<%--表格顶部工具栏--%>
<script type="text/html" id="top_toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">开启考评</button>
        <%--<button class="layui-btn layui-btn-sm" lay-event="delete">删除考评</button>--%>
    </div>
</script>
<%--数据最右边的操作栏--%>
<script type="text/html" id="action_toolbar">
    {{#  if(d.state == 0){ }}
    <a class="layui-btn layui-btn-xs" lay-event="delayed" time="30">延时30分钟</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="endAssessment">结束考评</a>
    {{#  }else{ }}
    <a class="layui-btn layui-btn-xs" lay-event="asessmentDetail">考评详细</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除考评</a>
    {{#  } }}
</script>
<%--操作表单--%>
<div id="actionBox" style="display: none;margin-top:20px;padding: 80px;">
    <form class="layui-form" method="post" id="actionForm" lay-filter="_form">
        <input type="hidden" name="assessmentId">
        <div class="layui-form-item">
            <label class="layui-form-label">考评教师：</label>
            <div class="layui-input-inline">
                <select name="empId" lay-verify="required" lay-filter="empSelect">
                    <option value="">请选择</option>
                    <optgroup label="班主任">
                        <c:forEach var="emp" items="${classTeacherEmp}">
                            <c:if test="${emp.postName == '班主任'}">
                                <option value="${emp.empId}">${emp.empName}</option>
                            </c:if>
                        </c:forEach>
                    </optgroup>
                    <optgroup label="授课老师">
                        <c:forEach var="emp" items="${classTeacherEmp}">
                            <c:if test="${emp.postName == '授课老师'}">
                                <option value="${emp.empId}">${emp.empName}</option>
                            </c:if>
                        </c:forEach>
                    </optgroup>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">考评班级：</label>
            <div class="layui-input-inline">
                <select name="studentClassId" lay-verify="required" lay-search lay-filter="studentClassSelect">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间：</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" class="layui-input" name="startTime" id="startTime" style="width:190px;">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">结束时间：</label>
            <div class="layui-inline">
                <input type="text" class="layui-input" name="endTime" id="endTime" style="width:190px;">
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
    layui.use(['table', 'layer', 'form', 'laydate'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;

        var laydateOptions = {
            type: 'datetime'
        };
        //执行一个laydate实例
        laydate.render({
            elem: '#searchStartTime', //指定元素,
            type: laydateOptions.type
        });
        laydate.render({
            elem: '#searchEndTime', //指定元素
            type: laydateOptions.type
        });
        laydate.render({
            elem: '#startTime', //指定元素
            type: laydateOptions.type,
            value: new Date(),
            min: '0'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: laydateOptions.type,
            min: 0
        });

        var formArea = ['580px', '580px'];// 编辑和添加表单的宽高

        <%--var addFormTitle = '添加考评';--%>
        <%--var addFormURL = '${pageContext.request.contextPath}/evaluation-standard/add';--%>

        <%--var editFormTitle = '编辑考评';--%>
        <%--var editFormURL = '${pageContext.request.contextPath}/evaluation-standard/add';--%>

        var addOptions = {
            title: '开启考评',
            url: '${pageContext.request.contextPath}/assessment/add'
        };
        var editOptions = {
            title: '编辑考评',
            url: '${pageContext.request.contextPath}/assessment/update',
            detailUrl: '${pageContext.request.contextPath}/assessment/detail'
        };
        var deleteOptions = {
            url: '${pageContext.request.contextPath}/assessment/delete',
            multiUrl: '${pageContext.request.contextPath}/assessment/deleteMulti'
        };
        var queryData = {
            url: '${pageContext.request.contextPath}/assessment/allData'
        };

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
                , {field: 'assessmentId', width: 80, title: 'ID', sort: true}
                , {field: 'postName', title: '考评类型'}
                , {field: 'empName', title: '教师名称'}
                , {field: 'className', title: '考评班级'}
                , {field: 'startTime', title: '开始时间', width: 180, sort: true}
                , {field: 'endTime', title: '结束时间', width: 180, sort: true}
                , {field: 'averageScore', title: '平均分', sort: true}
                , {
                    field: 'state', title: '考评状态', templet: function (d) {
                        if (d.state == 0) {
                            return '待考评中';
                        } else if (d.state == 1) {
                            return '考评完成';
                        }
                    }
                }
                , {fixed: 'right', width: 200, title: '操作', align: 'center', toolbar: '#action_toolbar'}
            ]]
        });

        // 监听表格搜索
        form.on('submit(searchForm)', function (data) {
            table.reload('dataTable', {url: '${pageContext.request.contextPath}/assessment/searchByOptions', where: data.field});
            <%--var loadIndex = layer.load();--%>
            <%--$.get('${pageContext.request.contextPath}/assessment/searchByOptions', data.field, function (data) {--%>
            <%--    layer.close(loadIndex);--%>
            <%--    console.log(data);--%>
            <%--}, 'json');--%>
            return false;
        });

        // 监听头部工具栏事件
        table.on('toolbar(table_filter)', function (obj) {//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var event = obj.event;

            if (event == 'add') {
                showAddForm();// 展示添加表单的编辑框
            }/* else if (event == 'delete') {// 删除多选
                var checkStatus = table.checkStatus('dataTable');

                deleteMulti(checkStatus);// 删除多行
            }*/
        });
        // 监听行中工具栏事件
        table.on('tool(table_filter)', function (obj) {
            var event = obj.event;
            if (event == 'delayed') {// 延时考评
                delayAssessment(obj.data, $(this).attr('time'));
            } else if (event == 'endAssessment') {// 结束考评
                endAssessment(obj.data);
            } else if (event == 'asessmentDetail') {// 考评详细
                lookDetail(obj.data);
            } else if (event == 'del') {
                deleteData(obj);// 删除数据
            }
        });

        // 监听操作表单中的教师下拉框改变可选班级
        form.on('select(empSelect)', function (data) {
            var loadIndex = layer.load();

            var empId = data.value;
            $.get('${pageContext.request.contextPath}/assessment/queryStudentClassByEmpId', {
                empId: empId
            }, function (data) {
                layer.close(loadIndex);

                var studentClassSelect = $('select[name="studentClassId"]');
                studentClassSelect.empty();
                $.each(data, function (i, element) {
                    studentClassSelect.append('<option value="' + element.classId + '">' + element.className + '</option>');
                });
                form.render('select');
            }, 'json');
        });

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
                    $.get(editOptions.detailUrl, {evaluationId: dataObject.assessmentId}, function (data) {
                        form.val('_form', {
                            "evaluationId": data.assessmentId,
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
            layer.confirm('确定删除该条考评吗？', function (index) {
                var loadIndex = layer.load();
                // 传输id到后台删除数据
                $.post(deleteOptions.url, {evaluationId: obj.data.assessmentId}, function (data) {
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
        /*function deleteMulti(checkStatus) {
            var hintMsg;// 提示信息
            if (checkStatus.data.length <= 0) {
                layer.msg('请选中至少一行！', {icon: 0, time: 2000});
                return;
            } else if (checkStatus.data.length == 1) {
                hintMsg = '确定删除该条考评吗？';
            } else {
                hintMsg = '确定删除' + checkStatus.data.length + '条考评吗？';
            }
            layer.confirm(hintMsg, function (index) {
                var loadIndex = layer.load();
                var ids = [];
                $.each(checkStatus.data, function (i, element) {
                    ids.push(element.assessmentId);
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
        }*/

        // 结束考评
        function endAssessment(objectData) {
            layer.confirm('确定提前结束该次考评吗？', function (index) {
                layer.close(index);
                var loadIndex = layer.load();
                $.get('${pageContext.request.contextPath}/assessment/endAssessment', {assessmentId: objectData.assessmentId}, function (data) {
                    layer.close(loadIndex);

                    if (data.code == 0 && data.result > 0) {
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

        // 查看考评详细，先查看是否有人进行了考评，没有则弹窗
        function lookDetail(objectData) {
            var loadIndex = layer.load();
            $.get('${pageContext.request.contextPath}/assessment/assessmentInformationTotality', {assessmentId: objectData.assessmentId}, function (data) {
                layer.close(loadIndex);

                if (data.code == 0 && data.result > 0) {
                    var windowIndex = layer.open({
                        type: 2,
                        title: '考评详细',
                        resize: false,
                        content: ['${pageContext.request.contextPath}/assessment/lookDetailList?assessmentId=' + objectData.assessmentId],
                        area: ['100%', '100%'],
                        success: function (layero, index) {
                            // console.log(1)
                        },
                        end: function () {
                            // console.log(2)
                        }
                    });
                } else if (data.code == 0 && data.result <= 0) {
                    layer.msg('没有同学进入这次考评！', {time: 1000});
                } else if (data.code == 1) {
                    layer.msg(data.msg, {time: 1000});
                }
            }, 'json');
        }

        // 延迟考评时间，将时间延后
        function delayAssessment(objData, time) {
            var loadIndex = layer.load();
            $.get('${pageContext.request.contextPath}/assessment/changeEndTime', {assessmentId: objData.assessmentId, time: time}, function (data) {
                layer.close(loadIndex);
                if (data.code == 0 && data.result > 0) {
                    layer.msg(data.msg, {
                        time: 1000
                    });
                    table.reload('dataTable', {});// 重载表格
                } else if (data.code == 1) {
                    layer.msg(data.msg, {time: 1000});
                }
            }, 'json');
        }
    })
    ;
</script>
</body>
</html>
