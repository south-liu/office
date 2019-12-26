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
    <title>修改学生在校记录</title>
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
<form class="layui-form" action="" lay-filter="example">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">情况记录：</label>
        <div class="layui-input-block">
            <textarea name="happening" lay-verify="required" class="layui-textarea"></textarea>
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


            var re = /^[0-9]+.?[0-9]*$/;
            var nubmer =fd.happening;
            if (re.test(nubmer)) {
                layer.msg('请认真填写该学生的在校情况');
            }else {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/stujef/updzx",
                    async:true,
                    dataType:"text",
                    data:{
                        happening:fd.happening,
                        happenId:${studentHappeningVO.happenId},
                        stuId:${studentHappeningVO.stuId},
                        empId:${studentHappeningVO.empId},
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
                        layer.close(index);
                        layer.msg("服务器错误", {
                            icon: 1,
                            time: 1000
                        });
                    }
                });
            }
            return false;
    });

        //表单赋值
        form.val('example', {
            "happening":"${studentHappeningVO.happening}"
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

