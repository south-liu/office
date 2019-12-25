<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2019/12/5
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询谈心记录</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>

<body>
<table class="layui-table"
       lay-data="{url:'${pageContext.request.contextPath}/chatRecord/chatRecordlist', page:true, id:'myTable'}"
       lay-filter="fTable">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'chatId', width:80, sort: true, fixed: true,align:'center'}">ID</th>
        <th lay-data="{field:'stuName', width:135}">学生名称</th>
        <th lay-data="{field:'empName', width:135}">员工名称</th>
        <th lay-data="{field:'address', width:135}">地址</th>
        <th lay-data="{field:'sayScon', width:300}">谈心内容</th>
        <th lay-data="{field:'chatDate', width:145, sort: true}">时间</th>
        <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addCheckData" onclick="addchatRecord()">添加记录</button>
    </div>
</script>


<script>
    <%--初始化table--%>
    layui.use(['table', 'laydate'], function () {
        var table = layui.table;
        var laydate = layui.laydate;

        table.on('tool(fTable)', function (obj) {
                var event = obj.event;
                console.log(obj.data.classTypeName);
                if (event === 'detail') {
                    layer.alert(JSON.stringify(obj.data));
                } else if (event === 'del') {
                    layer.confirm('确认删除', function (index) {
                        console.log(index);
                        $.post('${pageContext.request.contextPath}/chatRecord/chatRecorddelete', {chatId: obj.data.chatId}, function (data) {
                            console.log(data);
                            layer.msg('删除成功！');
                            location.reload();
                        }, 'json');
                        layer.close(index);
                    });
                }
            }
        )


//        添加记录
        <%--table.on('toolbar(fTable)', function (obj) {--%>
        <%--var checkStatus = table.checkStatus(obj.config.id);--%>
        <%--if (obj.event == 'addCheckData') {--%>
        <%--layer.open({--%>
        <%--title: '添加记录',--%>
        <%--type: 1,--%>
        <%--content: '<div style="margin-top: 10px" class="layui-inline">\n' +--%>

        <%--'  <div style="margin: 20px 0px ">' +--%>
        <%--'          <label class="layui-form-label">学生姓名：</label>\n' +--%>
        <%--'        <div class="layui-input-inline">\n' +--%>
        <%--'            <input type="text" id="studentname" lay-verify="required" autocomplete="off" class="layui-input">\n' +--%>
        <%--'        </div>' +--%>
        <%--'       </div> \n' +--%>

        <%--'  <div style="margin: 20px 0px ">' +--%>
        <%--'        <label class="layui-form-label">地址：</label>\n' +--%>
        <%--'        <div class="layui-input-inline">\n' +--%>
        <%--'            <input type="text" id="address" lay-verify="required" autocomplete="off" class="layui-input">\n' +--%>
        <%--'        </div>\n' +--%>
        <%--'       </div> \n' +--%>


        <%--'  <div style="margin: 20px 0px ">' +--%>
        <%--'        <label class="layui-form-label">内容：</label>\n' +--%>
        <%--'        <div class="layui-input-inline">\n' +--%>
        <%--'            <textarea id="sayScon" lay-verify="required" autocomplete="off" class="layui-input" style="width: 300px;height: 100px"></textarea>\n' +--%>
        <%--'        </div>\n' +--%>
        <%--'       </div> \n' +--%>


        <%--'  <div style="margin: 20px 0px ">' +--%>
        <%--'        <label class="layui-form-label">时间：</label>\n' +--%>
        <%--'        <div class="layui-input-inline">\n' +--%>
        <%--'            <input type="date" id="chatDate" lay-verify="required" autocomplete="off" class="layui-input">\n' +--%>
        <%--'        </div>\n' +--%>
        <%--'       </div> \n' +--%>
        <%--'        </div> \n',--%>

        <%--btn: ['确定', '取消'],--%>
        <%--yes: function (index, layero) {--%>
        <%--//                            选中输入框--%>
        <%--var studentname = $('#studentname').val().trim();--%>
        <%--var address = $('#address').val().trim();--%>
        <%--var sayScon = $('#sayScon').val().trim();--%>
        <%--var chatDate = $('#chatDate').val().trim();--%>
        <%--//                            判断是否为空--%>
        <%--if (studentname == '' || address == '' || sayScon == '' || chatDate == '') {--%>
        <%--layer.msg('请输完全部信息!', {--%>
        <%--icon: 2,--%>
        <%--time: 1000--%>
        <%--});--%>
        <%--} else {--%>
        <%--var--%>
        <%--lindex = layer.load();--%>
        <%--$.ajax({--%>
        <%--type: "post",--%>
        <%--url: "${pageContext.request.contextPath}/chatRecord/chatRecordadd",--%>
        <%--async: true,--%>
        <%--dataType: "text",--%>
        <%--data: {--%>
        <%--stuName: studentname,--%>
        <%--address: address,--%>
        <%--sayScon: sayScon,--%>
        <%--chatDate: chatDate--%>
        <%--},--%>
        <%--success: function (data) {--%>
        <%--layer.close(lindex);--%>
        <%--layer.msg('添加成功!', {--%>
        <%--icon: 1,--%>
        <%--time: 1000--%>
        <%--}, function () {--%>
        <%--layer.close(index);--%>
        <%--// location.reload();--%>
        <%--table.reload('myTable', {--%>
        <%--url: '${pageContext.request.contextPath}/chatRecord/chatRecordlist'--%>
        <%--});--%>
        <%--});--%>
        <%--},--%>
        <%--error: function () {--%>
        <%--layer.close(lindex);--%>
        <%--layer.msg("服务器错误");--%>
        <%--}--%>
        <%--});--%>
        <%--}--%>
        <%--},--%>
        <%--btnAlign: 'c',--%>
        <%--area: ['450px', '430px']--%>
        <%--});--%>

        <%--}--%>
        <%--});--%>
    });

    function addchatRecord() {
        layer.open({
            title: '添加试讲日期',
            type: 2,
            content: ['${pageContext.request.contextPath}/chatRecord/gotochatRecord_add', 'no'],
            area: ['480px', '550px'],
            resize: false
        })
    }
</script>
</body>
</html>
