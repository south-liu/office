<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看公告</title>
    <jsp:include page="../../../public/head.jsp"></jsp:include>
</head>
<body style="padding:30px">

    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);">返回</button>
    </div>

    <center>
        <h1 style="margin-bottom: 10px;">${notice.title}</h1>
        <font color="gray" style="font-size:12px;">${notice.empName}&nbsp;&nbsp;${notice.noticeIime}</font>
    </center>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>内容</legend>
    </fieldset>
    <blockquote class="layui-elem-quote layui-quote-nm">
        <div id="noticeContent"></div>
    </blockquote>

<script type="text/javascript">

    $('#noticeContent').html('${notice.content}');

    layui.use(['form','layedit'],function () {
        var form = layui.form;
        var layedit = layui.layedit;

        var myEdit = layedit.build('myEdit',{

        });

        form.on('submit(sub)',function (data) {
            //同步编辑器内容到textarea
            layedit.sync(myEdit);

            var lod = layer.load();
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/notice/addNotice" ,//url
                data: $('#noticeForm').serialize(),
                success: function (result) {
                    layer.close(lod);
                    layer.msg('添加成功',{
                        time:1000
                    },function () {
                        location.href="${pageContext.request.contextPath}/notice/toNoticeList";
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误',{
                        icon:2,
                        time:1000
                    });
                }
            });

            return false;
        });

    });
</script>
</body>
</html>
