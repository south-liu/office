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
    <jsp:include page="head.jsp"></jsp:include>
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
                    ${emp.empName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a id="exit" href="javascript:;">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" id="_menuBox">

                <li class="layui-nav-item"><a href="javascript:;" id="_welcome"><i class="layui-icon layui-icon-home" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>首页</a></li>

                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-username" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>个人主页</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="我的邮件" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>我的邮件</a></dd>
                        <dd><a href="javascript:;" title="员工请假" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>员工请假</a></dd>
                        <dd><a href="javascript:;" title="学生请假" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>学生请假</a></dd>
                        <dd><a href="javascript:;" title="我的周报" src="MY/tomyweekly_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>我的周报</a></dd>
                        <dd><a href="javascript:;" title="考勤管理" src="checkwork/gotocheckworklist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>考勤管理</a></dd>
                        <dd><a href="javascript:;" title="离职申请" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>离职申请</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-carousel" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>通知公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="公告发布" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>公告发布</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-user" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="员工资料" src="emp/toEmpList"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>员工资料</a></dd>
                        <dd><a href="javascript:;" title="周报管理" src="MY/toweekly_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>周报管理</a></dd>
                        <dd><a href="javascript:;" title="谈心记录" src="chatRecord/gotochatRecordList"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>谈心记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-component" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>教务管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="学生资料" src="student/toStuList"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>学生资料</a></dd>
                        <dd><a href="javascript:;" title="考试成绩" src="MY/toscore_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>考试成绩</a></dd>
                        <dd><a href="javascript:;" title="答辩成绩" src="student-reply-score/home"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>答辩成绩</a></dd>
                        <dd><a href="javascript:;" title="班级管理" src="studentclass/gotostudentclasslist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>班级管理</a></dd>
                        <dd><a href="javascript:;" title="课程类别" src="MY/totype_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>课程类别</a></dd>
                        <dd><a href="javascript:;" title="课程管理" src="MY/tocourse_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>课程管理</a></dd>
                        <dd><a href="javascript:;" title="试讲培训" src="trial/gototriallist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>试讲培训</a></dd>
                        <dd><a href="javascript:;" title="值班管理" src="weekang/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>值班管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-tabs" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>后勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="宿舍管理" src="student-huor/home"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>宿舍管理</a></dd>
                        <dd><a href="javascript:;" title="楼栋管理" src="MY/tofloor_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>楼栋管理</a></dd>
                        <dd><a href="javascript:;" title="维修管理" src="repair/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>维修管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-date" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>考核管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="考核指标" src="MY/toaduitmodel"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>考核指标</a></dd>
                        <dd><a href="javascript:;" title="员工考核" src="MY/toaduitlog"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>员工考核</a></dd>
                        <dd><a href="javascript:;" title="教师考评" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>教师考评</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-chart" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>系统报表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="系统报表" src=""><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>系统报表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-set" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="届别管理" src="student-fall/home"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>届别管理</a></dd>
                        <dd><a href="javascript:;" title="班级类别" src="classtype/gotoclasstypelist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>班级类别</a></dd>
                        <dd><a href="javascript:;" title="部门管理" src="dept/toDeptList"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>部门管理</a></dd>
                        <dd><a href="javascript:;" title="院系设置" src="Chengcollege/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>院系设置</a></dd>
                        <dd><a href="javascript:;" title="专业设置" src="major/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>专业设置</a></dd>
                        <dd><a href="javascript:;" title="学期设置" src="MY/toterm_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>学期设置</a></dd>
                        <dd><a href="javascript:;" title="答辩项目" src="project/home"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>答辩项目</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="${pageContext.request.contextPath}/public/welcome" frameborder="0" id="_iframe"
                style="width:100%;height:100%;"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use(['element', 'layer'], function () {
        var element = layui.element;
        var layer = layui.layer;

        $('#exit').click(function () {
            layer.confirm('确认退出系统吗？', function () {
                var lod = layer.load();
                $.ajax({
                    url: "${pageContext.request.contextPath}/system/exit",
                    type: "post",
                    async:true,
                    dataType: "json",
                    data:{},
                    success: function (data) {
                        layer.close(lod);
                        layer.msg('退出成功',{
                            time:1000
                        },function () {
                            window.top.location='${pageContext.request.contextPath}/public/login';
                        });
                    },
                    error:function () {
                        layer.close(lod);
                        layer.msg('服务器错误');
                    }
                });
            })
        });
    });


    $(function () {
        $.each($('#_menuBox').children(), function (index, element) {
            // 左侧菜单上滑收回效果：点击菜单展示时的其他菜单上滑收回效果
            $(element).children('a.small_menu_title').click(function () {
                // 如果当前点击的菜单已展开，则不执行后面步骤
                if (this.parentElement.classList.contains('layui-nav-itemed')) {
                    return;
                }
                // 移除其他菜单的class且菜单会上滑收回
                $(this).parent('li.layui-nav-item').siblings('li.layui-nav-item').removeClass('layui-nav-itemed');
            })
            // 左侧导航点击右侧切换效果：为每一个a标签添加点击事件，切换iframe的url
            var element_a = $(element).children('dl').children('dd').children('a');
            element_a.click(function () {
                var url = '${pageContext.request.contextPath}';
                $('#_iframe').attr('src', url + $(this).attr('src'));
            })
        });

        $('#_welcome').click(function () {
            $('#_iframe').attr('src', '${pageContext.request.contextPath}/public/welcome');
        });

    })
</script>
</body>
</html>