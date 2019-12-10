<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 20px">

<div class="layui-btn-container">
    <button type="button" class="layui-btn layui-btn-sm" onclick="addDept();">添加部门</button>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>宏图软件部门</legend>
</fieldset>
<div id="tree"></div>

<script type="text/javascript">

    layui.use('tree',function () {
        var tree = layui.tree;

        //部门数据
        $.ajax({
            url: "${pageContext.request.contextPath}/dept/allDept",
            type: "get",
            async:true,
            dataType: "json",
            success: function (data) {
                //渲染
                tree.render({
                    elem:'#tree',
                    data:data,
                    //showCheckbox:true,
                    onlyIconControl:true,
                    edit:['del'],
                    click:function(obj){
                        console.log(obj.data);
                        layer.open({
                            title:'修改部门',
                            type:2,
                            content:['${pageContext.request.contextPath}/dept/toUpd?deptId='+obj.data.id,'no'],
                            area: ['480px', '500px'],
                            resize:false
                        });
                    },
                    operate:function (obj) {
                        var type = obj.type; //得到操作类型：add、edit、del
                        var data = obj.data; //得到当前节点的数据
                        var elem = obj.elem; //得到当前节点元素
                        //Ajax 操作
                        var id = data.id; //得到节点索引
                        if(type === 'update'){ //修改节点

                          //修改后的值
                            var updData = elem.find('.layui-tree-txt').html();
                            var lod = layer.load();
                            $.ajax({
                                url: "${pageContext.request.contextPath}/dept/updDept",
                                type: "post",
                                async:true,
                                dataType: "json",
                                data:{
                                    deptId:data.id,
                                    deptName:updData
                                },
                                success: function (data) {
                                    layer.close(lod);
                                    layer.msg('修改成功');
                                },
                                error:function () {
                                    layer.close(lod);
                                    layer.msg('服务器错误');
                                }
                            });

                        } else if(type === 'del'){ //删除节点
                            var lod = layer.load();
                            $.ajax({
                                url: "${pageContext.request.contextPath}/dept/delDept",
                                type: "post",
                                async:true,
                                dataType: "json",
                                data:{
                                    deptId:data.id
                                },
                                success: function (data) {
                                    layer.close(lod);
                                    layer.msg('删除成功');
                                },
                                error:function () {
                                    layer.close(lod);
                                    layer.msg('服务器错误');
                                }
                            });
                        };
                    }
                });
            },
            error:function () {
                layer.msg('服务器错误');
            }
        });
    });

    //添加部门
    function addDept() {
        layer.open({
            title:'添加部门',
            type:2,
            content:['${pageContext.request.contextPath}/dept/toAdd','no'],
            area: ['480px', '500px'],
            resize:false
        });
    };
</script>
</body>
</html>
