<%--
  Created by IntelliJ IDEA.
  User: 南城
  Date: 2019/11/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>未打卡统计图表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
    <script type="text/javascript"src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
    <%--顶部按钮--%>
    <div class="layui-inline" style="background: #F2F2F2; width: 100%; height: 50px">
        <button class="layui-btn layui-btn-sm" id="fan" onclick="fan()" style="height: 30px; margin: 10px 0 0 15px"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
        <div class="layui-inline" style="margin-left: 110px">
            <label class="layui-form-label" style="width: 300px; font-size: 20px; margin-left: 300px">
                根据某一时间段来浏览趋势！
            </label>
        </div>
    </div>

    <div id="main" style="width: 600px; height: 400px; margin-left: 400px; margin-top: 20px"></div>
    <script type="text/javascript">
        var myChart1 = echarts.init(document.getElementById("main"));
        $.post("${pageContext.request.contextPath}/RF/checkwork_list_rf", {}, function (p) {
            console.log(p)
            myChart1.setOption({
                title: {  //标题
                    text: '未打卡月统计'
                },
                legend: {},  //图例
                tooltip: {},
                dataset:{
                    source:p.zong
                },
                xAxis: {  //X轴
                    type:'category'  //类目轴
                },
                yAxis: {},  //Y轴，折线、条形统计图Y轴无值也需要写此属性。
                series:p.xilie  //系列坐标
            })
        }, "json");

    </script>

    <div id="min" style="width: 600px; height: 400px; margin-left: 400px; margin-top: 20px"></div>
    <script type="text/javascript">
        var myChart2 = echarts.init(document.getElementById("min"));
        $.post("${pageContext.request.contextPath}/RF/checkwork_list_rf_zong", {}, function (p) {
            console.log(p)
            myChart2.setOption({
                title: {  //标题
                    text: '未打卡总概览'
                },
                legend: {},  //图例
                tooltip: {},
                dataset:{
                    source:p.zong
                },
                xAxis: {  //X轴
                    type:'category'  //类目轴
                },
                yAxis: {},  //Y轴，折线、条形统计图Y轴无值也需要写此属性。
                series:p.xilie  //系列坐标
            })
        }, "json");

    </script>

    <script>
        //头部上传按钮点击事件
        function fan(){
            location.href='${pageContext.request.contextPath}/RF/tocheckwork_list';
        }
    </script>

</body>
</html>