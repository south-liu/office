<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学期管理</title>
    <jsp:include page="../public/head.jsp"></jsp:include>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>

<%--列表头部的按钮--%>
<script type="text/html" id="toolbarDemo">
    <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
        <button data-method="notice" class="layui-btn" lay-event="getadd">添加学期</button>
        <%--<button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>--%>
        <%--<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>--%>
        <%--<button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>--%>
    </div>
</script>

<%--操作列表内的按钮--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<%--表单监听--%>
<script>
    layui.use(['form', 'layedit'], function(){
        var form = layui.form
            ,layedit = layui.layedit

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 4){
                    return '标题至少得4个字符啊';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });
    });
</script>

<%--加载列表--%>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${pageContext.request.contextPath}/MY/selcemester'
            ,toolbar: '#toolbarDemo'
            ,title: '学期表'
            ,totalRow: true
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'termId', title:'学期编号', width:120, fixed: 'left', unresize: true, sort: true, totalRowText: '合计'}
                ,{field:'remark', title:'学期名称', width:120, edit: 'text'}
                ,{field:'termName', title:'说明', width:220, edit: 'text'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
        });

        //添加按钮触发事件
        var active = {
            notice: function(){
                //示范一个公告层
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '400px;'  //设置宽高：area: ['400px', '180px']
                    ,shade: 0.8
                    ,btn: ['添加', '重置', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    //,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
                    ,content: '<form class="layui-form" action="" lay-filter="example" method="post">' +
                                    '<div class="layui-form-item">' +
                                        '<label class="layui-form-label">输入框</label>' +
                                        '<div class="layui-input-block">' +
                                            '<input type="text" name="username" style="width: 200px" lay-verify="title" autocomplete="off" placeholder="请输入学期名称" class="layui-input">' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="layui-form-item layui-form-text">' +
                                        '<label class="layui-form-label">文本域</label>' +
                                        '<div class="layui-input-block">' +
                                        '   <textarea placeholder="请输入内容" style="width: 200px; height: 40px" class="layui-textarea" name="desc"></textarea>' +
                                        '</div>' +
                                    '</div>' +
//                                    '<div class="layui-form-item">' +
//                                    '   <div class="layui-input-block">' +
//                                            '<button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-setval">赋值</button>' +
//                                            '<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>' +
//                                        '</div>' +
//                                    '</div>' +
                                '</form>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        //选中窗口的第一个按钮
                        btn.find('.layui-layer-btn0').attr({
                            href: 'http://www.layui.com/'
                            ,target: '_blank'
                        });
                        //选中窗口的第二个按钮（未选中的按钮默认为退出/取消）
                        btn.find('.layui-layer-btn1').attr({
                            href: 'http://www.baidu.com/'
                            ,target: '_blank'
                        });
                    }
                });
            }
        };

        //工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选')
                    break;
                case 'getadd':
                    var othis = $(this), method = othis.data('method');
                    active[method] ? active[method].call(this, othis) : '';
            };
        });
    });
</script>










<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>参照</legend>
</fieldset>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test',
            url:'${pageContext.request.contextPath}/MY/selcemester',
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '用户数据表',
            cols: [[
                {field:'id',align:'center', title:'ID', width:80, fixed: 'left', unresize: true, sort: true},
                {field:'weixin',align:'center', title:'微信', width:120, edit: 'text'},
                {field:'createTime',align:'center',title:'开始时间', width:200, sort: true},
                {fixed: 'right',align:'center', title:'操作', toolbar: '#barDemo', width:100}
            ]],
            page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'add':
                    var index = layer.open({
                        title:'添加',
                        type:1,
                        content:'<div style="margin-top: 20px" class="layui-inline">\n' +
                        '        <label class="layui-form-label">微信号</label>\n' +
                        '        <div class="layui-input-inline">\n' +
                        '            <input type="text" id="weixin" lay-verify="required" autocomplete="off" class="layui-input">\n' +
                        '        </div>\n' +
                        '    </div>',
                        btn: ['确定', '取消'],
                        yes: function(index, layero){
                            var weixin = $('#weixin').val().trim();
                            if (weixin == '') {
                                layer.msg('请输入微信!',{
                                    icon: 2,
                                    time: 1000
                                });
                            } else {
                                var lindex = layer.load();
                                $.ajax({
                                    type:"post",
                                    url:"${pageContext.request.contextPath}/order/addOrder",
                                    async:true,
                                    dataType:"text",
                                    data:{weixin:weixin},
                                    success:function(data){
                                        layer.close(lindex);
                                        layer.msg('添加成功!', {
                                            icon: 1,
                                            time: 1000
                                        },function () {
                                            layer.close(index);
                                            // location.reload();
                                            table.reload('test', {
                                                url: '${pageContext.request.contextPath}/order/orderList'
                                            });
                                        });
                                    },
                                    error:function () {
                                        layer.close(lindex);
                                        layer.msg("服务器错误");
                                    }
                                });
                            }
                        },
                        btnAlign: 'c',
                        area: ['400px', '180px']
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('点击数据即可修改');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确定删除该订单吗', function(index){
                    //发异步删除数据
                    var lindex = layer.load();
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/order/delOrder",
                        async:true,
                        dataType:"text",
                        data:{id:data.id},
                        success:function(data){
                            layer.close(lindex);
                            obj.del();
                            layer.close(index);
                            layer.msg('已删除!', {
                                icon: 1,
                                time: 1000
                            });
                        },
                        error:function () {
                            layer.close(lindex);
                            layer.msg("服务器错误");
                        }
                    });
                });
            }
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var lindex = layer.load();
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/order/editOrder",
                async:true,
                dataType:"text",
                data:{id:data.id,weixin:value},
                success:function(data){
                    layer.close(lindex);
                    layer.msg('修改成功');
                },
                error:function () {
                    layer.close(lindex);
                    layer.msg("服务器错误");
                }
            });
        });
    });
</script>
</body>
</html>
