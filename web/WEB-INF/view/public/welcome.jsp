<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/9
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<div class="layui-carousel" id="test1">
    <div carousel-item>
        <div>条目1</div>
        <div>条目2</div>
        <div>条目3</div>
        <div>条目4</div>
        <div>条目5</div>
    </div>
</div>
<script type="text/javascript">
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '100%', //设置容器宽度
            arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });
    });
</script>
</body>
</html>
