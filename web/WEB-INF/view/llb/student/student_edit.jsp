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
    <title>编辑学生</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body style="padding: 30px 0px 30px 44px;">
<form id="stuForm" class="layui-form layui-form-pane" action="" lay-filter="fTable">
    <input type="hidden" name="studId" value="">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="stuName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-inline">
                <input type="text" name="cardId" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">担保人</label>
            <div class="layui-input-inline">
                <input type="text" name="guarantee" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">毕业学校</label>
            <div class="layui-input-inline">
                <input type="text" name="middleSchool" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
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
        <div class="layui-inline">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-inline">
                <input type="text" name="age" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="text" id="date2" name="birthDay" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">学生电话</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">家庭地址</label>
            <div class="layui-input-inline">
                <input type="text" name="addr" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入学时间</label>
            <div class="layui-input-inline">
                <input type="text" id="date1" name="enterTime" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">介绍老师</label>
            <div class="layui-input-inline">
                <input type="text" name="introdureTech" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">户口性质</label>
            <div class="layui-input-inline">
                <select name="resiDence">
                    <option value="1">农业</option>
                    <option value="2">非农</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-inline">
                <input type="text" name="naTion" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">籍贯</label>
            <div class="layui-input-inline">
                <input type="text" name="naTives" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">专业类别</label>
            <div class="layui-input-inline">
                <select name="proLevel">
                    <option value="1">中技</option>
                    <option value="2">高技</option>
                    <option value="3">3+2</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">学习类别</label>
            <div class="layui-input-inline">
                <select name="studyType">
                    <option value="1">全日制</option>
                    <option value="2">函授</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">家长姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="parents" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">家长电话</label>
            <div class="layui-input-inline">
                <input type="text" name="parentsPhone" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">老师电话</label>
            <div class="layui-input-inline">
                <input type="text" name="intrPhone" lay-verify="" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">面试人</label>
            <div class="layui-input-inline">
                <input type="text" name="audiTion" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否送电脑</label>
            <div class="layui-input-inline">
                <select name="computer">
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">是否领用</label>
            <div class="layui-input-inline">
                <select name="collar">
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否中专</label>
            <div class="layui-input-inline">
                <select name="isVocational">
                    <option value="2">是</option>
                    <option value="1">否</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">中专学校</label>
            <div class="layui-input-inline">
                <input type="text" name="vocationalsch" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">中专学籍</label>
            <div class="layui-input-inline">
                <select name="vocationalFlag">
                    <option value="1">已退</option>
                    <option value="2">保留</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">省录取号</label>
            <div class="layui-input-inline">
                <input type="text" name="enrollno" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">面试人意见</label>
            <div class="layui-input-inline">
                <input type="text" name="auditionOption" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">学号</label>
            <div class="layui-input-inline">
                <input type="text" name="stuno" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">报名号</label>
            <div class="layui-input-inline">
                <input type="text" name="registration" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">低保</label>
            <div class="layui-input-inline">
                <select name="dibao">
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">生源类型</label>
            <div class="layui-input-inline">
                <select name="sourceType">
                    <option value="普通生源">普通生源</option>
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
                url: "${pageContext.request.contextPath}/student/updStu" ,//url
                data: $('#stuForm').serialize(),
                success: function (result) {
                    layer.close(lod);
                    layer.msg('修改成功',{
                        time:1000
                    },function () {
                        window.parent.location.reload();
                    });
                },
                error : function() {
                    layer.close(lod);
                    layer.msg('服务器错误');
                }
            });
            return false;
        });

        //表单赋值
        form.val('fTable', {
            "studId": "${student.studId}",
            "stuName": "${student.stuName}",
            "cardId": "${student.cardId}",
            "guarantee": "${student.guarantee}",
            "middleSchool": "${student.middleSchool}",
            "sex": "${student.sex}",
            "age": "${student.age}",
            "birthDay": "${student.birthDay}",
            "phone": "${student.phone}",
            "addr": "${student.addr}",
            "enterTime": "${student.enterTime}",
            "introdureTech": "${student.introdureTech}",
            "proLevel": "${student.proLevel}",
            "naTion": "${student.naTion}",
            "naTives": "${student.naTives}",
            "resiDence": "${student.resiDence}",
            "studyType": "${student.studyType}",
            "parents": "${student.parentsPhone}",
            "parentsPhone": "${student.parentsPhone}",
            "intrPhone": "${student.intrPhone}",
            "audiTion": "${student.audiTion}",
            "computer": "${student.computer}",
            "collar": "${student.collar}",
            "isVocational": "${student.isVocational}",
            "vocationalsch": "${student.vocationalsch}",
            "vocationalFlag": "${student.vocationalFlag}",
            "enrollno": "${student.enrollno}",
            "auditionOption": "${student.auditionOption}",
            "stuno": "${student.stuno}",
            "registration": "${student.registration}",
            "dibao": "${student.dibao}",
            "sourceType": "${student.sourceType}",
            "remark": "${student.remark}"
        });

    });
</script>
</body>
</html>
