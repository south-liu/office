<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生资料</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-btn-llb{
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }
    .layui-btn-llbb{
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body>
<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addStu">添加学生</button>
        <button class="layui-btn layui-btn-sm" lay-event="delStus">删除选中</button>

        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updHuor">调整宿舍</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="leaHuor">搬离宿舍</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updClass">调整班级</button>

        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="eduInfo">教育经历</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="famInfo">家庭信息</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="zxInfo">在校情况</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="dabian">答辩成绩</a>
        <a class="layui-btn layui-btn-radius layui-btn-normal layui-btn-xs" lay-event="test">考试成绩</a>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="tuixue">退学</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="repass">重置密码</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/student/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '班级数据表',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field:'studId', title:'ID', hide:true, sort: true,align: 'center',width:60},
                {field:'stuName', title:'学生姓名 ',align: 'center',width:100},
                // {field:'', title:'学号',align: 'center',width:60},
                {field:'sex', title:'性别',sort: true,align: 'center',width:80},
                {field:'cardId', title:'身份证号', align: 'center',width:200},
                {field:'phone', title:'学生电话',align: 'center',width:150},
                {field:'className', title:'班级',align: 'center',width:100,templet: function(res){
                        if (res.className == ''||res.className==null) {
                            return '未分配'
                        } else {
                            return res.className;
                        }
                    }},
                {field:'huorName', title:'宿舍房号',align: 'center',width:100,templet: function(res){
                        if (res.huorName == ''||res.huorName==null) {
                            return '未分配'
                        } else {
                            return res.huorName;
                        }
                    }},
                {field:'collar', title:'是否领用电脑',align: 'center',width:100},
                {field:'grants', title:'是否享受助学金',align: 'center',width:100},
                {field:'computer', title:'是否送电脑',align: 'center',width:100},
                {field:'parents', title:'家长姓名',align: 'center',width:100},
                {field:'parentsPhone', title:'家长电话',align: 'center',width:100},
                {field:'', title:'已缴金额',align: 'center',width:100},
                {field:'qkMoney', title:'欠款金额',align: 'center',width:100},
                {field:'stat', title:'学生状态',align: 'center',width:100,templet: function(res){
                        if (res.stat == 0) {
                            return '退学'
                        } else {
                            return '正常';
                        }
                    }},
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center',width:250},
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addStu':
                    layer.open({
                        title:'添加学生',
                        type:2,
                        content:'${pageContext.request.contextPath}/student/toAdd',
                        area: ['720px', '500px'],
                        resize:false,
                        // maxmin:true
                    });
                    break;
                case 'delStus':
                    var ids = [];
                    var stu = checkStatus.data;
                    for (var i = 0;i < length;i++) {
                        ids.push(stu[i].studId);
                    }
                    var idstr = ids.join(',');
                    console.log(idstr);
                    if (length != 0) {
                        layer.confirm('确认删除吗', function(index){
                            var lod = layer.load();
                            $.ajax({
                                type: "POST",
                                dataType: "json",
                                url: "${pageContext.request.contextPath}/student/delStu" ,
                                data: {idstr:idstr},
                                success: function (result) {
                                    layer.close(lod);
                                    layer.msg('删除成功',{
                                        icon:1,
                                        time:1000
                                    },function () {
                                        window.location.reload();
                                    });
                                },
                                error : function() {
                                    layer.close(lod);
                                    layer.msg('服务器错误',{
                                        icon:2
                                    });
                                }
                            });
                        });
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;

                case 'updHuor':
                    if (length == 1) {
                        if (checkStatus.data[0].stat ==0) {
                            layer.msg('该学生已退学',{
                                icon:0,
                                time:1000
                            });
                        } else {
                            layer.open({
                                title:'调整宿舍',
                                type:2,
                                content:'${pageContext.request.contextPath}/student/toUpdHuor?studId='+checkStatus.data[0].studId,
                                area: ['500px', '400px']
                            });
                        }
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'leaHuor':
                    if (length == 1) {
                        if (checkStatus.data[0].stat ==0) {
                            layer.msg('该学生已退学',{
                                icon:0,
                                time:1000
                            });
                        } else {
                            var hour = checkStatus.data[0].huorName;
                            console.log(hour);
                            if (hour==undefined) {
                                layer.msg('当前学生未分配宿舍',{
                                    icon:0
                                });
                            } else {
                                layer.confirm('确认让该学生搬离 '+hour+' 宿舍吗？',function () {
                                    var lod = layer.load();
                                    $.ajax({
                                        type: "POST",
                                        dataType: "json",
                                        url: "${pageContext.request.contextPath}/student/leaHuor" ,
                                        data: {stuId:checkStatus.data[0].studId},
                                        success: function (result) {
                                            layer.close(lod);
                                            layer.msg('搬离宿舍成功',{
                                                icon:1,
                                                time:1000
                                            },function () {
                                                window.location.reload();
                                            });
                                        },
                                        error : function() {
                                            layer.close(lod);
                                            layer.msg('服务器错误',{
                                                icon:2
                                            });
                                        }
                                    });
                                })
                            }
                        }
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'updClass':
                    if (length == 1) {
                        if (checkStatus.data[0].stat ==0) {
                            layer.msg('该学生已退学',{
                                icon:0,
                                time:1000
                            });
                        } else {
                            layer.open({
                                title:'调整班级',
                                type:2,
                                content:'${pageContext.request.contextPath}/student/toUpdClass?studId='+checkStatus.data[0].studId,
                                area: ['500px', '400px']
                            });
                        }
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;

                case 'eduInfo':
                    if (length == 1) {
                        <%--window.open('${pageContext.request.contextPath}/stujef/toedu?stuId='+checkStatus.data[0].studId,'target');--%>
                        window.location.href='${pageContext.request.contextPath}/stujef/toedu?stuId='+checkStatus.data[0].studId;
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'famInfo':
                    if (length == 1) {
                        <%--window.open('${pageContext.request.contextPath}/stujef/tofam?stuId='+checkStatus.data[0].studId,'target');--%>
                        window.location.href='${pageContext.request.contextPath}/stujef/tofam?stuId='+checkStatus.data[0].studId;
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'zxInfo':
                    if (length == 1) {
                        <%--window.open('${pageContext.request.contextPath}/stujef/tozx?stuId='+checkStatus.data[0].studId,'target');--%>
                        window.location.href='${pageContext.request.contextPath}/stujef/tozx?stuId='+checkStatus.data[0].studId;
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'dabian':
                    if (length == 1) {
                        <%--window.open('${pageContext.request.contextPath}/CJEF/tofam?empId='+checkStatus.data[0].empId,'target');--%>
                        window.location.href='${pageContext.request.contextPath}/CJEF/tofam?empId='+checkStatus.data[0].empId;
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'test':
                    if (length == 1) {
                        <%--window.open('${pageContext.request.contextPath}/MY/tostudentscore_list?studId='+checkStatus.data[0].studId,'target');--%>
                        window.location.href='${pageContext.request.contextPath}/MY/tostudentscore_list?studId='+checkStatus.data[0].studId;
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除此学生吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/student/delStu" ,
                        data: {idstr:data.studId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                               window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            } else if(obj.event === 'edit'){
                layer.open({
                    title:'编辑信息',
                    type:2,
                    content:'${pageContext.request.contextPath}/student/toEdit?studId='+data.studId,
                    area: ['720px', '500px'],
                    resize:false,
                    maxmin:true
                });
            } else if (obj.event === 'tuixue') {
                if (data.stat == 0) {
                    layer.msg('该学生已退学',{
                        icon:0,
                        time:1000
                    });
                } else {
                    layer.confirm('确实将该学生退学吗',function () {
                        var lod = layer.load();
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${pageContext.request.contextPath}/student/updStatus" ,
                            data: {stuId:data.studId,stat:0},
                            success: function (result) {
                                layer.close(lod);
                                layer.msg('退学成功',{
                                    icon:1,
                                    time:1000
                                },function () {
                                    window.location.reload();
                                });
                            },
                            error : function() {
                                layer.close(lod);
                                layer.msg('服务器错误',{
                                    icon:2
                                });
                            }
                        });
                    })
                }
            } else if (obj.event === 'repass') {
                layer.confirm('确实重置密码为123456吗',function () {
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/student/repass" ,
                        data: {stuId:data.studId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('重置密码成功',{
                                icon:1
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                })
            }
        });
    });
</script>
</body>
</html>
