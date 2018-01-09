<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" media="all" href="../css/login.css" />
    <link type="text/css" rel="stylesheet" media="all" href="../css/reset.css" />
    <title>注册页面</title>
</head>
<body>
<div class="page">
    <div class="loginwarrp">
        <div class="logo">注册账号</div>
        <div class="login_form">
            <form id="Login" name="Login" method="post" onsubmit="" action="/user/register">
                <li class="login-item">
                    <span>用  户  名：</span>
                    <input type="text" name="user.stuid" class="login_input" placeholder="学生号或者教职工号">
                </li>
                <li class="login-item">
                    <span>密   　码 ：</span>
                    <input type="password" name="password" class="login_input" placeholder="不低于6位数字、下划线或字母">
                </li>
                <li class="login-item">
                    <span>确认密码：</span>
                    <input type="password" name="user.password" class="login_input" placeholder="再次输入密码">
                </li>

                <li class="login-sub">
                    <input type="submit" name="Submit" value="注册" />
                </li>
            </form>
        </div>
    </div>
</div>

</body>
</html>
