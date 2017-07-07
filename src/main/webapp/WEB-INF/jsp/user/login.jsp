<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/config/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <!-- 公共需要先引入 -->
    <%@include file="../common/config/head.jsp"%>
    <link rel="stylesheet" href="http://open.itboy.net/itboy/js/itboy/app/supersized.css">
    <style>
        canvas{position: fixed; top: 0px; left: 0px; }
    </style>
</head>
<body class="login_body">

    <div class="page-container">
        <h1>Login</h1>
        <form id="_form" action="" method="post">
            <input type="text" name="username" class="username input-container" placeholder="请输入账号">
            <input type="password" name="password" class="password input-container" placeholder="请输入密码">
            <div style="text-align: left; margin-left: 10px;">
                <label><input type="checkbox" checked="checked"  name="rememberMe" class="rememberMe">记住我</label>
            </div>
            <button type="button" id="login">登录</button>
            <button type="button" id="register" class="register">Register</button>
            <div class="error"><span>+</span></div>
        </form>
        <input id="baseurl" type="hidden" value="${baseurl}" >
    </div>
</body>


<script data-main="${baseurl}js/page/user/login" src="${baseurl}js/lib/require.js"></script>
</html>