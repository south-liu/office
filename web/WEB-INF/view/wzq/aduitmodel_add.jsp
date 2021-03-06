<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加考核指标</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
    <form class="layui-form" action="">

        <div class="layui-form-item" style="margin-top: 20px; margin-left: 20px">
            <label class="layui-form-label">考核内容</label>
            <div class="layui-input-block">
                <input type="text" name="aduitName" style="width: 200px" lay-verify="required" autocomplete="off" placeholder="请输入考核内容" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">考核分数</label>
            <div class="layui-input-block">
                <input type="text" name="scores" style="width: 200px" autocomplete="off" placeholder="请输入考核分数" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 20px">
            <label class="layui-form-label">考核部门</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="deptId" lay-filter="aihao" lay-verify="required">
                    <option value="">请选择考核部门</option>
                    <c:forEach items="${dept}" var="list">
                        <option value="${list.deptId}">${list.deptName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item layui-form-text" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入说明" name="remark" style="width: 200px" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 35px">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

<%--    监听非空--%>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer

            //监听提交
            form.on('submit(demo1)', function(obj){
                var data = obj.field;
                var lindex = layer.load();
                //部门数据
                $.ajax({
                    url: "${pageContext.request.contextPath}/MY/addaduitmodel",
                    type: "post",
                    async:true,
                    dataType: "json",
                    data:{
                        aduitName:data.aduitName,
                        scores:data.scores,
                        deptId:data.deptId,
                        remark:data.remark
                    },
                    success: function (data) {
                        layer.close(lindex);
                        layer.msg('添加成功',{
                            time:1000
                        },function () {
                            //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            //parent.layer.close(index); //再执行关闭
                            window.parent.location.reload();  //添加成功之后关闭本页面且刷新原页面（直接重新访问原页面）
                        });
                    },
                    error:function () {
                        layer.close(lindex);
                        layer.msg('服务器错误');
                    }
                });
                return false;
            });
        });
    </script>

</body>
</html>
