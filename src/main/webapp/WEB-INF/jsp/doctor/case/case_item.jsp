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

    <style>
        .content {
            padding: 15px;
            margin-bottom: 20px;
        }

        .equipment {
            padding: 15px;
            border-bottom: 1px solid #e2e2e2;
        }

        .box-s {
            background-color: #ffffff;
            box-shadow: 1px 1px 5px 3px #e2e2e2;
            padding: 10px 15px;
            margin: 20px 0px;
            color: #009688;
        }

        i {
            margin-right: 10px;
        }

        blockquote {
            display: block;
            border-left: 8px solid #d0e5f2;
            padding: 5px 10px;
            margin: 10px 0;
            line-height: 1.4;
            font-size: 100%;
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<div class="yadmin-body animated fadeIn">
    <div class="layui-col-md12" id="wrap">
        <div>
            <button class="layui-btn layui-btn-sm" onclick="printChecklist()">打印</button>
        </div>
        <div class="title" style="padding: 15px; border-bottom: 1px solid #e2e2e2">
            <h2><i class="layui-icon layui-icon-note"></i>${cases.title}</h2>
        </div>
        <div>
            <table class="layui-table">
                <tbody>
                <tr>
                    <td style="width: 200px;">患者姓名</td>
                    <td>${cases.user.name}</td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>${cases.user.sex == true?'男':'女'}</td>
                </tr>
                <tr>
                    <td>年龄</td>
                    <td>${cases.user.age}</td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td>${cases.user.cellphone}</td>
                </tr>
                <tr>
                    <td>身份证</td>
                    <td>${cases.user.identityCard}</td>
                </tr>
                <tr>
                    <td>地址</td>
                    <td>${cases.user.address}</td>
                </tr>
                <tr>
                    <td>入院诊断</td>
                    <td>${cases.startHospitalized}</td>
                </tr>
                <tr>
                    <td>入院病情</td>
                    <td>${cases.startReason}</td>
                </tr>
                <tr>
                    <td>输血信息</td>
                    <td>${cases.bloodType}</td>
                </tr>
                <tr>
                    <td>过敏记录</td>
                    <td>${cases.record}</td>
                </tr>
                <tr>
                    <td>手术记录</td>
                    <td>${cases.surgicalRecord}</td>
                </tr>
                <tr>
                    <td>出院诊断</td>
                    <td>${cases.endHospitalized}</td>
                </tr>
                <tr>
                    <td>出院病情</td>
                    <td>${cases.endReason}</td>
                </tr>
                <tr>
                    <td>签名信息</td>
                    <td>${cases.autograph}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="/js/jquery-3.3.1.min.js" charset="utf-8"></script>
<script src="/js/ajax.js" charset="utf-8"></script>
<script src="/Y-Admin/layui/layui.js"></script>
<script src="/Y-Admin/js/common.js"></script>
<script type="text/javascript" src="/wangEditor/release/wangEditor.js"></script>
<script type="text/javascript" src="/js/Print.js"></script>

<script>
    function printChecklist() {
        Print('#wrap', {
            onStart: function () {
                console.log('onStart', new Date())
            },
            onEnd: function () {
                console.log('onEnd', new Date())
            }
        })
    }
</script>

</body>
</html>