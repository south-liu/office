<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/4
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加班级类型</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<body>
<form class="layui-form" style="width: 40%;margin-top: 40px;">

    <div class="layui-form-item">
        <label class="layui-form-label">类别名称：</label>
        <div class="layui-input-block">
            <input type="text" name="username" required lay-verify="required" placeholder="请输入班级类别名称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="submit" id="upload">添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    layui.use(['form'], function () {
        var form = layui.form;
        //执行一个laydate实例

        form.on('submit(submit)', function (data) {
            var formData = new FormData();
            formData.append('username', data.field.username);
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/addUser',
                processData: false,//jquery 是否对数据进行 预处理
                contentType: false,//不要自己修改请求内容类型
                async: true,
                dataType: 'json',
                data: formData,
                success: function (data) {
                    layer.msg(data, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        location.href = '${pageContext.request.contextPath}/user/toList';
                    });
                },
                error: function () {
                    layer.msg('服务器错误，请稍后再试！');
                }
            });
            return false;
        })});
</script>
</body>
</html>
