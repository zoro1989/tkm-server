<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/config/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <!-- 公共需要先引入 -->
    <%@include file="../common/config/head.jsp"%>
    <link rel="stylesheet" href="http://open.itboy.net/itboy/js/itboy/app/supersized.css">
    <style>
        canvas{position: fixed; top: 0px; left: 0px; }
    </style>
</head>
<body class="login_body">

<div class="page-container" style="margin: 100px auto 0px;">
    <h1>注册新用户</h1>
    <form id="_form" action="" method="post">
        <input type="text" name="nickname" id="nickname" class="username input-container" placeholder="请输入姓名">
        <input type="text" name="username"  id="email" class="email input-container" placeholder="请输入邮件">
        <input type="password" name="password" id="password" class="password input-container" placeholder="请输入密码">
        <input type="password" id="re_password" class="re_password input-container"  placeholder="请再次输入密码">
        <div style="text-align: left; margin-left: 10px;" id="vcode">
            <input type="text" name="vcode" class="vcode input-container"  placeholder="验证码" style="width: 110px; margin-left: -8px; margin-right: 8px;">
            <img src="${baseurl}common/getVCode.tkm" />
        </div>
        <button type="button" class="register">注册</button>
        <button type="button" id="login" >登录</button>
        <div class="error"><span>+</span></div>
    </form>
    <input id="baseurl" type="hidden" value="${baseurl}" >
</div>

</body>
<script data-main="${baseurl}js/page/user/register" src="${baseurl}js/lib/require.js"></script>
</html>