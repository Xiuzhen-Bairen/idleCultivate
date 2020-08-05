<%@ page import="com.idlewow.support.util.DataDictUtil" %>
<%@ page import="com.idlewow.game.GameWorld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute(GameWorld.SK_User) == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String path = request.getContextPath();
%>
<html>
<head>
    <title>创建角色</title>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
</head>
<body>
<form id="form-createChar" method="post">
    <div>
        <label>昵称：</label>
        <input type="text" name="name"/>
    </div>
    <div>
        <label>阵营：</label>
        <select name="faction">
            <option value="">请选择阵营</option>
            <option value="10111">联盟</option>
            <option value="10112">部落</option>
        </select>
    </div>
    <div>
        <label>种族：</label>
        <% request.setAttribute("raceMap", DataDictUtil.race()); %>
        <select name="race">
            <option value="">请选择种族</option>
            <c:forEach items="${raceMap}" var="race">
                <option value="${race.key}">${race.value}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label>职业：</label>
        <% request.setAttribute("jobMap", DataDictUtil.job()); %>
        <select name="job">
            <option value="">请选择职业</option>
            <c:forEach items="${jobMap}" var="job">
                <option value="${job.key}">${job.value}</option>
            </c:forEach>
        </select>
    </div>
    <button type="button" onclick="createChar();">确定</button>
    <button type="button" onclick="history.go(-1);">取消</button>
</form>
<script type="text/javascript" src="<%=path%>/js/wow/game/createChar.js?v=0719"></script>
</body>
</html>
