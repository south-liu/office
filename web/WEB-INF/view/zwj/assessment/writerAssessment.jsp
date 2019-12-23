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
    <style type="text/css">
        .layui-form .layui-form-item .layui-form-label {
            width: 130px;
        }
    </style>
</head>
<body>
<c:if test="${!errorState}">
    ${assessment}
    ${assessmentEmp}
    ${evaluationStandardList}
    <blockquote class="layui-elem-quote">
        <h1>${assessmentEmp.postName}【${assessmentEmp.empName}】的教师考评：</h1>
        <p style="margin-top: 10px;">考评时间：${assessment.startTime}&ensp;~&ensp;${assessment.endTime}</p>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>内容</legend>
        <div class="layui-field-box">
                <%--<input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">--%>
            <form class="layui-form" action="">
                <c:forEach items="${evaluationStandardList}" var="evaluationStandard" varStatus="i">
                    <div class="layui-form-item">
                        <label class="layui-form-label">${evaluationStandard.evaluationName}：</label>
                        <div class="layui-input-inline">
                            <div id="test${i.index}"></div>
                        </div>
                    </div>
                </c:forEach>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">建议：</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容" name="suggest" class="layui-textarea" style="width:90%;"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="_form">提交</button>
                </div>
            </form>
        </div>
    </fieldset>

    <script type="text/javascript">
        layui.use(['rate', 'form'], function () {
            var rate = layui.rate;
            var form = layui.form;

            var rateValue = new Array();// 评分控件的个数

            // 只读
            <c:forEach items="${evaluationStandardList}" varStatus="i">
            rate.render({
                elem: '#test${i.index}'
                , text: true
                , setText: function (value) {
                    var arrs = {
                        '1': '极差'
                        , '2': '差'
                        , '3': '中等'
                        , '4': '好'
                        , '5': '极好'
                    };
                    if (value > 0) {
                        this.span.text(arrs[value] || (value + "星"));
                    }
                }
                , choose: function (value) {
                    rateValue[${i.index}] = value;
                }
            });
            </c:forEach>


            form.on('submit(_form)', function (data) {
                var totalityNum = 0;// 计算总分数
                $.each(rateValue, function (i, element) {
                    totalityNum += element;
                });
                $.post('${pageContext.request.contextPath}/s/assessment/insertAssessmentInformation', {
                    assessmentId: ${assessment.assessmentId},
                    grossScore: totalityNum,
                    suggest: $('textarea[name="suggest"]').val()
                }, function (data) {
                    if (data.code == 0 && data.result > 0) {
                        layer.msg(data.msg, {time: 1000}, function () {
                            location.href = '${pageContext.request.contextPath}/studentSide/welcome';
                        });
                    } else if (data.code == 1) {
                        layer.msg(data.msg, {time: 1000});
                    } else if (data.code == 2) {
                        layer.msg(data.msg, {time: 1000});
                    } else if (data.code == 3) {
                        layer.msg(data.msg, {time: 1000}, function () {
                            window.top.location.href = '${pageContext.request.contextPath}/studentSide/login';
                        });
                    }
                }, 'json');

                return false;
            });
        });
    </script>
</c:if>
<c:if test="${errorState}">
    <script type="text/javascript">
        layui.use(['layer'], function () {
            var layer = layui.layer;
            layer.msg('${msg}', {time: 1000}, function () {
                window.top.location.href = '${pageContext.request.contextPath}/studentSide/login';
            });
        });
    </script>
</c:if>
</body>
</html>
