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
    <jsp:include page="../head.jsp"></jsp:include>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-size: 35px;">office</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <%--<ul class="layui-nav layui-layout-left">
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
        </ul>--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${student.stuName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a id="repass" href="javascript:;">修改密码</a></dd>
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

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="small_menu_title" href="javascript:;"><i class="layui-icon layui-icon-username" style="font-size: 15px; color: #FFF;margin-right: 10px;"></i>个人主页</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" title="申请请假" src="hstudent/goto_hs_myapply_list"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>申请请假</a></dd>
                        <dd><a href="javascript:;" title="问题反馈" src="feedback/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>问题反馈</a></dd>
                        <dd><a href="javascript:;" title="报修申请" src="repair/tolist"><i class="layui-icon layui-icon-right" style="font-size: 15px; color: #FFF;margin: 0 10px;"></i>报修申请</a></dd>
                        <dd><a href="javascript:;" title="教师考评" src="s/assessment/writerAssessment"><i class="layui-icon layui-icon-right" style="font-size:
                        15px;
                        color: #FFF;margin: 0
                        10px;"></i>教师考评</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="${pageContext.request.contextPath}/studentSide/welcome" frameborder="0" id="_iframe"
                style="width:100%;height:100%;"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © office
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use(['element', 'layer'], function () {
        var element = layui.element;
        var layer = layui.layer;


        //修改密码
        $('#repass').click(function () {
            var op1 = layer.open({
                title: '验证密码',
                type: 1,
                content: '<div style="margin-top: 10px" class="layui-inline">\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '          <label class="layui-form-label">原密码：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="oldPass" autocomplete="off" class="layui-input">\n' +
                    '        </div>' +
                    '       </div> \n' +
                    '        </div> \n',

                btn:['确定','取消'],
                yes:function (index,layero) {
                    var oldpass = $('#oldPass').val().trim();
                    if (oldpass==''){
                        layer.msg('请输入密码',{
                            icon:0,
                            time:1000
                        });
                        return;
                    }
                    var lod1 = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/studentSide/selStu",
                        data: {stuId:${student.studId}},
                        success: function (result) {
                            layer.close(lod1);
                            var pass = result.passWord;
                            if (pass!=oldpass) {
                                layer.msg('密码错误', {
                                    icon:2,
                                    time: 1000
                                });
                            } else {
                                layer.close(op1);
                                layer.msg('验证成功', {
                                    icon:1,
                                    time: 1000
                                }, function () {
                                    updPass();
                                });
                            }
                        },
                        error: function () {
                            layer.close(lod1);
                            layer.msg('服务器错误');
                        }
                    });
                },
                btnAlign:'c',
                area:['350px','200px']
            })
        });

        //修改密码
        function updPass() {
            var op1 = layer.open({
                title: '请输入新密码',
                type: 1,
                content: '<div style="margin-top: 10px" class="layui-inline">\n' +

                    '  <div style="margin: 20px 0px ">' +
                    '          <label class="layui-form-label">新密码：</label>\n' +
                    '        <div class="layui-input-inline">\n' +
                    '            <input type="text" id="newPass" autocomplete="off" class="layui-input">\n' +
                    '        </div>' +
                    '       </div> \n' +
                    '        </div> \n',

                btn:['确定','取消'],
                yes:function (index,layero) {
                    var newPass = $('#newPass').val().trim();
                    if (newPass==''){
                        layer.msg('请输入密码',{
                            icon:0,
                            time:1000
                        });
                        return;
                    }
                    var lod1 = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/studentSide/repass",
                        data: {stuId:${student.studId},password:newPass},
                        success: function (result) {
                            layer.msg('修改成功,请重新登陆', {
                                icon:1,
                                time: 1000
                            }, function () {
                                exit();
                            });
                        },
                        error: function () {
                            layer.close(lod1);
                            layer.msg('服务器错误');
                        }
                    });
                },
                btnAlign:'c',
                area:['350px','200px']
            })
        };


        $('#exit').click(function () {
            layer.confirm('确认退出系统吗？', function () {
                exit();
            })
        });

        function exit() {
            var lod = layer.load();
            $.ajax({
                url: "${pageContext.request.contextPath}/studentSide/exit",
                type: "post",
                async:true,
                dataType: "json",
                data:{},
                success: function (data) {
                    layer.close(lod);
                    layer.msg('退出成功',{
                        time:1000,
                        icon:1
                    },function () {
                        window.top.location='${pageContext.request.contextPath}/studentSide/login';
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
        }
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
            $('#_iframe').attr('src', '${pageContext.request.contextPath}/studentSide/welcome');
        });

    })
</script>
</body>
</html>