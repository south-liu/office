<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>招生信息</title>
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
<%--<div class="layui-card-body ">--%>
<%--    <form class="layui-form layui-col-space5" action="" onsubmit="return false;">--%>
<%--        <div class="layui-inline layui-show-xs-block">--%>
<%--            <input type="text" id="studName" name="studName"  placeholder="学生姓名" autocomplete="off" class="layui-input">--%>
<%--        </div>--%>
<%--        <div class="layui-inline layui-show-xs-block">--%>
<%--            <input type="text" id="tell" name="tell"  placeholder="手机号码" autocomplete="off" class="layui-input">--%>
<%--        </div>--%>
<%--        <div class="layui-inline layui-show-xs-block">--%>
<%--            <div class="layui-inline layui-show-xs-block">--%>
<%--                <input type="text" id="empName" name="tell"  placeholder="招生老师" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-inline layui-show-xs-block">--%>
<%--            <div class="layui-input-inline">--%>
<%--                <select name="majorId">--%>
<%--                    <c:forEach items="${major}" var="mar">--%>
<%--                        <option value="${mar.majorId}">${mar.majorName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-inline layui-show-xs-block">--%>
<%--            <button class="layui-btn" id="seacch" ><i class="layui-icon">&#xe615;</i></button>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addStu">添加学生</button>
<%--        <button class="layui-btn layui-btn-sm" lay-event="delStus">删除选中</button>--%>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updHuor">预定报名费</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updyuding">预定报名审核</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updClass">试学</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="updshixue">未试学</button>
        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm" lay-event="leaHuor">提成金额</button>
    </div>
    <input type="hidden" name="studType" value="${studType}">
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="ruxue">设置入学</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/erlment/erlmentlist',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '班级数据表',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field:'enrollmentId', title:'ID', hide:true, sort: true,align: 'center',width:60},
                {field:'studName', title:'学生姓名 ',align: 'center',width:100},
                // {field:'', title:'学号',align: 'center',width:60},
                {field:'sex', title:'性别',sort: true,align: 'center',width:80},
                {field:'card', title:'身份证号', align: 'center',width:200},
                {field:'tell', title:'学生电话',align: 'center',width:150},
                {field:'school', title:'学校',align: 'center',width:150},
                {field:'classes', title:'班级',align: 'center',width:100},
                {field:'score', title:'学生成绩',align: 'center',width:100},
                {field:'amount', title:'预定报名费',align: 'center',width:100},
                {field:'enrollMoney', title:'提成金额',align: 'center',width:100},
                {field:'status', title:'学生状态',align: 'center',width:100,templet: function(res){
                        if (res.status == 1) {
                            return '意向学生';
                        } else if (res.status == 2){
                            return '预定报名学生';
                        } else if (res.status == 3){
                            return '试学学生';
                        } else if (res.status == 4){
                            return '在读学生';
                        } else if (res.status == 5){
                            return '已毕业学生';
                        }else if (res.status == 6){
                            return '未试学';
                        }
                    }},
                {field:'signDate', title:'录入时间',align: 'center',width:100},
                {field:'startTime', title:'入学时间',align: 'center',width:100},
                {field:'negativeName', title:'招生老师',align: 'center',width:100},
                {field:'luruId', title:'录入人',align: 'center',width:100},
                {field:'reviewStatus', title:'报名费状态',align: 'center',width:100,templet: function(res){
                        if (res.reviewStatus == 1) {
                            return '未审核';
                        } else if (res.reviewStatus == 2){
                            return '已审核';
                        }
                    }},
                {field:'reviewer', title:'审核人',align: 'center',width:100},
                {field:'reviewerTime', title:'审核时间',align: 'center',width:100},
                {field:'classTypeName', title:'班级类别',align: 'center',width:100},
                {field:'majorName', title:'学生专业',align: 'center',width:100},
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center',width:250},
            ]],
            page: true
        });

        //搜索
        $('#seacch').click(function () {
            var studName = $('#studName').val();
            var tell = $('#tell').val().trim();
            var clazz = $('#clazz').val();
            var huor = $('#huor').val();
            table.reload('myTab',{
                url:'${pageContext.request.contextPath}/student/pageListWhere',
                where:{
                    studName:studName,
                    tell:tell,
                    clazz:clazz,
                    huor:huor
                }
            });
        });

        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addStu':
                    /*layer.open({
                        title:'添加学生',
                        type:2,
                        content:'${pageContext.request.contextPath}/student/toAdd',
                        area: ['720px', '500px'],
                        resize:false,
                        // maxmin:true
                    });*/
                    layer.load();
                    location.href='${pageContext.request.contextPath}/erlment/toadderlm';
                    break;
                case 'delStus':
                    var ids = [];
                    var stu = checkStatus.data;
                    for (var i = 0;i < length;i++) {
                        ids.push(stu[i].enrollmentId);
                    }
                    var idstr = ids.join(',');
                    console.log(idstr);
                    if (length != 0) {
                        layer.confirm('确认删除吗', function(index){
                            var lod = layer.load();
                            $.ajax({
                                type: "POST",
                                dataType: "json",
                                url: "${pageContext.request.contextPath}/erlment/delStu" ,
                                data: {enrollmentId:idstr},
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
                case 'updClass':
                    if (length == 1) {
                        if (checkStatus.data[0].status==4) {
                            layer.msg('该学生已经是在读学生',{
                                icon:0,
                                time:1000
                            });
                        } if (checkStatus.data[0].status==3){
                            layer.msg('该学生已是试学状态',{
                                icon:0,
                                time:1000
                            });
                        }else {
                            layer.confirm('确认让此学生试学吗', function(index){
                                var lod = layer.load();
                                $.ajax({
                                    type: "POST",
                                    dataType: "json",
                                    url: "${pageContext.request.contextPath}/erlment/stushixue?enrollmentId="+checkStatus.data[0].enrollmentId,
                                    success: function (result) {
                                        layer.close(lod);
                                        layer.msg('设置成功',{
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
                        }
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'updshixue':
                    if (length == 1) {
                        if (checkStatus.data[0].status==4 || checkStatus.data[0].status==3 || checkStatus.data[0].status==5) {
                            layer.msg('该学生已在读或已经试学',{
                                icon:0,
                                time:1000
                            });
                        } if (checkStatus.data[0].status==6){
                            layer.msg('该学生已是未试学状态',{
                                icon:0,
                                time:1000
                            });
                        } else {
                            layer.confirm('确认让把该学生设置为未试学吗', function(index){
                                var lod = layer.load();
                                $.ajax({
                                    type: "POST",
                                    dataType: "json",
                                    url: "${pageContext.request.contextPath}/erlment/stuwshixue?enrollmentId="+checkStatus.data[0].enrollmentId,
                                    success: function (result) {
                                        layer.close(lod);
                                        layer.msg('设置成功',{
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
                        }
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'leaHuor':
                    //提成金额
                    if (length == 1) {
                        var index = layer.open({
                            title:'提成金额',
                            type:1,
                            content:'<div style="padding: 30px 63px 30px 34px;" class="layui-inline" >\n' +
                                '        <label class="layui-form-label">设置提成金额</label>\n' +
                                '        <div class="layui-input-inline">\n' +
                                '            <input type="text" id="enrollMoney" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                                '        </div><div style="height: 30px"></div>' +
                                '    </div>',
                            btn: ['确定', '取消'],
                            yes: function(index, layero){
                                var enrollMoney = $('#enrollMoney').val().trim();
                                if (enrollMoney == '') {
                                    layer.msg('请输入',{
                                        icon: 2,
                                        time: 1000
                                    });
                                } else {
                                    var lindex = layer.load();
                                    $.ajax({
                                        type:"post",
                                        url:"${pageContext.request.contextPath}/erlment/addenrollMoney?enrollmentId="+checkStatus.data[0].enrollmentId,
                                        async:true,
                                        dataType:"text",
                                        data:{enrollMoney:enrollMoney},
                                        success:function(data){
                                            layer.close(lindex);
                                            layer.msg('添加成功!', {
                                                icon: 1,
                                                time: 1000
                                            },function () {
                                                layer.close(index);
                                                // location.reload();
                                                window.location.reload();
                                            });
                                        },
                                        error:function () {
                                            layer.close(lindex);
                                            layer.msg("请输入数字");
                                        }
                                    });
                                }
                            },
                            btnAlign: 'c',
                        });
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'updHuor'://预定报名费
                    if (length == 1) {
                        var index = layer.open({
                            title:'预定报名费',
                            type:1,
                            content:'<div style="padding: 30px 63px 30px 34px;" class="layui-inline" >\n' +
                                '        <label class="layui-form-label">预定报名费</label>\n' +
                                '        <div class="layui-input-inline">\n' +
                                '            <input type="text" id="amount" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                                '        </div><div style="height: 30px"></div>' +
                                '    </div>',
                            btn: ['确定', '取消'],
                            yes: function(index, layero){
                                var amount = $('#amount').val().trim();
                                if (amount == '') {
                                    layer.msg('请输入',{
                                        icon: 2,
                                        time: 1000
                                    });
                                } else {
                                    var lindex = layer.load();
                                    $.ajax({
                                        type:"post",
                                        url:"${pageContext.request.contextPath}/erlment/addamount?enrollmentId="+checkStatus.data[0].enrollmentId,
                                        async:true,
                                        dataType:"text",
                                        data:{amount:amount},
                                        success:function(data){
                                            layer.close(lindex);
                                            layer.msg('添加成功!', {
                                                icon: 1,
                                                time: 1000
                                            },function () {
                                                layer.close(index);
                                                // location.reload();
                                                window.location.reload();
                                            });
                                        },
                                        error:function () {
                                            layer.close(lindex);
                                            layer.msg("请输入数字");
                                        }
                                    });
                                }
                            },
                            btnAlign: 'c',
                        });
                    } else {
                        layer.msg('请选择一个学生',{
                            icon:0
                        });
                    }
                    break;
                case 'updyuding'://预定报名审核
                    if (length == 1) {
                        var index = layer.open({
                            title:'预定报名审核',
                            type:1,
                            content:'<div style="padding: 30px 63px 30px 34px;" class="layui-inline" >\n' +
                                '        <label class="layui-form-label">请输入名称</label>\n' +
                                '        <div class="layui-input-inline">\n' +
                                '            <input type="text" id="reviewer" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                                '        </div><div style="height: 30px"></div>' +
                                '    </div>',
                            btn: ['确定', '取消'],
                            yes: function(index, layero){
                                var reviewer = $('#reviewer').val().trim();
                                if (reviewer == '') {
                                    layer.msg('请输入',{
                                        icon: 2,
                                        time: 1000
                                    });
                                } else {
                                    var lindex = layer.load();
                                    $.ajax({
                                        type:"post",
                                        url:"${pageContext.request.contextPath}/erlment/addreviewer?enrollmentId="+checkStatus.data[0].enrollmentId,
                                        async:true,
                                        dataType:"text",
                                        data:{reviewer:reviewer},
                                        success:function(data){
                                            layer.close(lindex);
                                            layer.msg('审核成功!', {
                                                icon: 1,
                                                time: 1000
                                            },function () {
                                                layer.close(index);
                                                // location.reload();
                                                window.location.reload();
                                            });
                                        },
                                        error:function () {
                                            layer.close(lindex);
                                            layer.msg("请输入正确的格式");
                                        }
                                    });
                                }
                            },
                            btnAlign: 'c',
                        });
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
                        url: "${pageContext.request.contextPath}/erlment/deletelist?enrollmentId="+data.enrollmentId,
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
                location.href='${pageContext.request.contextPath}/erlment/toupderlm?enrollmentId='+data.enrollmentId;
                <%--switch(obj.event){--%>
                <%--    case 'edit':--%>
                <%--        var index = layer.open({--%>
                <%--            title:'修改',--%>
                <%--            type:2,--%>
                <%--            content:'${pageContext.request.contextPath}/erlment/toupderlm?enrollmentId='+data.enrollmentId,--%>
                <%--            btnAlign: 'c',--%>
                <%--            area: ['460px', '400px'],--%>
                <%--            resize:false,--%>
                <%--        });--%>
                <%--        break;--%>
                <%--};--%>
            }else if(obj.event === 'ruxue'){
                if (data.status==4 && data.status==5){
                    layer.msg('该学生已经在读或毕业',{
                        icon:0
                    });
                } else {layer.confirm('确认让此学生入学吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/erlment/addstulist",
                        data:{
                            enrollmentId:data.enrollmentId,
                            stuName:data.studName,
                            sex:data.sex,
                            cardId:data.card,
                            phone:data.tell,
                            enterTime:data.startTime,
                            introdureTech:data.empName,
                            middleSchool:data.school,
                            proLevel:data.studType,
                            professional:data.majorId,
                        },
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('已经设置为入学',{
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
                });}
            }
        });
    });
</script>
</body>
</html>
