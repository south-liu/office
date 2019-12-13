<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生登陆</title>
    <jsp:include page="../head.jsp"></jsp:include>
</head>
<style type="text/css">
    .login-bg{
        /*background: #eeeeee url() 0 0 no-repeat;*/
        background:url(../../images/bg.png) no-repeat center;
        background-size: cover;
        overflow: hidden;
    }
    .login{
        margin: 120px auto 0 auto;
        min-height: 420px;
        max-width: 420px;
        padding: 40px;
        background-color: #ffffff;
        margin-left: auto;
        margin-right: auto;
        border-radius: 4px;
        /* overflow-x: hidden; */
        box-sizing: border-box;
    }
    .login a.logo{
        display: block;
        height: 58px;
        width: 167px;
        margin: 0 auto 30px auto;
        background-size: 167px 42px;
    }
    .login .message {
        margin: 10px 0 0 -58px;
        padding: 18px 10px 18px 60px;
        background: #189F92;
        position: relative;
        color: #fff;
        font-size: 16px;
    }
    .login #darkbannerwrap {
        background: url(../../images/aiwrap.png);
        width: 18px;
        height: 10px;
        margin: 0 0 20px -58px;
        position: relative;
    }

    .login input[type=text],
    .login input[type=file],
    .login input[type=password],
    .login input[type=email], select {
        border: 1px solid #DCDEE0;
        vertical-align: middle;
        border-radius: 3px;
        height: 50px;
        padding: 0px 16px;
        font-size: 14px;
        color: #555555;
        outline:none;
        width:100%;
        box-sizing: border-box;
    }
    .login input[type=text]:focus,
    .login input[type=file]:focus,
    .login input[type=password]:focus,
    .login input[type=email]:focus, select:focus {
        border: 1px solid #27A9E3;
    }
    .login input[type=submit],
    .login input[type=button]{
        display: inline-block;
        vertical-align: middle;
        padding: 12px 24px;
        margin: 0px;
        font-size: 18px;
        line-height: 24px;
        text-align: center;
        white-space: nowrap;
        vertical-align: middle;
        cursor: pointer;
        color: #ffffff;
        background-color: #189F92;
        border-radius: 3px;
        border: none;
        -webkit-appearance: none;
        outline:none;
        width:100%;
    }
    .login hr {
        background: #fff  0 0 no-repeat;
    }
    .login hr.hr15 {
        height: 15px;
        border: none;
        margin: 0px;
        padding: 0px;
        width: 100%;
    }
    .login hr.hr20 {
        height: 20px;
        border: none;
        margin: 0px;
        padding: 0px;
        width: 100%;
    }
</style>
<body class="login-bg">
<div class="login layui-anim layui-anim-up">
    <div class="message">用户登录</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form" >
        <input name="phone" placeholder="手机号"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <div style="margin-bottom: 20px;">
            <%--<label>完成验证：</label>--%>
            <div id="captcha" style="font-size: 10px;">
                <p id="wait" >正在加载验证码......</p>
            </div>
        </div>
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/gt.js"></script>
<script>
    $(function  () {
        //获取极简验证码
        getGee();

        layui.use('form', function(){
            var form = layui.form;
            //监听提交
            form.on('submit(login)', function(data){
                var challenge = data.field.geetest_challenge;
                var validate = data.field.geetest_validate;
                var seccode = data.field.geetest_seccode;
                console.log(challenge);
                if (challenge==''||validate==''||seccode==''){
                    layer.msg('请先完成验证！',{
                        icon:0
                    });
                } else {
                    var index = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/system/student/login",
                        async:true,
                        dataType:"json",
                        data:{
                            phone:data.field.phone,
                            passWord:data.field.password,

                            //二次验证
                            challenge:challenge,
                            validate:validate,
                            seccode :seccode
                        },
                        success:function(data){
                            layer.close(index);
                            if (data.type == "error") {
                                layer.msg(data.msg,{
                                    icon:2
                                });
                            } else if (data.type == 'geeError') {
                                layer.msg(data.msg,{
                                    icon:2
                                });
                                $("#captcha").html('');
                                getGee();
                            } else {
                                layer.msg(data.msg,{
                                    icon:1,
                                    time:1000
                                },function(){
                                    window.location='${pageContext.request.contextPath}/public/student/index'
                                });
                            }
                        },
                        error:function () {
                            layer.close(index);
                            layer.msg("服务器错误",{
                                icon:2
                            });
                        }
                    });
                }
                return false;
            });
        });

        //获取极简验证码
        function getGee() {
            $.ajax({
                url: "${pageContext.request.contextPath}/system/geeCheck?t=" + (new Date()).getTime(), // 加随机数防止缓存
                type: "get",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    // 调用 initGeetest 初始化参数
                    // 参数1：配置参数
                    // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
                    initGeetest({
                        gt: data.gt,
                        challenge: data.challenge,
                        new_captcha: data.new_captcha, // 用于宕机时表示是新验证码的宕机
                        offline: !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                        product: "float", // 产品形式，包括：float，popup
                        width: "100%"
                    }, handler);
                }
            });
        };

        //生成验证码
        function handler (captchaObj) {
            // 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
            captchaObj.appendTo("#captcha");
            captchaObj.onReady(function () {
                $("#wait").hide();
            });
        };
    });

</script>

</body>
</html>
