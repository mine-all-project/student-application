<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="/css/login.css">
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
</head>
<body>

<div id="particles-js">
    <div class="login layui-form">
        <div class="login-top">
            登录
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/name.png"/></div>
            <div class="login-center-input">
                <input type="text" name="username" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入您的用户名'" required  lay-verify="required"/>
                <div class="login-center-input-text">用户名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/images/password.png"/></div>
            <div class="login-center-input">
                <input type="password" name="password" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''"
                       onblur="this.placeholder='请输入您的密码'" required  lay-verify="required"/>
                <div class="login-center-input-text">密码</div>
            </div>
        </div>
        <div class="login-button" lay-submit lay-filter="login">
            登陆
        </div>
        <div style="text-align: center; margin-top: 15px">
            <a href="/register.html">前往注册</a>
        </div>
    </div>
    <div class="sk-rotating-plane"></div>
</div>

<script type="text/javascript" src="/Y-Admin/layui/layui.all.js"></script>
<script type="text/javascript" src="/js/particles.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/ajax.js"></script>

<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(login)', function(data){
            var user = data.field;
            Ax.rest("/login", user, function (data) {
                if (data.role == 1) location.href = "/nurse/index.html";
                if (data.role == 2) location.href = "/doctor/index.html";
                if (data.role == 3) location.href = "/sys/index.html";
            })
        });
    });
</script>
</body>
</html>