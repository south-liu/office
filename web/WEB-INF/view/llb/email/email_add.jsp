<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>填写邮件</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/xm-select/dist/xm-select.js"></script>
</head>
<body style="padding:30px">

    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);">返回</button>
    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>填写邮件</legend>
    </fieldset>

    <form id="emailForm" class="layui-form layui-form-pane" action="">

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">接收人</label>
                <div class="layui-input-inline">
                    <div id="empSelect" style="width: 438px;"></div>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <textarea name="content" id="myEdit" style="display: none;"></textarea>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">上传附件</label>
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn" id="upload">
                        <i class="layui-icon">&#xe67c;</i>选择文件
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="sub">发送</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>



<script type="text/javascript">

    var url = ${pageContext.request.contextPath}/;

    // 配置下拉框 【选择接收人】
    var empSelect = xmSelect.render({
        el: '#empSelect',
        layVerify: 'required',// layui验证表单类型
        name: 'empIds',
        max: 5,// 最大可选数
        toolbar: {// 工具栏
            show: true,
            list: ['CLEAR']
        },
        theme: {
            color: '#8dc63f',
        },
        filterable: true,// 开启过滤
        maxMethod: function (selectedData, item) {// 超出最大时回调函数
            layer.msg('最多可选五位成员！', {time: 1000,icon:0});
        }
    });

    //获取员工
    $.get(url+'email/allEmp',{},function (data) {
        empSelect.update({
            data:data
        });
    },'json');

    layui.use(['form','layedit','upload'],function () {
        var form = layui.form;
        var layedit = layui.layedit;
        var upload = layui.upload;

        var myEdit = layedit.build('myEdit',{

        });

        //执行实例
        var uploadInst = upload.render({
            elem: '#upload', //绑定元素
            //url: '/upload/', //上传接口
            size:10240,
            auto:false,
            accept: 'file',
            done: function(res){
                //上传完毕回调
            },
            error: function(){
                //请求异常回调
            }
        });


        form.on('submit(sub)',function (data) {

            var title = data.field.title;
            var empIds = data.field.empIds;
            var content = layedit.getContent(myEdit);
            var file = $('.layui-upload-file')[0].files[0];
            if (layedit.getText(myEdit).trim() == ''){
                layer.msg('请输入内容',{
                    icon:0,
                    time:2000
                });
                return false;
            }
            var formData = new FormData();
            formData.append('title',title);
            formData.append('empIds',empIds);
            formData.append('content',content);
            if (file!=undefined) {
                formData.append('file',file);
            }
            var lod = layer.load();
            $.ajax({
                type:'post',
                url:url+'email/addEmail',
                processData: false,//jquery 是否对数据进行 预处理
                contentType: false,//不要自己修改请求内容类型
                async:true,
                dataType:'json',
                data:formData,
                success:function (data) {
                    layer.close(lod);
                    layer.msg('发送成功',{
                        icon:1,
                        time:2000
                    },function () {
                        location.href=url+'email/home';
                    });
                },
                error:function () {
                    layer.close(lod);
                    layer.msg('服务器错误，请稍后再试！',{
                        icon:2
                    });
                }
            });

            return false;
        });

    });
</script>
</body>
</html>
