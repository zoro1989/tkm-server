<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="config/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>提示</title>
    <!-- 公共需要先引入 -->
    <%@include file="../common/config/head.jsp"%>
    <link rel="stylesheet" href="${baseurl}css/page/first.css">
</head>
<body data-target="#one" data-spy="scroll">
<%@include file="../common/top.jsp"%>
<div class="main-wrapper">
	<div class="main-title">
        系统提示
    </div>
    <div class="row">
        <div class="col-md-12">
            <p>被拒绝的请求，你没有权限。请重新登录或者联系管理员！</p>
        </div>
    </div>
</div>
</body>
</html>