<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加周报</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
    <form class="layui-form" action="">

        <div class="layui-form-item" style="margin-left: 20px; margin-top: 15px">
            <label class="layui-form-label">请假类型</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="type" lay-filter="aihao" lay-verify="required">
                    <option value="">请选择请假类型</option>
                    <option value="事假">事假</option>
                    <option value="病假">病假</option>
                    <option value="4小时带薪">4小时带薪</option>
                    <option value="婚假">婚假</option>
                    <option value="产假">产假</option>
                    <option value="陪产假">陪产假</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 20px">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-inline">
                <input placeholder="yyyy-MM-dd HH:mm:ss" type="text" lay-verify="required" class="layui-input" style="width: 200px" name="startTime" id="start">
            </div>
        </div>

        <div class="layui-inline" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-inline">
                <input placeholder="yyyy-MM-dd HH:mm:ss" type="text" lay-verify="required" class="layui-input" style="width: 200px" name="endTime" id="end">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <div class="layui-inline">
                <label class="layui-form-label">请假时长</label>
                <div class="layui-input-inline" style="width: 65px;">
                    <input placeholder="0" type="text" name="day" id="day" style="width: 65px" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">天</div>
                <div class="layui-input-inline" style="width: 65px;">
                    <select name="hour" lay-verify="required" lay-search="">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="4">5</option>
                        <option value="4">6</option>
                        <option value="4">7</option>
                        <option value="4">8</option>
                        <option value="4">9</option>
                        <option value="4">10</option>
                        <option value="4">11</option>
                        <option value="4">12</option>
                        <option value="4">13</option>
                        <option value="4">14</option>
                        <option value="4">15</option>
                        <option value="4">16</option>
                        <option value="4">17</option>
                        <option value="4">18</option>
                        <option value="4">19</option>
                        <option value="4">20</option>
                        <option value="4">21</option>
                        <option value="4">22</option>
                        <option value="4">23</option>
                    </select>
                </div>
                <div class="layui-form-mid">小时</div>
            </div>
        </div>

        <input type="hidden" name="status" value="1"/>

        <div class="layui-form-item layui-form-text" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">请假事由</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入请假事由" name="remark" style="width: 200px" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>

        <input type="hidden" name="types" value="holiday"/>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn"  lay-submit="" lay-filter="demo1">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

    <script>
        function fanhui() {
            location.href="${pageContext.request.contextPath}/TP/toholiday_list";
        }

        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer
                ,laydate = layui.laydate;

            //日期时间选择器
            laydate.render({
                elem: '#start'
                ,type: 'datetime'
            });
            //日期时间选择器
            laydate.render({
                elem: '#end'
                ,type: 'datetime'
            });

            //监听提交
            form.on('submit(demo1)', function(obj){
                var data = obj.field;

                var dayStr = data.day + "天" + data.hour + "小时";

                var lindex = layer.load();

                if (data.startTime < data.endTime){
                    $.ajax({
                        url: "${pageContext.request.contextPath}/TP/addholiday",
                        type: "post",
                        async:true,
                        dataType: "json",
                        data:{
                            holidayDay:data.day,
                            dayStr:dayStr,
                            startTime:data.startTime,
                            endTime:data.endTime,
                            type:data.type,
                            remark:data.remark,
                        },
                        success: function (data) {
                            layer.close(lindex);
                            layer.msg('添加成功',{
                                time:1000
                            },function () {
                                window.parent.location.reload();  //添加成功之后关闭本页面且刷新原页面（直接重新访问原页面）
                            });
                        },
                        error:function () {
                            layer.close(lindex);
                            layer.msg('服务器错误');
                        }
                    });
                }else {
                    layer.close(lindex);  //拦截请求，让其不继续提交下去（无此拦截会进入转圈圈模式）
                    layer.msg('请正确选择时间：开始时间应小于结束时间！',{
                        time:1000
                    })  //layui自定义提示框
                }
                return false;
            });
        });
    </script>

</body>
</html>
