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
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name"
                               placeholder="请输入姓名" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">电话</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cellphone"
                               placeholder="请输入学生电话" autocomplete="off" class="layui-input">
                    </div>

                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-inline">
                        <input type="text" name="content"
                               placeholder="请输入留言内容" autocomplete="off" class="layui-input">
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

<div id="edit" style="display: none; padding: 30px">
    <div class="layui-form layui-form-pane" lay-filter="edit">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required"
                       autocomplete="off" class="layui-input id">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">回复内容</label>
            <div class="layui-input-block">
                <textarea type="text" name="replyCon" required lay-verify="required"
                          placeholder="请输入回复内容" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: right">
                <button class="layui-btn layui-btn-sm layui-btn-normal"
                        lay-submit lay-filter="edit">提交
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="column-toolbar">
    <button type="button" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
        <i class="layui-icon layui-icon-edit" title="回复"></i>
        回复
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
            , url: '/sys/leaves/data'
            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
            , title: '数据表'
            , cols: [[
                {field: 'id', title: 'ID', width: 130, fixed: 'left', sort: true}
                , {
                    field: 'name', title: '留言用户', templet: function (data) {
                        return data.user.name;
                    }
                }
                , {
                    field: 'cellphone', title: '联系电话', templet: function (data) {
                        return data.user.cellphone;
                    }
                }
                , {field: 'content', title: '留言内容'}
                , {field: 'leavesTime', title: '留言时间'}
                , {field: 'replyCon', title: '回复内容'}
                , {field: 'replyTime', title: '回复时间'}
                , {fixed: 'right', title: '操作', toolbar: '#column-toolbar', width: 170}
            ]]
            , page: true
        });

        //监听行工具事件
        table.on('tool', function (obj) {
                var data = obj.data;
                if (obj.event == 'del') {
                    layer.confirm('真的删除行么？', function (index) {
                        Ax.rest("/sys/leaves/delete", data.id, function (data) {
                            obj.del();
                            layer.close(index);
                            layer.msg("删除成功");
                        })
                    });
                } else if (obj.event == 'edit') {
                    layer.open({
                        type: 1,
                        title: '留言回复',
                        content: $('#edit'),
                        area: ['550px', '370px'],
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

        // 回复
        form.on('submit(edit)', function (data) {
            Ax.rest("/sys/leaves/edit", data.field, function (data) {
                form.val('edit', data);
                layer.msg('回复成功');
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