<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/3
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>office</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">office</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" id="_menuBox">

                <li class="layui-nav-item"><a href="javascript:;" src="main.jsp">首页</a></li>

                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;">个人主页</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="列表一" src="emp/toEmpList">列表一</a></dd>
                        <dd><a href="javascript:;" title="列表二" src="two.jsp">列表二</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="列表一" src="emp/toEmpList">员工资料</a></dd>
                        <dd><a href="javascript:;" title="列表二" src="MY/toweekly_list">周报管理</a></dd>
                        <dd><a href="javascript:;" title="列表二" src="chatRecord/gotochatRecordList">谈心记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;">教务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="列表一" src="student-fall/home">届别管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;">后勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="楼栋管理" src="MY/tofloor_list">楼栋管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="welcome.jsp" frameborder="0" id="_iframe" style="width:100%;height:100%;"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });


    $(function () {
        $.each($('#_menuBox').children(), function (index, element) {
            // 左侧菜单上滑收回效果：点击菜单展示时的其他菜单上滑收回效果
            $(element).children('a.small_menu_title').click(function(){
                // 如果当前点击的菜单已展开，则不执行后面步骤
                if(this.parentElement.classList.contains('layui-nav-itemed')){
                    return;
                }
                // 移除其他菜单的class且菜单会上滑收回
                $(this).parent('li.layui-nav-item').siblings('li.layui-nav-item').removeClass('layui-nav-itemed');
            })
            // 左侧导航点击右侧切换效果：为每一个a标签添加点击事件，切换iframe的url
            var element_a = $(element).children('dl').children('dd').children('a');
            element_a.click(function(){
                var url = '${pageContext.request.contextPath}';
                $('#_iframe').attr('src',url+$(this).attr('src'));
            })
        })
    })
</script>
</body>
</html>