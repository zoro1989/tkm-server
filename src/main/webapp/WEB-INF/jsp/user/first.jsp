<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/config/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <!-- 公共需要先引入 -->
    <%@include file="../common/config/head.jsp"%>
    <link rel="stylesheet" href="${baseurl}css/page/first.css">
</head>
<body data-target="#one" data-spy="scroll">
    <%@include file="../common/top.jsp"%>
    <div class="main-wrapper">
    	<div class="main-title">
        	个人资料
    	</div>
        <div class="row">
            <div class="col-md-12">
              <iframe class="loadframe" name="mainframe" src="${baseurl}static/index.html#personal-data" style="width:100%;height: calc(100vh - 60px);" frameborder="no" border="0"></iframe>
            </div>
        </div>
    </div>
</body>

<script data-main="${baseurl}js/page/user/first" src="${baseurl}js/lib/require.js"></script>
</html>