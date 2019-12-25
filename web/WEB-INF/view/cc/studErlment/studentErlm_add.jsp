<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加学生</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 30px 0px 30px 44px;">

<%--返回按钮--%>
<div class="layui-inline" style="margin-bottom: 20px">
    <button class="layui-btn layui-btn-sm" onclick="javascript:history.go(-1);"><i class="layui-icon layui-icon-return" style="font-size: 15px; color: #FFF"></i>返回</button>
</div>

<form id="erlmForm" class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="studName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-inline">
                <input type="text" name="card" lay-verify="identity" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="男" title="男" checked="">
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">学生电话</label>
            <div class="layui-input-inline">
                <input type="text" name="tell" lay-verify="phone" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">毕业学校</label>
            <div class="layui-input-inline">
                <input type="text" name="school" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所在班级</label>
            <div class="layui-input-inline">
                <input type="text" name="classes" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">录取成绩</label>
            <div class="layui-input-inline">
                <input type="text" name="score" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">专业类别</label>
            <div class="layui-input-inline">
                <select name="studType">
                    <c:forEach items="${classtype}" var="classtype">
                        <option value="${classtype.classTypeId}">${classtype.classTypeName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">学生专业</label>
            <div class="layui-input-inline">
                <select name="majorId">
                    <c:forEach items="${major}" var="mar">
                        <option value="${mar.majorId}">${mar.majorName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
<%--            <label class="layui-form-label">招生老师</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <select name="empId">--%>
<%--                    <option value=" ">无</option>--%>
<%--                    <c:forEach items="${emp}" var="emp">--%>
<%--                        <option value="${emp.empId}">${emp.empName}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">招生老师</label>
            <div class="layui-input-inline">
                <input type="text" name="negativeName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否送电脑</label>
            <div class="layui-input-inline">
                <select name="computer">
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item" style="margin-left: 138px;">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="sub">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script type="text/javascript">

</script>
<script>
    layui.use(['form', 'layer','laydate'], function(){
        var form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        laydate.render({
            elem:'#date1'
        });

        laydate.render({
            elem:'#date2'
        });

        //监听提交
        form.on('submit(sub)', function(data){
            var lod = layer.load();
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/erlment/addlist" ,//url
                data: $('#erlmForm').serialize(),
                success: function ( result) {
                    layer.close(lod);
                    layer.msg('添加成功',{
                        time:1000
                    },function () {
                        // window.parent.location.reload();
                        location.href='${pageContext.request.contextPath}/erlment/tolist';
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
            return false;
        });

    });


</script>
</body>
</html>
