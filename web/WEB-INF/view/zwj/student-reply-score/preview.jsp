<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/14
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生成绩预览</title>
    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <style type="text/css">
        .selectBox {
            position: fixed;
            top: 10px;
            right: 10px;
        }
    </style>
</head>
<body style="padding: 30px">
<div class="layui-btn-container">
    <a class="layui-btn layui-btn-sm" href="javascript:history.go(-1);">返回</a>
</div>
<%--没有该学生的答辩成绩时--%>
<c:if test="${empty projectList}">
    该学生还没有答辩成绩！
</c:if>

<%--有该学生的答辩成绩时--%>
<c:if test="${!empty projectList}">
    <div id="main">
        <div id="echarts" style="min-height:500px;height:100%;"></div>

        <div class="layui-form selectBox">
            <div class="layui-form-item">
                <select name="projectId" lay-filter="projectSelect">
                    <c:forEach items="${projectList}" var="project">
                        <option value="${project.projectId}">${project.projectName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            window.onresize = function () {
                //重置容器高宽
                myEcharts.resize();
            };

            var myEcharts = echarts.init(document.getElementById('echarts'));

            function reload(stuId, projectId) {
                // 发送请求获取学生成绩并渲染
                $.get('${pageContext.request.contextPath}/student-reply-score/findDataToPreview', {
                    stuId: stuId,
                    projectId: projectId
                }, function (data) {
                    myEcharts.setOption({
                        title: {
                            text: data.stuName + '的【' + data.projectName + '】成绩分数',
                            subtext: '评分老师：' + data.gradeEmpName + ' 总分数：' + data.score7,
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a}<br>{b} : {c}分"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: ['功能完善（50）', '技术难度（10）', '界面完美（10）', '回答问题（10）', '演示方法（10）', '语言表达（10）']
                        },
                        series: [
                            {
                                name: data.projectName,
                                type: 'pie',
                                radius: '50%',
                                data: [
                                    {value: data.score1, name: '功能完善（50）'},
                                    {value: data.score2, name: '技术难度（10）'},
                                    {value: data.score3, name: '界面完美（10）'},
                                    {value: data.score4, name: '回答问题（10）'},
                                    {value: data.score5, name: '演示方法（10）'},
                                    {value: data.score6, name: '语言表达（10）'}
                                ],
                                label: {
                                    normal: {
                                        formatter: '{b} : {c}分'
                                    }
                                },
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    });
                }, 'json');
            }

            layui.use('form', function () {
                var form = layui.form;

                form.on('select(projectSelect)', function (data) {
                    reload(${param.stuId}, data.value);
                });
            });

            // 第一次执行
            var projectSelect = $('select[lay-filter="projectSelect"]');
            var projectId = projectSelect.val();
            if (projectId == undefined || projectId == null || projectId <= 0) {
                layui.layer.msg('该学生还没有答辩成绩！', {time: 1000});
                return;
            }
            reload(${param.stuId}, projectId);
        })
    </script>
</c:if>
</body>
</html>
