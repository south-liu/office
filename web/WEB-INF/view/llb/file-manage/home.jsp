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
    <title>员工列表</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<style type="text/css">
    .layui-btn-llb{
        height: 22px;
        padding: 6px 5px;
        font-size: 12px;
    }
    .layui-btn-llbb{
        color: #fff;
        border-radius: 2px;
        cursor: pointer;
    }
</style>
<body>

<table class="layui-hide" id="myTab" lay-filter="fTab"></table>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addFile">添加文件</button>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="getFile">下载文件</a>
</script>

<div id="addFile" style="display: none;padding: 20px 35px 20px 0px;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">选择文件</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="upload">
                    <i class="layui-icon">&#xe67c;</i>上传文件
                </button>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">说明</label>
        <div class="layui-input-block">
            <textarea id="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-input-block" style="margin-left: 208px;">
        <button type="submit" class="layui-btn" id="sub">确定添加</button>
    </div>
</div>

<script>
    layui.use(['table','upload'], function(){
        var table = layui.table;
        var upload = layui.upload;

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

        $('#sub').click(function () {
            var file = $('.layui-upload-file')[0].files[0];
            var remark = $('#remark').val().trim();
            if (file == null) {
                layer.msg('请选择文件',{
                    icon:0
                });
                return;
            }
            if (remark == ""){
                layer.msg('请填写说明',{
                    icon:0
                });
                return;
            }
            var lod = layer.load();
            var formData = new FormData();
            formData.append('file',file);
            formData.append('remark',remark);
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/file/addFile',
                processData: false,//jquery 是否对数据进行 预处理
                contentType: false,//不要自己修改请求内容类型
                async:true,
                dataType:'json',
                data:formData,
                success:function (data) {
                    layer.close(lod);
                    layer.msg('上传成功',{
                        icon:1,
                        time:2000
                    },function(){
                        location.reload();
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

        table.render({
            elem: '#myTab',
            url:'${pageContext.request.contextPath}/file/pageList',
            toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '文件表',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field:'docId', title:'编号', sort: true,align: 'center'},
                {field:'dataName', title:'资料名称 ',align: 'center'},
                {field:'optime', title:'上传时间',sort: true,align: 'center'},
                {field:'remark', title:'备注', align: 'center'},
                {field:'empName', title:'上传人',align: 'center'},
                {fixed: 'right', title:'操作', toolbar: '#bar',align: 'center'}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(fTab)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var length = checkStatus.data.length;
            switch(obj.event){
                case 'addFile':
                    layer.open({
                        title:'添加文件',
                        type:1,
                        content:$('#addFile'),
                        area: ['500px', '350px'],
                        resize:false,
                        // maxmin:true
                    });
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(fTab)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除此文件吗', function(index){
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/file/delFile" ,
                        data: {docId:data.docId},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('删除成功',{
                                icon:1,
                                time:1000
                            },function () {
                               window.location.reload();
                            });
                        },
                        error : function() {
                            layer.close(lod);
                            layer.msg('服务器错误',{
                                icon:2
                            });
                        }
                    });
                });
            } else if(obj.event === 'getFile'){
                location.href='${pageContext.request.contextPath}/file/download.do?docId='+data.docId;
            }
        });
    })
</script>
</body>
</html>
