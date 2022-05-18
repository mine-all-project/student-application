<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/Y-Admin/layui/css/layui.css">
    <link rel="stylesheet" media="screen" href="/css/login.css">
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
</head>
<body>

<div id="particles-js">
    <div class="login layui-form">
        <div class="login-top" style="margin-top: 80px; margin-bottom: 20px">
            注册
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/name.png"/></div>
            <div class="login-center-input">
                <input type="text" name="name" value="" placeholder="请输入姓名" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入姓名'" required  lay-verify="required"/>
                <div class="login-center-input-text">姓名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/name.png"/></div>
            <div class="login-center-input">
                <input type="text" name="username" value="" placeholder="请输入用户名" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入用户名'" required  lay-verify="required"/>
                <div class="login-center-input-text">用户名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/password.png"/></div>
            <div class="login-center-input">
                <input type="password" name="password" value="" placeholder="请输入密码" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入密码'" required  lay-verify="required"/>
                <div class="login-center-input-text">密码</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/name.png"/></div>
            <div class="login-center-input">
                <select name="role" lay-verify="required">
                    <option value="">请选择身份</option>
                    <option value="0">护士</option>
                    <option value="1">医生</option>
                </select>
                <div class="login-center-input-text">身份</div>
            </div>
        </div>
        <div class="login-button" lay-submit lay-filter="register">
            注册
        </div>
        <div style="text-align: center; margin-top: 15px">
            <a href="/login.html">
                前往登录
            </a>
        </div>
    </div>
    <div class="sk-rotating-plane"></div>
</div>

<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/ajax.js"></script>
<script type="text/javascript" src="/Y-Admin/layui/layui.all.js"></script>
<script type="text/javascript" src="/js/particles.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form;

        form.render('select');

        //监听提交
        form.on('submit(register)', function(formData){
            var user = formData.field;
            Ax.rest("/register", user, function (data) {
                layer.msg('注册成功');
            })
        });
    });
</script>
</body>
</html>