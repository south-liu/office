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
<head>
    <title>添加申请</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-input-block {
        margin-left: 110px;
        min-height: 36px;
        width: 190px;
    }
    .layui-form-label {
        float: left;
        display: block;
        padding: 9px 15px;
        width: 100px;
        font-weight: 400;
        line-height: 20px;
        text-align: right;
    }
    .layui-input-block {
        margin-left: 130px;
        min-height: 36px;
        width: 190px;
    }
</style>
<body style="padding: 30px;">
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">设备名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="equipmentType" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明：</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="sub">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form', 'layer'], function(){
        var form = layui.form
            ,layer = layui.layer
        //监听提交
        form.on('submit(sub)', function(data){
            console.log(data.field);
            var fd = data.field;
            var lod = layer.load();
            //部门数据
            $.ajax({
                url: "${pageContext.request.contextPath}/repair/sturepairadd",
                type: "post",
                async:true,
                dataType: "json",
                data:{
                    equipmentType:fd.equipmentType,
                    remark:fd.remark
                },
                success: function (data) {
                    layer.close(lod);
                    layer.msg('添加成功',{
                        time:1000
                    },function () {
                        //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //parent.layer.close(index); //再执行关闭
                        window.parent.location.reload();
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
            return false;
        });

    });
</script>
</body>

</html>
