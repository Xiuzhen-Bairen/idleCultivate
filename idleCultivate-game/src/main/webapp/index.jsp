<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>挂机魔兽</title>
    <link rel="stylesheet" href="<%=path%>/css/wow/base.css?v=0714">
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <style>
        .input-wow {
            background: rgb(80, 80, 80);
            border: gray;
            border-radius: 4px;
            color: white;
        }
    </style>
</head>
<body>
<div class="bg bg-index"></div>
<div style="text-align:center; width:100%;position:relative;top:58%;">
    <form id="form-login" method="post">
        <div style="margin: 10px;">
            <input type="text" name="username" placeholder="请输入账号" class="input-wow"/>
        </div>
        <div style="margin-top: 50px;">
            <input type="password" name="password" placeholder="请输入密码" class="input-wow"/>
        </div>
        <div style="margin: 20px;">
            <button type="button" class="btn-wow" onclick="login();">登 陆</button>
            <button type="button" class="btn-wow" onclick="location.href='/register.jsp';">注 册</button>
        </div>
    </form>
</div>
<script type="text/javascript" src="<%=path%>/js/wow/index.js?v=0714"></script>
</body>
</html>
