<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
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
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name"
                               placeholder="请输入名称" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cellphone"
                               placeholder="请输入手机号" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <select name="sex">
                            <option value="">全部</option>
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
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
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="true" title="男">
                <input type="radio" name="sex" value="false" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="text" name="age" required lay-verify="required|number" placeholder="请输入年龄"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="cellphone" required lay-verify="required|phone" placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="identityCard" required lay-verify="required|identity" placeholder="请输入身份证"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" required lay-verify="required" placeholder="请输入地址"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="add">确定</button>
            </div>
        </div>
    </div>
</div>
<div id="edit" style="display: none; padding: 30px">
    <div class="layui-form layui-form-pane" lay-filter="edit">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="text" name="id" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" disabled style="cursor: not-allowed"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="true" title="男" checked>
                <input type="radio" name="sex" value="false" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="text" name="age" required lay-verify="required|number" placeholder="请输入年龄"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="cellphone" required lay-verify="required|phone" placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="identityCard" required lay-verify="required|identity" placeholder="请输入身份证"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" required lay-verify="required" placeholder="请输入地址"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="edit">保存编辑</button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="toolbar">

    <button type="button" class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add">
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
            , url: '/sys/user/data'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , title: '用户数据表'
            , where: {
                role: 2
            }
            , cols: [[
                {field: 'id', title: 'ID', width: 110, fixed: 'left', sort: true}
                , {field: 'username', title: '用户名'}
                , {field: 'name', title: '姓名'}
                , {
                    field: 'sex', title: '性别', width: 100, templet: function (data) {
                        if (data.sex) {
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                }
                , {field: 'age', title: '年龄'}
                , {field: 'cellphone', title: '手机号'}
                , {field: 'identityCard', title: '身份证'}
                , {field: 'address', title: '地址'}
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
                        area: ['550px', '500px'],
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
                        Ax.rest("/sys/user/delete", data.id, function (data) {
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
                        area: ['550px', '500px'],
                        success: function (layero, index) {
                            if (data.sex == true) data.sex = "true";
                            if (data.sex == false) data.sex = "false";
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
            data.field.role = 2;
            Ax.rest("/sys/user/add", data.field, function (data) {
                form.val('add', {
                    "username": "",
                    "name": "",
                    "password": "",
                    "cellphone": "",
                    "age": "",
                    "address": "",
                    "identityCard": "",
                });
                layer.msg("添加成功", {icon: 6});
                table.reload('dataTable'); //只重载数据
            })
        });

        // 编辑
        form.on('submit(edit)', function (data) {
            Ax.rest("/sys/user/edit", data.field, function (data) {
                layer.msg("编辑成功", {icon: 6});
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