<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>值班管理</title>
    <jsp:include page="../../public/head.jsp"/>
</head>
<body>
<table class="layui-hide" id="myTable" lay-filter="fTable"></table>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem:'#myTable',
            <%--url:'${pageContext.request.contextPath}/stuholi/holi_ptcls?studentId=${stuId}'+'&sum='+${sum}+'&month=${month}',--%>
            url:'${pageContext.request.contextPath}/stuholi/holi_ptcls?studentId=${stuId}&sum=${sum}&month=${month}',
            cellMinWidth: 80,
            title:'请假详情',
            cols:[[
                {field:'holidayId', title:'ID', sort: true,align: 'center'},
                {field:'stuName', title:'学生姓名',align: 'center'},
                {field:'holidayDay', title:'假期天数',align: 'center'},
                {field:'holidayHour', title:'小时',align: 'center'},
                {field:'startTime', title:'开始时间',align: 'center'},
                {field:'endTime', title:'结束时间',align: 'center'},
                {field:'title', title:'标题',align: 'center'},
                {field:'status', title:'总值班',align: 'center',templet: function(res){
                        if (res.status ==0) {
                            return '审批中'
                        } else if (res.status ==1) {
                            return '审批通过';
                        }else if (res.status ==2) {
                            return '审批未通过';
                        }
                    }},
                {field:'remark', title:'内容',align: 'center'},
            ]],
            page:true
        });
    });
</script>
</body>
</html>