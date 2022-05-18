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
    <link rel="stylesheet" href="/Y-Admin/layui/extend/formSelects-v4.css">
</head>
<body>
<div class="yadmin-body animated fadeIn">
    <div class="layui-form layui-form-pane" lay-filter="edit">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="text" name="id" value="${user.id}" required lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" placeholder="请输入新密码"
                       required lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="password2" placeholder="请输入确认密码"
                       required lay-verify="required"
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

<script src="/js/jquery-3.3.1.min.js" charset="utf-8"></script>
<script src="/js/ajax.js" charset="utf-8"></script>
<script src="/Y-Admin/layui/layui.js"></script>
<script src="/Y-Admin/js/common.js"></script>

<script>
    layui.config({
        base: '/Y-Admin/layui/extend/'
    }).extend({
        formSelects: 'formSelects-v4'
    }).use(['form', 'layer', 'formSelects', 'dtree', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , formSelects = layui.formSelects
            , dtree = layui.dtree
            , upload = layui.upload;

        form.on('submit(edit)', function (form) {
            if (form.field.password != form.field.password2) {
                layer.msg('两次密码输入不一致哦');
                return;
            }
            var user = {};
            user.id = form.field.id;
            user.password = form.field.password;
            Ax.rest("/nurse/user/personal/edit", user, function (data) {
                layer.msg("密码修改成功", {icon: 6});
            });
            return false;
        });

    });
</script>

</body>
</html>