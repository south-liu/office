<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/9
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    .layui-quote-nm {
        border-style: solid;
        border-width: 1px 1px 1px 5px;
        background: 0 0;
    }

    .layui-icon {
        font-family: layui-icon !important;
        font-size: 16px;
        font-style: normal;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    .test111 {
        font-size: smaller;
        color: blue;
    }
</style>
<head>
    <title>问题反馈</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/template.css" media="all">
<body layadmin-themealias="default" style="" rlt="1">
<form class="layui-form" action="" lay-filter="example">
    <blockquote class="layui-elem-quote layui-quote-nm">
        <label>${feedbackVO.remark}</label></br>
        <div><img src="${pageContext.request.contextPath}/${feedbackVO.image}"/></div>
        <div class="test111">
            <label>${feedbackVO.empname}</label>于<label>${feedbackVO.feedbackTime}</label>
        </div>
    </blockquote>
</form>
<div class="layui-fluid layadmin-message-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-form">
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea id="feedbackMsgName" name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item" style="overflow: hidden;">
                    <div class="layui-input-block layui-input-right">
                        <button class="layui-btn" id="sub">发表</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
            <c:forEach items="${feedBackMsgVO}" var="msg">
                <div class="media-body">
                    <div class="pad-btm">
                        <p class="fontColor"><a href="javascript:;">${msg.userName}</a></p>
                        <p class="min-font">
                      <span class="layui-breadcrumb" lay-separator="-" style="visibility: visible;">
                        <a href="javascript:;" class="layui-icon layui-icon-cellphone"></a><span
                              lay-separator="">-</span>
                        <a href="javascript:;">从移动</a><span lay-separator="">-</span>
                        <a href="javascript:;">${msg.singDate}</a>
                      </span>
                        </p>
                    </div>
                    <p class="message-text">${msg.feedbackMsgName}</p>
                </div>
            </c:forEach>
        </div>

    </div>
</div>

<script type="text/javascript">
    layui.use('layer', function () {
        var layer = layui.layer;

        $('#sub').click(function () {
            var feedbackMsgName = $('#feedbackMsgName').val().trim();
            if (feedbackMsgName != '' && feedbackMsgName != null) {
                var lod = layer.load();
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/feedback/addmsg",
                    async: true,
                    dataType: "json",
                    data: {
                        feedbackId:${feedbackVO.feedbackId},
                        feedbackMsgName: feedbackMsgName
                    },
                    success: function (data) {
                        layer.close(lod);
                        alert("回复成功");
                        // layer.msg("回复成功", {
                        //     icon: 1,
                        //     time: 1000
                        // });
                        window.location.reload();

                    },
                    error: function () {
                        layer.close(lod);
                        layer.msg("服务器错误", {
                            icon: 2,
                            time: 1000
                        });
                    }
                });
            } else {
                console.log(0);
                layer.msg('请输入内容！', {
                    icon: 0
                });
            }
        });
    });
</script>
</body>
</html>
