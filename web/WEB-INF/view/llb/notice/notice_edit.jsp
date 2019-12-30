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
    <title>编辑公告</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding:30px">

    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);">返回</button>
    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>修改公告</legend>
    </fieldset>

    <form id="noticeForm" lay-filter="ff" class="layui-form layui-form-pane" action="">
        <input type="hidden" name="noticeId" value="${notice.noticeId}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">接收对象</label>
                <div class="layui-input-inline">
                    <select name="noticeType">
                            <option value="1">所有人</option>
                            <option value="2">员工</option>
                            <option value="3">学生</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <textarea name="content" id="myEdit" style="display: none;">${notice.content}</textarea>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="sub">确定修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>



<script type="text/javascript">
    layui.use(['form','layedit'],function () {
        var form = layui.form;
        var layedit = layui.layedit;

        var myEdit = layedit.build('myEdit',{
            tool:['strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线

                ,'|' //分割线

                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'link' //超链接
                ,'unlink' //清除链接
                ,'face']
        });

        form.val('ff',{
            'title':'${notice.title}',
            'noticeType':${notice.noticeType},
        });

        form.on('submit(sub)',function (data) {

            if (layedit.getText(myEdit).trim() == ''){
                layer.msg('请输入内容',{
                    icon:0,
                    time:2000
                });
                return false;
            }

            //同步编辑器内容到textarea
            layedit.sync(myEdit);

            var lod = layer.load();
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/notice/editNotice" ,//url
                data: $('#noticeForm').serialize(),
                success: function (result) {
                    layer.close(lod);
                    layer.msg('编辑成功',{
                        time:1000
                    },function () {
                        location.href="${pageContext.request.contextPath}/notice/toNoticeList";
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误',{
                        icon:2,
                        time:1000
                    });
                }
            });

            return false;
        });

    });
</script>
</body>
</html>
