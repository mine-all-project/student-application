<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>首页</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/Y-Admin/favicon.ico">
    <link rel="icon" type="image/png" sizes="32x32" href="/Y-Admin/favicon.ico">
    <link rel="stylesheet" href="/Y-Admin/layui/css/layui.css">
    <link rel="stylesheet" href="/Y-Admin/css/yadmin.css">
    <link rel="stylesheet" href="/Y-Admin/css/common.css">
    <link rel="stylesheet" href="/Y-Admin/static/remixicon/fonts/remixicon.css">
    <link rel="stylesheet" href="/Y-Admin/css/index.css">
</head>

<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <div class="layui-header">
        <!-- logo 区域 -->
        <div class="layui-logo">
            <img src="/Y-Admin/images/security.png" alt="logo" style="margin-left: -5px;"/>
            <cite>
                病例电子化匿名系统&emsp;
            </cite>
        </div>

        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item" lay-unselect>
                <a lay-event="flexible" title="侧边伸缩">
                    <i class="layui-icon layui-icon-shrink-right"></i>
                </a>
            </li>
            <!-- 面包屑 -->
            <span class="layui-breadcrumb layui-anim layui-anim-up">
                <a><cite>首页</cite></a>
            </span>
        </ul>

        <!-- 头像区域 -->
        <ul class="layui-nav layui-layout-right" lay-filter="lay-nav" style="margin-right: 30px">

            <li class="layui-nav-item feature-items">
                <a id="screenFull" lay-event="screenFull" title="全屏">
                    <i class="ri-fullscreen-line"></i>
                </a>
                <a id="screenRestore" lay-event="screenRestore" title="退出全屏" style="display: none;">
                    <i class="ri-fullscreen-exit-line"></i>
                </a>
            </li>

            <li class="layui-nav-item">
                <a>
                    <img src="${user.headImg}" class="layui-nav-img headImg" alt="头像" style="margin-right: 5px">
                    <cite>${user.name}</cite>
                    <span class="layui-nav-more"></span>
                </a>
                <dl class="layui-nav-child">
                    <dd lay-unselect>
                        <a style="cursor: pointer;" onclick="logOut()">退出</a>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>

    <!-- 左侧导航区域 -->
    <div class="layui-side ">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="lay-nav" lay-accordion="true">
                <li class="layui-nav-item">
                    <a lay-url="/doctor/index.html" lay-id="home">
                        <i class="ri-home-8-line"></i>&emsp;<cite>首页</cite>
                    </a>
                </li>

                <li class="layui-nav-item">
                    <a lay-id="#" lay-url="#">
                        <i class="ri-settings-2-fill"></i>&emsp;<cite>个人中心</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a lay-id="/doctor/user/personal.html" lay-url="/doctor/user/personal.html">
                                <cite>个人资料</cite>
                            </a>
                        </dd>
                        <dd>
                            <a lay-id="/doctor/user/password.html" lay-url="/doctor/user/password.html">
                                <cite>重置密码</cite>
                            </a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a lay-id="#" lay-url="#">
                        <i class="ri-user-line"></i>&emsp;<cite>患者管理</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a lay-id="/doctor/user/user.html" lay-url="/doctor/user/user.html">
                                <cite>患者管理</cite>
                            </a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a lay-id="#" lay-url="#">
                        <i class="ri-radar-line"></i>&emsp;<cite>基础管理</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a lay-id="/doctor/leaves/manage.html" lay-url="/doctor/leaves/manage.html">
                                <cite>留言反馈</cite>
                            </a>
                        </dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a lay-id="#" lay-url="#">
                        <i class="ri-landscape-fill"></i>&emsp;<cite>病例管理</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a lay-id="/doctor/case/advice.html" lay-url="/doctor/case/advice.html">
                                <cite>医嘱管理</cite>
                            </a>
                        </dd>
                        <dd>
                            <a lay-id="/doctor/case/case.html" lay-url="/doctor/case/case.html">
                                <cite>电子病历管理</cite>
                            </a>
                        </dd>
                        <dd>
                            <a lay-id="/doctor/case/inspect.html" lay-url="/doctor/case/inspect.html">
                                <cite>检查报告管理</cite>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>

        </div>
    </div>

    <div class="layui-body">
        <div class="layui-pagetabs">
            <div class="layui-icon admin-tabs-control layui-icon-refresh-3" lay-event="refresh"></div>
            <div class="layui-tab" lay-unauto lay-allowclose="true" lay-filter="lay-tab">
                <ul class="layui-tab-title">
                    <li lay-id="home" lay-url="/sys/home.html" class="layui-this">
                        <!-- <i class="ri-home-heart-line ri-xl"></i> -->
                        <i class="ri-home-8-line ri-xl"></i>
                    </li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src="/doctor/home.html" class="layui-iframe"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 底部固定区域 -->
    <div class="layui-footer">
        @ 2022 - 病例电子化匿名系统
    </div>

    <!-- 移动端遮罩 -->
    <div class="site-mobile-shade"></div>
</div>
<!-- layui JS -->
<script src="/Y-Admin/layui/layui.js"></script>
<!-- jQuery JS -->
<script src="/Y-Admin/js/jquery.js"></script>
<!-- 公共JS -->
<script src="/Y-Admin/js/common.js"></script>
<script src="/Y-Admin/js/yadmin.js"></script>

<script>
    function logOut() {
        sessionStorage.setItem("tabs", null);
        location.href = "/login.out";
    }
</script>
</body>

</html>