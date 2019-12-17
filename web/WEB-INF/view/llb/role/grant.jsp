<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户授权</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 30px">

<div id="tree"></div>
<input type="hidden" id="characterId" value="${characterId}">

<button style="margin: 25px 0 0 95px;" id="sub" class="layui-btn">保存</button>

<script type="text/javascript">

    layui.use('tree',function () {
        var tree = layui.tree;

        //渲染
        tree.render({
            elem:'#tree',
            id:'mytree',
            data:${allEmp},
            showCheckbox:true,
            onlyIconControl:true
        });

        tree.setChecked('mytree', ${thisEmps}); //批量勾选 id 为 2、3 的节点

        $('#sub').click(function () {
            var empIds = [];
            //获得选中的节点
            var checkData = tree.getChecked('mytree');
            for (var i = 0;i<checkData.length;i++){
                for (var j = 0;j<checkData[i].children.length;j++){
                    empIds.push(checkData[i].children[j].id);
                }
            };
            if (empIds.length == 0) {
                empIds.push(0);
            }
            var lod = layer.load();
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/menu/updGrant',
                async:true,
                dataType:'json',
                data:{
                    characterId:$('#characterId').val(),
                    empIds:empIds
                },
                success:function (data) {
                    layer.close(lod);
                    layer.msg('保存成功',{
                        icon:1,
                        time:2000
                    },function(){
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误，请稍后再试！',{
                        icon:2
                    });
                }
            });
        });

    });
</script>
</body>
</html>
