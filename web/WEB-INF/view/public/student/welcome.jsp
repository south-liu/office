<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/9
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>欢迎</title>
    <base href="${pageContext.request.contextPath}/">
    <jsp:include page="../head.jsp"></jsp:include>
</head>
<body>

<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">我的通知</div>
                <div class="layui-card-body">
                    <c:forEach items="${assessmentList}" var="assessment">
                        <p>你有一条教师考评可以进入：<a href="s/assessment/writerAssessment?assessmentId=${assessment.assessmentId}">进入</a></p>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">通知公告</div>
                <div class="layui-card-body">

                    <c:forEach items="${myNotice}" var="notice" begin="0" end="3">

                        <a href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/studentSide/viewNotice?noticeId=${notice.noticeId}';">
                            <i class="layui-icon layui-icon-tips"></i> ${notice.title}
                            <span style="float: right;">${notice.empName}&nbsp;&nbsp;${notice.noticeIime}</span>
                        </a><br>

                    </c:forEach>

                    <a style="font-weight: 700;" href="javascript:layer.load();
                    location.href='${pageContext.request.contextPath}/studentSide/toNoticeList';">
                        所有公告 <i class="layui-icon layui-icon-next"></i>
                    </a><br>

                </div>
            </div>
        </div>

        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header"><span id="titSpan"></span></div>
                <div class="layui-card-body">
                    <span id="conSpan"></span>
                </div>
            </div>
        </div>

    </div>
    <%--<div class="layui-card" style="width:300px">
        <div class="layui-card-header">我的通知</div>
        <div class="layui-card-body">
            <c:forEach items="${assessmentList}" var="assessment">
                <p>你有一条教师考评可以进入：<a href="s/assessment/writerAssessment?assessmentId=${assessment.assessmentId}">进入</a></p>
            </c:forEach>
        </div>
    </div>--%>
</div>
<script type="text/javascript">
    layui.use(['carousel', 'layer'], function () {
        var carousel = layui.carousel;
        var layer = layui.layer;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '100%', //设置容器宽度
            arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        /*layer.open({
            type: 1,
            title: '你收到一条教师考评',
            closeBtn: 1, //不显示关闭按钮
            shade: false,
            area: ['340px', '215px'],
            offset: 'rb', //右下角弹出
            // time: 2000, //2秒后自动关闭
            anim: 2,
            content: '<a>1234</a>'
        });*/
    });
</script>
</body>
</html>
