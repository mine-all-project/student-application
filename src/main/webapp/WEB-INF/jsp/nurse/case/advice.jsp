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
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title"
                               placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>

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
                </div>
                <div class="layui-inline">
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

<script type="text/html" id="column-toolbar">

    <button type="button" class="layui-btn layui-btn-xs" lay-event="item">
        <i class="layui-icon layui-icon-screen-full" title="详情"></i>
        详情
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
            , url: '/nurse/case/data'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , title: '数据表'
            , where: {
                casesType: 0
            }
            , cols: [[
                {field: 'id', title: 'ID', width: 130, fixed: 'left', sort: true}
                , {field: 'title', title: '医嘱标题'}
                , {
                    field: 'name', title: '患者', templet: function (data) {
                        return data.user.name;
                    }
                }
                , {
                    field: 'cellphone', title: '联系电话', templet: function (data) {
                        return data.user.cellphone;
                    }
                }
                , {
                    field: 'identityCard', title: '身份证', templet: function (data) {
                        return data.user.identityCard;
                    }
                }
                , {field: 'creatTime', title: '创建时间'}
                , {fixed: 'right', title: '操作', toolbar: '#column-toolbar', width: 80}
            ]]
            , page: true
        });

        //监听行工具事件
        table.on('tool', function (obj) {
                var data = obj.data;
                if (obj.event === 'item') {
                    location.href = "/nurse/case/advice/item.html?id=" + data.id;
                }
            }
        );

        // 搜索
        form.on('submit(search)', function (data) {
            data.field.casesType = 0;
            table.reload('dataTable', {
                where: data.field
            }); //只重载数据
        });

    });
</script>
</body>
</html>
