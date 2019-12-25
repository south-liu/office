<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/24
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>班级分配</title>
    <jsp:include page="../../public/head.jsp"></jsp:include>
</head>
<body>
    <div style="padding: 20px; background-color: #F2F2F2;">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md3">
                <div class="layui-card">
                    <div class="layui-card-header">班级列表</div>
                    <div class="layui-card-body">
                        <div id="tree" style="height: 271px;overflow: scroll;"></div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md9">
                <div class="layui-card">
                    <div class="layui-card-header">班级学生列表</div>
                    <div class="layui-card-body">
                        <table class="layui-hide" id="csTab" lay-filter="csfTab"></table>
                    </div>
                </div>
            </div>
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">待分班学生</div>
                    <div class="layui-card-body">
                        <form class="layui-form layui-col-space5" action="" onsubmit="return false;">
                            <div class="layui-inline layui-show-xs-block">
                                <input type="text" id="stuName" name="stuName"  placeholder="学生姓名" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-inline layui-show-xs-block">
                                <input type="text" id="phone" name="phone"  placeholder="手机号码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-inline layui-show-xs-block">
                                <select id="clazz" name="classType">
                                    <option value="">班级类别</option>
                                    <c:forEach items="${classTypes}" var="classType">
                                        <option value="${classType.classTypeId}">${classType.classTypeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="layui-inline layui-show-xs-block">
                                <button class="layui-btn" id="seacch" ><i class="layui-icon">&#xe615;</i></button>
                                <button class="layui-btn layui-btn-sm layui-btn-normal" id="updStuClass">分班</button>
                            </div>
                        </form>
                        <table class="layui-hide" id="sTab" lay-filter="sfTab"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript">
    layui.use(['tree','table'],function () {
        var tree = layui.tree;
        var table = layui.table;

        var csTab = table.render({
            elem: '#csTab',
            title: '班级学生表',
            height:250,
            cols: [[
                {field:'clazz', title:'班级名称 ',align: 'center'},
                {field:'stuName', title:'学生姓名',align: 'center'},
                {field:'sex', title:'性别',align: 'center'},
                {field:'phone', title:'电话号码',align: 'center'},
            ]],
            data:[{clazz:'无',stuName:'无',sex:'无',phone:'无',parentsPhone:'无'}]
        });

        var sTab = table.render({
            id:'sTab',
            elem: '#sTab',
            url: '${pageContext.request.contextPath}/student-class/allNoClassStu', //数据接口
            title: '待分班学生表',
            cols: [[
                {checkbox: true,align: 'center'},
                {field:'stuName', title:'学生姓名',align: 'center'},
                {field:'cardId', title:'身份证',align: 'center'},
                {field:'sex', title:'性别',align: 'center'},
                {field:'phone', title:'电话号码',align: 'center'},
                {field:'middleSchool', title:'学校',align: 'center'},
                {field:'', title:'班级',align: 'center',templet:function (res) {
                        return '未分配';
                    }},
                {field:'introdureTech', title:'招生老师',align: 'center'},
            ]],
        });


        var treeData = [];

        $.ajax({
            type: "POST",
            dataType: "json",
            async:false,
            url: "${pageContext.request.contextPath}/student-class/allClass" ,
            success: function (result) {
               treeData = result;
            },
            error : function() {
                layer.close(lod);
                layer.msg('服务器错误',{
                    icon:2
                });
            }
        });

        //渲染
        tree.render({
            elem:'#tree',
            id:'myTree',
            data:treeData,
            showCheckbox:true,
            onlyIconControl:true,
            height:50,
            click:function(obj){
                if (obj.data.children == undefined) {
                    console.log(obj.data);
                    table.reload('csTab',{
                        url:'${pageContext.request.contextPath}/student-class/allThisClassStu',
                        where:{clazz:obj.data.id}
                    });

                } else {
                    layer.msg('请点击班级',{
                        icon:0
                    });
                }
            }
        });

        //搜索
        $('#seacch').click(function () {
            var stuName = $('#stuName').val();
            var phone = $('#phone').val().trim();
            var classType = $('#classType').val();
            table.reload('sTab',{
                url:'${pageContext.request.contextPath}/student/pageListWhere',
                where:{
                    stuName:stuName,
                    phone:phone,
                    clazz:clazz,
                }
            });
        });

        //分班
        $('#updStuClass').click(function () {
            var checkTableData = table.checkStatus('sTab').data;
            var checkTreeData = tree.getChecked('myTree');
            if (checkTableData.length < 1 ){
                layer.msg('请至少选择一个学生',{
                    time:1000,
                    icon:0
                })
            } else {
                if (checkTreeData.length==1 && checkTreeData[0].children.length==1){
                    var stuIds = [];
                    for (var i in checkTableData) {
                        stuIds.push(checkTableData[i].studId);
                    }
                    var lod = layer.load();
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${pageContext.request.contextPath}/student-class/updClass" ,
                        data: {stuIds:stuIds,classId:checkTreeData[0].children[0].id},
                        success: function (result) {
                            layer.close(lod);
                            layer.msg('调整成功',{
                                time:1000,
                                icon:1
                            },function () {
                                window.location.reload();
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
                } else {
                    layer.msg('请选择一个班级',{
                        time:1000,
                        icon:0
                    })
                }
            }

        });

    });
</script>
</body>
</html>
