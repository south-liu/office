<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/7
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改专业</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
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
<form class="layui-form" action="" lay-filter="example">
    <div class="layui-form-item">
        <label class="layui-form-label">排序编号：</label>
        <div class="layui-input-inline">
            <input type="text" name="orderId" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">星期：</label>
        <div class="layui-input-block">
            <select name="week" lay-filter="parentId">
                <option value="星期一">星期一</option>
                <option value="星期二">星期二</option>
                <option value="星期三">星期三</option>
                <option value="星期四">星期四</option>
                <option value="星期五">星期五</option>
                <option value="星期天">星期天</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排班名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="weekArrangeName" lay-verify="required"  autocomplete="off" class="layui-input"></input>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">员工名称：</label>
        <div class="layui-input-block">
            <select name="empId" lay-filter="parentId">
                <c:forEach items="${empVO}" var="emp">
                    <option value="${emp.empId}">${emp.empName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">值班要求：</label>
        <div class="layui-input-inline">
            <input type="text" name="ranges" lay-verify="required" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">总值班：</label>
        <div class="layui-input-block">
                <select name="duty" lay-filter="parentId">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明：</label>
        <div class="layui-input-block">
            <textarea name="remark" lay-verify="required" class="layui-textarea"></textarea>
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
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#data' //指定元素
        });
        laydate.render({
            elem:'#datas'
        });
    });
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
                type:"post",
                url:"${pageContext.request.contextPath}/weekang/updlist",
                async:true,
                dataType:"text",
                data:{
                    weekArrangeId:${weekArrangeVO.weekArrangeId},
                    empId:fd.empId,
                    weekArrangeName:fd.weekArrangeName,
                    ranges:fd.ranges,
                    week:fd.week,
                    orderId:fd.orderId,
                    duty:fd.duty,
                    remark:fd.remark
                },
                    success: function (data) {
                        layer.close(lod);
                        layer.msg('修改成功',{
                            time:1000
                        },function () {
                            //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            //parent.layer.close(index); //再执行关闭
                            window.parent.location.reload();
                        });
                    },
                error:function () {
                    layer.close(lindex);
                    layer.msg("服务器错误", {
                        icon: 1,
                        time: 1000
                    });
                }
            });
            return false;
    });

        //表单赋值
        form.val('example', {
            "weekArrangeName":"${weekArrangeVO.weekArrangeName}"
            ,"ranges": "${weekArrangeVO.ranges}"
            ,"week": "${weekArrangeVO.week}"
            ,"empId": "${weekArrangeVO.empId}"
            ,"orderId": "${weekArrangeVO.orderId}"
            ,"duty": "${weekArrangeVO.duty}"
            ,"kaiguan": true //开关状态
            ,"remark": "${weekArrangeVO.remark}"
        });

        <%--//表单赋值--%>
        <%--    form.val('example', {--%>
        <%--        "majorName":"${MajorVO.majorName}"--%>
        <%--        ,"collegeDeptId": ${MajorVO.collegeDeptId}--%>
        <%--        ,"remark": "${MajorVO.remark}"--%>
        <%--    });--%>
    });
</script>
</body>
</html>

