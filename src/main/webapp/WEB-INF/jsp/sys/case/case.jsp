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
                    <label class="layui-form-label">匿名</label>
                    <div class="layui-input-inline">
                        <select name="status">
                            <option value="">请选择匿名状态</option>
                            <option value="0">未匿名</option>
                            <option value="1">匿名</option>
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

<script type="text/html" id="column-toolbar">
    <button type="button" class="layui-btn layui-btn-xs" lay-event="item">
        <i class="layui-icon layui-icon-screen-full" title="详情"></i>
        详情
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
            , url: '/sys/case/data'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , title: '数据表'
            , where: {
                casesType: 1
            }
            , cols: [[
                {field: 'id', title: 'ID', width: 130, fixed: 'left', sort: true}
                , {field: 'title', title: '病例标题'}
                , {field: 'name', title: '患者', templet: function (data) {
                        return data.user.name;
                    }}
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
                , {field: 'status', title: '是否匿名', templet: function (data) {
                        if (data.status == 0) {
                            return "<span class='layui-badge layui-bg-green'>未匿名</span>";
                        } else {
                            return '<span class="layui-badge">匿名</span>';
                        }
                    }}
                , {field: 'creatTime', title: '创建时间'}
                , {fixed: 'right', title: '操作', toolbar: '#column-toolbar', width: 170}
            ]]
            , page: true
        });

        //监听行工具事件
        table.on('tool', function (obj) {
                var data = obj.data;
                if (obj.event == 'del') {
                    layer.confirm('真的删除行么？', function (index) {
                        Ax.rest("/sys/case/delete", data.id, function (data) {
                            obj.del();
                            layer.close(index);
                            layer.msg("删除成功");
                        })
                    });
                } else if (obj.event === 'item') {
                    location.href = "/sys/case/case/item.html?id=" + data.id;
                }
            }
        );

        // 搜索
        form.on('submit(search)', function (data) {
            data.field.status = 1;
            table.reload('dataTable', {
                where: data.field
            }); //只重载数据
        });

    });
</script>
</body>
</html>