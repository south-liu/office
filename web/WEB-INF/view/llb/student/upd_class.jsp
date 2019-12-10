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
    <title>调整班级</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 20px">

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>选择班级</legend>
</fieldset>
<div id="tree"></div>

<script type="text/javascript">

    layui.use('tree',function () {
        var tree = layui.tree;
        //渲染
        tree.render({
            elem:'#tree',
            data:${stuClass},
            //showCheckbox:true,
            onlyIconControl:true,
            click:function(obj){
                console.log(obj.data);
            }
        });
    });

</script>
</body>
</html>
