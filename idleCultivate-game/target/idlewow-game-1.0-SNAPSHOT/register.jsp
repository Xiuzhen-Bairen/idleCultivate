<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<body>
<form id="form-register" method="post">
    <div>
        <label>账号：</label>
        <input type="text" name="username" id="username"/>
    </div>
    <div>
        <label>密码：</label>
        <input type="password" name="password" id="password"/>
    </div>
    <div>
        <label>确认密码：</label>
        <input type="password" name="password2" id="password2"/>
    </div>
    <div>
        <button type="button" onclick="register();">注册</button>
        <button type="button" onclick="history.go(-1);">返回</button>
    </div>
</form>
<script type="text/javascript" src="/js/wow/register.js"></script>
</body>
</html>
