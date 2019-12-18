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

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">考核指标</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="aduitModelId" lay-filter="aihao" lay-verify="required">
                    <option value="">请选择考核指标</option>
                    <c:forEach items="${aduitm}" var="list">
                        <option value="${list.aduitModelId}">${list.aduitName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 20px">
            <label class="layui-form-label">考核时间</label>
            <div class="layui-input-inline">
                <input type="text" name="auditDate" id="da" style="width: 200px" autocomplete="off" class="layui-input" placeholder="yyyy-MM-dd">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">员工姓名</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="empId" lay-filter="aihao" lay-verify="required">
                    <option value="">请选择员工姓名</option>
                    <c:forEach items="${emp}" var="list">
                        <option value="${list.empId}">${list.empName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">考核分数</label>
            <div class="layui-input-block">
                <input type="text" name="score" style="width: 200px" autocomplete="off" placeholder="请输入考核分数" class="layui-input">
            </div>
        </div>

        <input type="hidden" name="auditPerson" value="1001"/><%-- 录入人员--%>

        <input type="hidden" name="image" value="1"/><%-- 图片--%>

        <div class="layui-form-item layui-form-text" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">说明</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入说明" name="remark" style="width: 200px" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 35px;">
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
                ,laydate = layui.laydate;

            //日期时间选择器
            laydate.render({
                elem: '#da',
                type: 'datetime'
            });

            //监听提交
            form.on('submit(demo1)', function(obj){
                var data = obj.field;
                var lindex = layer.load();
                //部门数据
                $.ajax({
                    url: "${pageContext.request.contextPath}/MY/addaduitlog",
                    type: "post",
                    async:true,
                    dataType: "json",
                    data:{
                        aduitModelId:data.aduitModelId,
                        empId:data.empId,
                        score:data.score,
                        auditDate:data.auditDate,
                        auditPerson:data.auditPerson,
                        image:data.image,
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
