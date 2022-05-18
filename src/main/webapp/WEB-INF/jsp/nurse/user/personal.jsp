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
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" value="${user.username}"
                       disabled style="cursor: not-allowed"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${user.name}"
                       required lay-verify="required"
                       placeholder="请输入姓名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="position: relative;">
            <div style="display: inline-block">
                <img src="${user.headImg}" class="img" width="200px" height="200px">
            </div>
            <button type="button" class="layui-btn layui-btn-primary" id="images"
                    style="position: absolute; bottom: 0px; left: 215px">
                <i class="layui-icon">&#xe67c;</i>上传头像
            </button>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="true" title="男" ${user.sex == 'true'? 'checked' : ''}>
                <input type="radio" name="sex" value="false" title="女" ${user.sex == 'false'? 'checked' : ''}>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄</label>
            <div class="layui-input-block">
                <input type="text" name="age" value="${user.age}" required lay-verify="required|number" placeholder="请输入年龄"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="cellphone" value="${user.cellphone}"
                       required lay-verify="required|phone"
                       placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="identityCard" value="${user.identityCard}" required lay-verify="required|identity" placeholder="请输入身份证"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="address" value="${user.address}" required lay-verify="required" placeholder="请输入地址"
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

        // 图片上传
        upload.render({
            elem: '#images'
            , url: '/uploadImgFile'
            , exts: 'png|jpeg|jpg'
            , accept: 'file'
            , done: function (res) {
                if (res.code == 0) {
                    $('.img').attr('src', res.data);
                }
            }
            , error: function (res) {
                layer.msg('上传失败：' + res.message);
            }
        });

        form.on('submit(edit)', function (form) {
            form.field.headImg = $('.img').attr('src');
            Ax.rest("/nurse/user/personal/edit", form.field, function (data) {
                $('.headImg').attr('src', $('.img').attr('src'));
                layer.msg("编辑成功", {icon: 6});
            });
            return false;
        });

    });
</script>

</body>
</html>