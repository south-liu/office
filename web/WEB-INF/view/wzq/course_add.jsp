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
    <title>添加课程</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
    <form class="layui-form" action="">

        <div class="layui-form-item" style="margin-top: 20px; margin-left: 20px">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-block">
                <input type="text" name="courseName" style="width: 200px" lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 20px">
            <label class="layui-form-label">是否必修</label>
            <div class="layui-input-block">
                <input type="radio" name="isObligatory" value="是" title="是" checked="">
                <input type="radio" name="isObligatory" value="否" title="否">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 20px">
            <label class="layui-form-label">课程类别</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="courseTypeId" lay-filter="aihao" lay-verify="required">
                    <option value="">请选择课程类别</option>
                    <c:forEach items="${course}" var="list">
                        <option value="${list.courseTypeId}">${list.courseTypeName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item layui-form-text" style="margin-left: 20px">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入说明" name="remark" style="width: 200px" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 45px">
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
                    url: "${pageContext.request.contextPath}/MY/addcourse",
                    type: "post",
                    async:true,
                    dataType: "json",
                    data:{
                        courseName:data.courseName,
                        isObligatory:data.isObligatory,
                        courseTypeId:data.courseTypeId,
                        remark:data.remark,
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
