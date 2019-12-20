<%--
  Created by IntelliJ IDEA.
  User: illusory
  Date: 2019/12/19
  Time: 8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考评详细</title>

    <jsp:include page="/WEB-INF/view/public/head.jsp"></jsp:include>
</head>
<body>
${assessment}
${assessmentInformationList}
<blockquote class="layui-elem-quote">
    <h1>${assessmentEmp.postName}【${assessmentEmp.empName}】的考评：</h1>
    <p style="margin-top: 10px;">考评时间：${assessment.startTime}&ensp;~&ensp;${assessment.endTime}</p>
</blockquote>
<fieldset class="layui-elem-field">
    <legend>内容</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">短输入框</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">日期选择</label>
                    <div class="layui-input-block">
                        <input type="text" name="date" id="date1" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">行内表单</label>
                    <div class="layui-input-inline">
                        <input type="text" name="number" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">建议：</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button>
            </div>
        </form>
    </div>
</fieldset>
<div id="test9"></div>

<script type="text/javascript">
    layui.use(['rate'], function() {
        var rate = layui.rate;
        //只读
        rate.render({
            elem: '#test9'
            , value: 4
            , readonly: true
        });
    });
</script>
</body>
</html>
