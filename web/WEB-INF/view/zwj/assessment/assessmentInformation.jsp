<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/19
  Time: 8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>考评详细</title>

    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
<div style="clear: both;">
    <%--图表展示--%>
    <div style="float: left;width: 60%;min-width: 500px;">
        <div id="echarts" style="min-height:500px;height:100%;"></div>
    </div>
    <%--数据显示--%>
    <div style="float: left;width: 40%;min-width: 420px;">
        <div style="float: right;width:20%;">
            <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="sidebar" style="width:100%;">
                <c:forEach items="${studentList}" var="student" varStatus="i">
                    <li class="layui-nav-item ${i.first ? 'layui-this' : ''}">
                        <a href="javascript:;" sid="${student.studId}" title="${student.stuName}">${student.stuName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div style="float: right;width:80%;display:none;" id="contextBox">
            <blockquote class="layui-elem-quote layui-quote-nm" id="scoreText">
                <div style="clear: both;">
                    <span style="float: left;">评分详细：</span>
                    <span style="float: right;">总分：<strong id="totalityBox"></strong></span>
                </div>
                <table class="layui-table" lay-skin="line" id="informationTable">
                    <thead>
                    <tr>
                        <th>考评内容</th>
                        <th>分值</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </blockquote>
            <fieldset class="layui-elem-field">
                <legend>建议</legend>
                <div class="layui-field-box" id="suggestText"></div>
            </fieldset>
        </div>
        <span style="clear: both;"></span>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var myEcharts = echarts.init(document.getElementById('echarts'));
        window.onresize = function () {
            //重置容器高宽
            myEcharts.resize();
        };

        layui.use(['rate', 'element', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

            $.get('${pageContext.request.contextPath}/assessment/queryAssessmentEvaluationAvgScore', {assessmentId: ${assessment.assessmentId}}, function
                (data) {
                if (data == null) return;
                // 渲染Echarts图表
                var echartsData = data.echartsData;
                /*myEcharts.setOption({
                    title: {
                        text: '各项考评内容的平均分',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a}<br>{b} : {c}分"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: echartsData.lengendData
                    },
                    series: [
                        {
                            name: '',
                            type: 'pie',
                            radius: '60%',
                            data: echartsData.seriesDataList,
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
                });*/
                myEcharts.setOption({
                    title: {
                        text: '各项考评内容的平均分',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c}分 ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        //图例文字样式
                        textStyle: {
                            color: '#000',
                            fontSize: 14,
                            fontWeight: '700'
                        },
                        data: echartsData.lengendData
                    },
                    toolbox: {
                        show: false,
                    },
                    calculable: true,
                    color: ['#FD6035', '#82C730', '#FACE2F', '#36B6DD', '#0084C5'],//自己设置扇形图颜色
                    series: [
                        {
                            name: '图例占比',
                            type: 'pie',
                            radius: '60%',
                            roseType: 'radius',
                            x: '50%',               // for funnel
                            sort: 'ascending',     // for funnel
                            data: echartsData.seriesDataList,
                            //标线的属性设置，以及显示的文字
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        formatter: '{c}分',
                                        textStyle: {
                                            color: '#000',
                                            fontSize: 14,
                                            fontWeight: '700'
                                        }

                                    },
                                    //标线长度，宽度
                                    labelLine: {
                                        show: true,
                                        length: 40,
                                        lineStyle: {
                                            width: 2
                                        }
                                    }
                                },
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

            // 刷新学生的考评内容信息
            function reloadDetail(studentId, isFirst) {
                if (!isFirst) {// 第一次不显示加载层
                    var loadIndex = layer.load();
                }
                // 获取当前学生对于这次考评的考评内容及分值信息
                $.get('${pageContext.request.contextPath}/assessment/queryAssessmentInformation', {
                    studentId: studentId,
                    assessmentId: ${assessment.assessmentId}
                }, function (data) {
                    if (!isFirst) {
                        layer.close(loadIndex);
                    }
                    $('#contextBox').show();

                    if (data.code == 0) {
                        var assessmentInformationList = data.result.assessmentInformation;
                        var tbody = $('#informationTable tbody');
                        tbody.empty();
                        $.each(assessmentInformationList, function (i, element) {
                            tbody.append('<tr><td>' + element.evaluationName + '</td>' + '<td><div id="test' + i + '"></div></td>');
                            //渲染
                            rate.render({
                                elem: '#test' + i  //绑定元素
                                , value: element.grossScore
                                , readonly: true
                            });
                        });

                        $('#suggestText').text(data.result.assessmentSuggest.suggest);// 建议
                        $('#totalityBox').text(assessmentInformationList.length * 5);// 建议
                    } else if (data.code == 1) {
                        layer.msg(data.msg, {time: 1000})
                    }
                }, 'json');
            }

            // 进入页面第一次请求
            reloadDetail($('.layui-this a').attr('sid'), true);

            $('ul[lay-filter="sidebar"] li a').click(function (event) {
                var studentId = $(this).attr('sid');// 获取点击的学生id
                reloadDetail(studentId, false);// 不是第一次执行
            });
        });
    });
</script>
</body>
</html>
