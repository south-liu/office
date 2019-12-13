<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/5
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教育背景</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<table class="layui-table"
       lay-data="{height:550, url:'${pageContext.request.contextPath}/education/educationlist?empId='+${empId}, page:true, id:'myTable',toolbar:'#toolbarDemo'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'schoolName', sort: true,edit:'text'}">学生名称</th>
        <th lay-data="{field:'degree',  sort: true,edit:'text'}">学历</th>
        <th lay-data="{field:'startDate',  sort: true,edit:'text'}">入校时间</th>
        <th lay-data="{field:'endDate', sort: true,edit:'text'}">毕业时间</th>
        <th lay-data="{field:'remark',sort: true,edit:'text'}">说明</th>
        <th lay-data="{fixed: 'right',align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="fanhui">返回</button>
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData">添加记录</button>
    </div>
</script>

<script>
    layui.use(['table'], function () {
        var table = layui.table;
        table.on('tool(fTable)', function (obj) {
            var event = obj.event;
            if (event === 'detail') {
                layer.alert(JSON.stringify(obj.data));
            } else if (event === 'del') {
                layer.confirm('确认删除', function (index) {
                    console.log(index);
                    $.post('${pageContext.request.contextPath}/education/educationdelete', {eduId: obj.data.eduId}, function (data) {
                        console.log(data);
                        layer.msg('删除成功！');
                        obj.del();
                    }, 'json');
                    layer.close(index);
                });
            }
        });
        table.on('toolbar(fTable)', function (obj) {
            if (obj.event == 'addCheckData') {
                layer.open({
                    title: '添加教育背景',
                    type: 1,
                    content: '<div style="margin-top: 10px" class="layui-inline">\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '        <label class="layui-form-label">学校名称：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="schoolName" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '            </div>' +
                    '        </div>\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '        <label class="layui-form-label">学历：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="degree" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '            </div>' +
                    '        </div>\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '        <label class="layui-form-label">入校时间：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="date" id="startDate" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '            </div>' +
                    '        </div>\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '        <label class="layui-form-label">毕业时间：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="date" id="endDate" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                    '            </div>' +
                    '        </div>\n' +


                    '  <div style="margin: 20px 0px ">' +
                    '        <label class="layui-form-label">说明：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '          <textarea id="remark" lay-verify="required" autocomplete="off" class="layui-input" style="width: 300px;height: 100px"></textarea>\n' +
                    '            </div>' +
                    '        </div>\n' +

                    '    </div>',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
//                            选中输入框
                        var schoolName = $('#schoolName').val().trim();
                        var degree = $('#degree').val().trim();
                        var startDate = $('#startDate').val().trim();
                        var endDate = $('#endDate').val().trim();
                        var remark = $('#remark').val().trim();
//                            判断是否为空
                        if (schoolName == '') {
                            layer.msg('请输入类别名称!', {
                                icon: 2,
                                time: 1000
                            });
                        } else {
                            var lindex = layer.load();
                            $.ajax({
                                type: "post",
                                url: "${pageContext.request.contextPath}/education/educationadd",
                                async: true,
                                dataType: "text",
                                data: {
                                    empId:${empId},
                                    schoolName: schoolName,
                                    degree: degree,
                                    startDate: startDate,
                                    endDate: endDate,
                                    remark: remark
                                },
                                success: function (data) {
                                    layer.close(lindex);
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    }, function () {
                                        layer.close(index);
                                        // location.reload();
                                        table.reload('myTable', {
                                            url: '${pageContext.request.contextPath}/education/educationlist?empId='+${empId}
                                        });
                                    });
                                },
                                error: function () {
                                    layer.close(lindex);
                                    layer.msg("服务器错误");
                                }
                            });
                        }
                    },
                    btnAlign: 'c',
                    area: ['450px', '500px']
                });
            }  else if (event === 'fanhui') {
                history.go(-1);
            }
        });


        table.on('edit(fTable)', function(obj){
//            console.log(obj)
            var lindex = layer.load();
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段名
            layer.msg('[ID: '+ data.eduId +'] ' + field + '更改为：'+ value);
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/education/educationupdate",
                async:true,
                dataType:"text",
                data:{empId:${empId},eduId:data.eduId,schoolName:data.schoolName,degree:data.degree,startDate:data.startDate,endDate:data.endDate,remark:data.remark},
                success:function(data){
                    layer.close(lindex);
                    layer.msg('修改成功!', {
                        icon: 1,
                        time: 1000
                    },function () {
                        table.reload('myTable', {
                            url: '${pageContext.request.contextPath}/education/educationlist?empId='+${empId}
                        });
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
        });


    })
</script>
</body>
</html>
