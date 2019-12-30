<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/14
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请请假</title>
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
<body style="padding: 30px">
<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">请假开始时间：</label>
        <div class="layui-input-inline">
            <input type="text" name="startTime" id="startTime" lay-verify="required" placeholder="开始请假时间"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">请假结束时间：</label>
        <div class="layui-input-inline">
            <input type="text" name="endTime" id="endTime" lay-verify="required" placeholder="请假结束时间"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">请假时长：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <%--<input type="text" name="holidayDay" id="holidayDay" disabled placeholder="请选择天数" lay-verify="required|number" autocomplete="off" class="layui-input" value="0">--%>
            <input type="text" name="holidayDay" id="holidayDay" disabled lay-verify="required|number"
                   autocomplete="off" class="layui-input" value="0">
        </div>
        <div class="layui-form-mid">天</div>
        <div class="layui-input-inline" style="width: 150px;">

            <input type="text" name="holidayHour" id="holidayHour" disabled lay-verify="required|number"
                   autocomplete="off" class="layui-input" value="0">
        </div>
        <div class="layui-form-mid">小时</div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">原因说明：</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入请假原因" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="sub" lay-filter="sub">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<script>
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;

        function changeTime(startTimeStr, endTimeStr) {
            // 判断开始时间和结束时间的长度是否大于0，否则提示
            if (startTimeStr.length > 0 && endTimeStr.length > 0) {
                // 判断结束时间要大于开始时间，否则提示
                if (endTimeStr <= startTimeStr) {
                    layer.msg('开始时间不能大于或等于结束时间！', {icon: 3, time: 1500});
                    return;
                }
                var startTime = new Date(startTimeStr).getTime();// getTime获取开始时间的毫秒值
                var endTime = new Date(endTimeStr).getTime();// getTime获取结束时间的毫秒值

                var diffTime = (endTime - startTime);// 计算开始时间与结束时间的时间差
                var days = diffTime / 3600 / 1000 / 24;// 天
                var hours = diffTime / 3600 / 1000;// 小时

                var day = parseInt(hours / 24);// 讲计算出的天数转换为int值，例如：1.2天转换为1天
                var hour = (hours % 24);
//赋值给时间选择器
                $('#holidayDay').val(parseInt(hours / 24));
                $('#holidayHour').val((hours % 24));
            } else {
                layer.msg('请填写开始时间与结束时间！', {icon: 3, time: 1500});
                return;
            }
        }

        //日期
        laydate.render({
            elem: '#startTime',
            min: 0,
            type: 'datetime',
            trigger: 'click',
            format: 'yyyy-MM-dd HH:mm:ss',
            done: function (startTimeStr, date, endDate) {
                var endTimeStr = $('#endTime').val();
                changeTime(startTimeStr, endTimeStr);
            }
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime',
            min: 0,
            trigger: 'click',
            format: 'yyyy-MM-dd HH:mm:ss',
            done: function (endTimeStr, date, endDate) {
                var startTimeStr = $('#startTime').val();
                changeTime(startTimeStr, endTimeStr);
            }
        });

        //监听提交
        form.on('submit(sub)', function (data) {
                console.log("22" + data.field);

                var fd = data.field;
                var holidayHour = $('#holidayHour').val().trim();
                console.log("holidayHour:" + holidayHour)
                console.log("holidayHour2:" + fd.holidayHour)
//            console.log(fd.holidayDay1 + ' ' + fd.holidayDay2);
//            return false;
//            var noCardTime2 = $('#noCardTime2').val().trim();
//            console.log("3" + noCardTime2)
                //部门数据
//            if (noCardTime2 == '') {
//                layer.msg('请输入未打卡时间段!', {
//                    icon: 2,
//                    time: 1000
//                });
//            } else {
                var lod = layer.load();
                $.ajax({
                    url: "${pageContext.request.contextPath}/hstudent/my_apply_add",
                    type: "post",
                    async: true,
                    dataType: "json",
                    data: {
                        startTime: fd.startTime,
                        endTime: fd.endTime,
                        holidayDay: fd.holidayDay,
                        holidayHour: fd.holidayHour,
                        remark: fd.remark
                    },
                    success: function (data) {
                        layer.close(lod);
                        layer.msg('添加成功', {
                            time: 1000
                        }, function () {
                            <%--location.href='${pageContext.request.contextPath}/hstudent/goto_hs_myapply_list'--%>
                            window.parent.location.reload();
                        });
                    },
                    error: function () {
                        layer.close(lod);
                        layer.msg('服务器错误');
                    }
                });
                return false;
            }
        );
    });
</script>
</body>
</html>
