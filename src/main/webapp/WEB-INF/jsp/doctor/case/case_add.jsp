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
    <div class="layui-form layui-form-pane" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">病例标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" required lay-verify="required"
                       placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">患者</label>
            <div class="layui-input-block">
                <select name="userId" lay-verify="required">
                    <option value="">请选择患者</option>
                    <c:forEach items="${userList}" var="item">
                        <option value="${item.id}">${item.name} - [身份证：${item.identityCard}]</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">入院诊断</label>
            <div class="layui-input-block">
                <textarea type="text" name="startHospitalized" required lay-verify="required"
                          placeholder="请输入入院诊断" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">入院病情</label>
            <div class="layui-input-block">
                <textarea type="text" name="startReason" required lay-verify="required"
                          placeholder="请输入入院病情" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">输血信息</label>
            <div class="layui-input-block">
                <input type="text" name="bloodType" required lay-verify="required"
                       placeholder="请输入输血信息" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">过敏记录</label>
            <div class="layui-input-block">
                <textarea type="text" name="record" required lay-verify="required"
                          placeholder="请输入过敏记录" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">手术记录</label>
            <div class="layui-input-block">
                <textarea type="text" name="surgicalRecord" required lay-verify="required"
                          placeholder="请输入手术记录" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">出院诊断</label>
            <div class="layui-input-block">
                <textarea type="text" name="endHospitalized" required lay-verify="required"
                          placeholder="请输入出院诊断" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">出院病情</label>
            <div class="layui-input-block">
                <textarea type="text" name="endReason" required lay-verify="required"
                          placeholder="请输入出院病情" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">签名信息</label>
            <div class="layui-input-block">
                <input type="text" name="autograph" required lay-verify="required"
                       placeholder="请输入签名信息" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 30px; text-align: right">
            <div class="layui-input-block" style="margin-left: 0px">
                <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="add">提交</button>
            </div>
        </div>
    </div>
</div>

<div id="dialog" style="padding:30px; display: none; text-align: center; background-color:#1e88e5; color: #ffffff">
    <div class="layui-col-md12">
        <strong>添加成功</strong>
    </div>
</div>

<script src="/js/jquery-3.3.1.min.js" charset="utf-8"></script>
<script src="/js/ajax.js" charset="utf-8"></script>
<script src="/Y-Admin/layui/layui.js"></script>
<script src="/Y-Admin/js/common.js"></script>
<script type="text/javascript" src="/wangEditor/release/wangEditor.js"></script>

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

        // 发布
        form.on('submit(add)', function (formData) {
            var data = formData.field;
            data.casesType = 1;
            if (data.title.length > 100) {
                layer.msg('标题不可超过100字数');
                return;
            }
            Ax.rest("/doctor/case/add", data, function (data) {
                layer.open({
                    type: 1,
                    title: false,
                    area: ['355px', '140px'],
                    content: $('#dialog'),
                    cancel: function (index, layero) {
                        location.reload();
                    }
                });
            })
        });

    });
</script>

</body>
</html>
