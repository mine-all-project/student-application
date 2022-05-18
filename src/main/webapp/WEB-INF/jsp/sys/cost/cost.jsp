<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>
    <link rel="stylesheet" href="/Y-Admin/layui/css/layui.css">
    <link rel="stylesheet" href="/Y-Admin/css/common.css">
    <link rel="stylesheet" href="/Y-Admin/layui/extend/dtree.css">
    <link rel="stylesheet" href="/Y-Admin/layui/extend/font/dtreefont.css">
    <!-- 图标 -->
    <link rel="stylesheet" href="/Y-Admin/static/CaoMei/style.css">
</head>

<body>

<div class="yadmin-body animated fadeIn">
    <div class="layui-col-md12">
        <div class="layui-form yadmin-search-area input">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">患者</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name"
                               placeholder="请输入关键词" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cellphone"
                               placeholder="请输入关键词" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">身份证</label>
                    <div class="layui-input-inline">
                        <input type="text" name="identityCard"
                               placeholder="请输入关键词" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">项目</label>
                    <div class="layui-input-inline">
                        <input type="text" name="projects"
                               placeholder="请输入关键词" autocomplete="off" class="layui-input">
                    </div>

                    <button lay-submit="" lay-filter="search"
                            class="layui-btn layui-btn-sm layui-btn-primary table-action">
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-col-md12">
        <table class="layui-hide" id="dataTable"></table>
    </div>
</div>

<div id="add" style="display: none; padding: 30px">
    <div class="layui-form layui-form-pane" lay-filter="add">
        <div class="layui-form-item">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required"
                       placeholder="请输入项目名称..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目类型</label>
            <div class="layui-input-block">
                <input type="text" name="types" required lay-verify="required"
                       placeholder="请输入项目类型..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">费用金额</label>
            <div class="layui-input-block">
                <input type="text" name="money" required lay-verify="required|number"
                       placeholder="请输入费用金额..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                        lay-submit lay-filter="add">确定</button>
            </div>
        </div>
    </div>
</div>
<div id="edit" style="display: none; padding: 30px">
    <div class="layui-form layui-form-pane" lay-filter="edit">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required"
                       autocomplete="off" class="layui-input id">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required"
                       placeholder="请输入项目名称..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目类型</label>
            <div class="layui-input-block">
                <input type="text" name="types" required lay-verify="required"
                       placeholder="请输入项目类型..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">费用金额</label>
            <div class="layui-input-block">
                <input type="text" name="money" required lay-verify="required|number"
                       placeholder="请输入费用金额..." autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                        lay-submit lay-filter="edit">保存编辑</button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="toolbar">
    <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add">
        <i class="layui-icon layui-icon-addition"></i>
        新增
    </button>
</script>

<script type="text/html" id="column-toolbar">
    <button type="button" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
        <i class="layui-icon layui-icon-edit" title="编辑"></i>
        编辑
    </button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
        <i class="layui-icon layui-icon-delete" title="删除"></i>
        删除
    </button>
</script>

<script src="/js/jquery-3.3.1.min.js" charset="utf-8"></script>
<script src="/js/ajax.js" charset="utf-8"></script>
<script src="/Y-Admin/layui/layui.js"></script>
<script src="/Y-Admin/js/common.js"></script>

<script>
    layui.config({
        base: '/Y-Admin/layui/extend/'
    }).use(['table', 'element', 'form'], function () {
        var table = layui.table;
        var form = layui.form;

        form.render('radio');

        table.render({
            elem: '#dataTable'
            , url: '/sys/cost/data'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , title: '数据表'
            , cols: [[
                {field: 'id', title: 'ID', width: 130, fixed: 'left', sort: true}
                , {field: 'name', title: '项目名称'}
                , {field: 'types', title: '项目类型'}
                , {field: 'money', title: '费用金额'}
                , {field: 'creatTime', title: '创建时间'}
                , {fixed: 'right', title: '操作', toolbar: '#column-toolbar', width: 170}
            ]]
            , page: true
        });

        //头工具栏事件
        table.on('toolbar', function (obj) {
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 1,
                        title: '添加',
                        content: $('#add'),
                        area: ['550px', '400px'],
                        cancel: function (index, layero) {
                            layer.close(index);
                        }
                    });
            }
        });

        //监听行工具事件
        table.on('tool', function (obj) {
                var data = obj.data;
                if (obj.event == 'del') {
                    layer.confirm('真的删除行么？', function (index) {
                        Ax.rest("/sys/cost/delete", data.id, function (data) {
                            obj.del();
                            layer.close(index);
                            layer.msg("删除成功");
                        })
                    });
                } else if (obj.event == 'edit') {
                    layer.open({
                        type: 1,
                        title: '编辑',
                        content: $('#edit'),
                        area: ['550px', '400px'],
                        success: function (layero, index) {
                            form.val('edit', data)
                        },
                        cancel: function (index, layero) {
                            layer.close(index);
                        }
                    });
                }
            }
        );

        // 添加
        form.on('submit(add)', function (data) {
            Ax.rest("/sys/cost/add", data.field, function (data) {
                form.val('add', {
                    "name": "",
                    "types": "",
                    "money": "",
                });
                layer.msg('添加成功');
                table.reload('dataTable'); //只重载数据
            })
        });

        // 编辑
        form.on('submit(edit)', function (data) {
            Ax.rest("/sys/cost/edit", data.field, function (data) {
                form.val('edit', data);
                layer.msg('编辑成功');
                table.reload('dataTable'); //只重载数据
            })
        });

        // 搜索
        form.on('submit(search)', function (data) {
            table.reload('dataTable', {
                where: data.field
            }); //只重载数据
        });

    });
</script>
</body>
</html>