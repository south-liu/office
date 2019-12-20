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
</head>
<body>
<div style="float: left;width:20%;">
    <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="sidebar" style="width:100%;">
        <c:forEach items="${studentList}" var="student" varStatus="i">
            <li class="layui-nav-item"><a href="javascript:;" class="${i.first ? 'layui-this' : ''}" sid="${student.studId}">${student.stuName}</a></li>
        </c:forEach>
    </ul>
</div>
<div style="float: left;width:80%;display:none;" id="contextBox">
    <blockquote class="layui-elem-quote layui-quote-nm" id="scoreText">评分：<span></span></blockquote>
    <fieldset class="layui-elem-field">
        <legend>建议</legend>
        <div class="layui-field-box" id="suggestText">
        </div>
    </fieldset>
</div>

<script type="text/javascript">
    $(function () {
        layui.use(['rate', 'element', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

            // 刷新详细信息
            function reloadDetail(studentId, first) {
                if (first > 0) {
                    var loadIndex = layer.load();
                }
                // 发送请求获取有关学生的考评
                $.get('${pageContext.request.contextPath}/assessment/queryAssessmentInformation', {
                    studentId: studentId,
                    assessmentId:${assessment.assessmentId}
                }, function (data) {
                    if (first > 0) {
                        layer.close(loadIndex);
                    }
                    $('#contextBox').show();

                    if (data.code == 0) {
                        $('#scoreText span').text(data.result.grossScore);
                        $('#suggestText').text(data.result.suggest);
                    } else if (data.code == 1) {
                        layer.msg(data.msg, {time: 1000})
                    }
                }, 'json');
            }

            // 进入页面第一次请求
            reloadDetail($('.layui-this').attr('sid'), 0);

            $('ul[lay-filter="sidebar"] li a').click(function (event) {
                if ($(this).parent()[0].classList.contains('layui-this')) {// 如果是以点击的则不重新获取
                    return;
                }

                var studentId = $(this).attr('sid');// 获取点击的学生id
                reloadDetail(studentId, 1);
            });
        });
    });
</script>
</body>
</html>
