<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户欢迎界面</title>
    <link rel="stylesheet" href="../css/reset.css" />
    <link rel="stylesheet" href="../css/content.css" />
</head>
<body  marginwidth="0" marginheight="0">
    <div class="container">
    <div class="public-nav">您当前的位置：<a href="">管理首页</a>></div>
    <div class="public-content">
        <div class="public-content-header">
            <h3>网站主页面</h3>
        </div>
        <div class="public-content-cont">
            <h4><p style="width: 100%;text-align: center; padding: 50px 0; font-size: 16px; color: #FF0000;"><s:property value="#session.user_session.stuid"/> 用户！ 欢迎登陆您个人图书管理系统！</p></h4>
        </div>
    </div>
</div>
</body>
</html>
