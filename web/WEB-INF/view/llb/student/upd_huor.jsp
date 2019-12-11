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
    <title>调整宿舍</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 20px">

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>选择宿舍</legend>
</fieldset>
<div id="tree"></div>

<script type="text/javascript">

    layui.use('tree',function () {
        var tree = layui.tree;
        //渲染
        tree.render({
            elem:'#tree',
            data:${huors},
            //showCheckbox:true,
            onlyIconControl:true,
            click:function(obj){
                if (obj.data.children == undefined) {
                    console.log(obj.data);
                    console.log(${student.studId});
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/student/updHuor" ,
                        data: {stuId:${student.studId},huorId:obj.data.id},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('调整成功',{
                                time:1000
                            },function () {
                                window.parent.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误');
                        }
                    });
                } else {
                    layer.msg('请选择宿舍');
                }
            }
        });
    });

</script>
</body>
</html>
