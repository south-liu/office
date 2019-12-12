<%--
  Created by IntelliJ IDEA.
  User: 南城
  Date: 2019/11/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>

<%--添加表单--%>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--顶部按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle" style="font-size: 15px; color: #FFF;"></i>添加成绩</button>
    </div>
</script>

<%--操作列表的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="upd">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/studentscorelist?stuId=${student.studId}',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '学生成绩表',
            cols: [[
                {field:'stuId',align:'center', title:'学生编号', width:120, fixed: 'left', unresize: true, sort: true},
                {field:'stuName',align:'center', title:'学生姓名', width:120},
                {field:'score',align:'center', title:'学生成绩', width:120, edit: 'text'},
                {field:'courseName',align:'center',title:'课程名字', width:200},
                {field:'testType',align:'center',title:'考试类别', width:200},
                {field:'termName',align:'center',title:'在读学期', width:200},
                {field:'scoreTime',align:'center',title:'考试时间', width:200},
                {field:'remark',align:'center',title:'备注', width:200, edit: 'text'},
                {fixed:'right',align:'center', title:'操作', toolbar: '#barDemo', width:120}
            ]],
            page: true
        });



        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加学生成绩',
                        type:2,
                        content:["${pageContext.request.contextPath}/MY/toaddstudentscore?stuId=" + ${param.studId}, "no"],  <%-- ${param.studId}获取URL框中段递过来的参数 --%>
                        area:['480px', "600px"],
                        resize:false  //不能鼠标拖动改变大小
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示按钮
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据或编辑按钮可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('您确定要删除该成绩吗？', function(index){
                    //发异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/MY/delstudentscore",
                        async:true,
                        dataType:"text",
                        data:{scoreId:data.scoreId},
                        success:function(data){
                            layer.close(lindex);
                            obj.del();
                            layer.close(index);
                            layer.msg('已删除!', {
                                icon: 1,
                                time: 1000
                            });
                        },
                        error:function () {
                            layer.close(lindex);
                            layer.msg("服务器错误");
                        }
                    });
                });
            }else if (obj.event == 'upd'){
                var index = layer.open({
                    title:'修改成绩',
                    type:2,
                    content:["${pageContext.request.contextPath}/MY/toupdstudentscoure?scoreId="+data.scoreId, "no"],
                    area:['480px', "600px"],
                    resize:false  //不能鼠标拖动改变大小
                });
            }
        });

        //监听单元格编辑(修改)
        table.on('edit(test)', function(obj){
            var lindex = layer.load();
            var data = obj.data //得得到所在行的的对应列的值
            //,value = obj.value //得到修改后的值
            //,field = obj.field; //得到字段
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/MY/updstudentscore2",
                async:true,
                dataType:"text",
                data:{
                    scoreId:data.scoreId,
                    stuId:data.stuId,
                    score:data.score,
                    rescore:data.rescore,
                    courseId:data.courseId,
                    testType:data.testType,
                    termId:data.termId,
                    scoreTime:data.scoreTime,
                    empId:data.empId,
                    remark:data.remark
                },
                success:function(data){

                    layer.close(lindex);
                    layer.msg('修改成功');
                },
                error:function () {
                    layer.close(lindex);
                    layer.msg("服务器错误");
                }
            });
        });
    });
</script>
</body>
</html>