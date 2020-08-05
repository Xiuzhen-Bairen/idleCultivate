<%@ page import="com.idleCultivate.game.GameWorld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute(GameWorld.SK_User) == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>