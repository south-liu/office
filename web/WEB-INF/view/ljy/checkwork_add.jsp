<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/11
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加未打卡说明</title>
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
<body style="padding: 30px">
<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">未打卡日期：</label>
        <div class="layui-input-inline">
            <input type="text" name="noCardTime1" id="noCardTime" lay-verify="required" placeholder="请输入"
                   autocomplete="off"
                   class="layui-input">
        </div>

        <div class="layui-input-inline">
            <select name="noCardTime2" id="noCardTime2" lay-filter="noCardTime">
                <option value="">请选择未打卡时间点</option>
                <option value="8:00">8:00</option>
                <option value="14:00">14:00</option>
                <option value="17:00">17:00</option>
                <option value="21:00">21:00</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">原因说明：</label>
        <div class="layui-input-block">
            <textarea name="why" placeholder="请输入内容" lay-verify="required" class="layui-textarea"></textarea>
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
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#noCardTime'
        });


        //监听提交
        form.on('submit(sub)', function (data) {
            console.log("22" + data.field);
            var fd = data.field;
            var noCardTime2 = $('#noCardTime2').val().trim();
            console.log("3" + noCardTime2)
            //部门数据
            if (noCardTime2 == '') {
                layer.msg('请输入未打卡时间段!', {
                    icon: 2,
                    time: 1000
                });
            } else {
                var lod = layer.load();
                $.ajax({
                    url: "${pageContext.request.contextPath}/checkwork/checkworkadd",
                    type: "post",
                    async: true,
                    dataType: "json",
                    data: {
                        noCardTime: fd.noCardTime1 + ' ' + fd.noCardTime2,
                        why: fd.why
                    },
                    success: function (data) {
                        layer.close(lod);
                        layer.msg('添加成功', {
                            time: 1000
                        }, function () {
                            window.parent.location.reload();
                        });
                    },
                    error: function () {
                        layer.close(lod);
                        layer.msg('服务器错误');
                    }
                });
            }
            return false;
        });
    });
</script>
</body>
</html>
