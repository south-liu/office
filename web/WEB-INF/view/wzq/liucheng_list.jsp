<%--
  Created by IntelliJ IDEA.
  User: 南城
  Date: 2019/11/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>流程设置</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<%--顶部按钮--%>
<div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
    <button class="layui-btn layui-btn-sm" id="add" onclick="add()" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-upload-drag" style="font-size: 15px; color: #FFF"></i>上传流程</button>
</div>

<%--添加表单--%>
<table width="100%" cellpadding="2" bgcolor="#e6e6e6" id="test" style="border-collapse: inherit; border-spacing: 1px">
    <tr bgcolor="#F2F2F2" style="height: 38px">
        <th><font size="2" color="#666">流程ID</font></th>
        <th><font size="2" color="#666">流程名称</font></th>
        <th><font size="2" color="#666">流程KEY</font></th>
        <th><font size="2" color="#666">操作</font></th>
    </tr>
    <c:forEach items="${liucheng}" var="list">
        <tr bgcolor="#FFF" style="height: 38px;text-align: center">
            <td><font size="2" color="#666">${list.id}</font></td>
            <td><font size="2" color="#666">${list.name}</font></td>
            <td><font size="2" color="#666">${list.key}</font></td>
            <td>
                <a class="layui-btn layui-btn-danger layui-btn-xs" href="${pageContext.request.contextPath}/TP/dowliucheng?id=${list.id}" lay-event="dow">下载流程图</a>
                <a class="layui-btn layui-btn-xs" href="${pageContext.request.contextPath}/TP/liuchengimgs?did=${list.deploymentId}&&imageName=${list.diagramResourceName}" target="_blank" lay-event="cha">查看流程图</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs del" lay-event="del"><input type="hidden" id="deploymentId" value="${list.deploymentId}"/>删除流程</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    //头部上传按钮点击事件
    function add(){
        location.href='${pageContext.request.contextPath}/TP/toaddliucheng';
    }

    //数据表格按钮点击事件
    layui.use(['layer'], function(){
        var layer = layui.layer;

        $('.del').click(function () {
            layer.confirm('您确定要删除该流程吗？', function(index){
                var deploymentId = $("#deploymentId").val();
                //异步删除数据
                var lindex = layer.load();
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/TP/delliucheng",
                    async:true,  //true为异步请求，false为同步请求。
                    dataType:"text",  //请求返回的数据类型格式。
                    data:{id:deploymentId},
                    success:function(data) {
                        layer.close(lindex);
                        layer.msg('已删除！', {
                            icon:1,
                            time:1000
                        },function () {
                            location.reload();  //显示弹窗后刷新
                        });
                    },
                    error:function () {
                        layer.close(lindex);
                        layer.msg("服务器错误！");
                    }
                });
            });
        });
    });
</script>
</body>
</html>