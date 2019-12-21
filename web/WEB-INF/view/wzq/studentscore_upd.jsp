<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加学生成绩</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
    <form class="layui-form" action="" lay-filter="ff">

        <input type="hidden" name="scoreId"/><%--学生ID--%>

        <input type="hidden" name="stuId"/><%--学生ID--%>

        <div class="layui-form-item" style="margin-top: 20px; margin-left: 20PX">
            <label class="layui-form-label">学生成绩</label>
            <div class="layui-input-block">
                <input type="text" name="score" style="width: 200px" lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20PX">
            <label class="layui-form-label">补考成绩</label>
            <div class="layui-input-block">
                <input type="text" name="rescore" style="width: 200px" autocomplete="off" placeholder="请输入补考成绩" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 20PX">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="courseId" lay-filter="aihao" lay-verify="required">
                    <c:forEach items="${course}" var="list">
                        <option value="${list.courseId}">${list.courseName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 20px">
            <label class="layui-form-label">考试时间</label>
            <div class="layui-input-inline">
                <input type="text" name="scoreTime" id="date" style="width: 200px" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 15px; margin-left: 20px">
            <label class="layui-form-label">考试类别</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="testType" lay-filter="aihao" lay-verify="required">
                    <option value="1">笔试</option>
                    <option value="2">机试</option>
                    <option value="3">模拟面试</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 20px">
            <label class="layui-form-label">在读学期</label>
            <div class="layui-input-block" style="width: 200px">
                <select name="termId" lay-filter="aihao" lay-verify="required">
                    <c:forEach items="${xueqi}" var="list">
                        <option value="${list.termId}">${list.termName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <input type="hidden" name="empId"/><%--录入人员ID--%>

        <div class="layui-form-item layui-form-text" style="margin-top: 15px; margin-left: 20PX">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea placeholder="${score.remark}" name="remark" style="width: 200px" lay-verify="required" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="margin-left: 35px;">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

<%--    监听非空--%>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer
                ,laydate = layui.laydate;

            //日期
            laydate.render({
                elem: '#date',
                type:'date'

            });

            //监听提交
            form.on('submit(demo1)', function(obj){
                var data = obj.field;
                var lindex = layer.load();
                //部门数据
                $.ajax({
                    url: "${pageContext.request.contextPath}/MY/updstudentscore",
                    type: "post",
                    async:true,
                    dataType: "json",
                    data:{
                        scoreId:data.scoreId,
                        stuId:data.stuId,
                        score:data.score,
                        rescore:data.rescore,
                        courseId:data.courseId,
                        testType:data.testType,
                        termId:data.termId,
                        scoreTime:data.scoreTime,
                        empId:data.empId,
                        remark:data.remark
                    },
                    success: function (data) {
                        layer.close(lindex);
                        layer.msg('修改成功',{
                            time:1000
                        },function () {
                            window.parent.location.reload();  //添加成功之后关闭本页面且刷新原页面（直接重新访问原页面）
                        });
                    },
                    error:function () {
                        layer.close(lindex);
                        layer.msg('服务器错误');
                    }
                });
                return false;
            });
           form.val("ff", {
               scoreId:${score.scoreId},
               stuId:${score.stuId},
               score:${score.score},
               rescore:${score.rescore},
               courseId:${score.courseId},
               testType:${score.testType},
               termId:${score.termId},
               empId:${score.empId},
           });
        });
    </script>

</body>
</html>
