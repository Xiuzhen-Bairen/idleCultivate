<%@ page import="com.idlewow.admin.model.SysAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("loginuser") == null) {
        response.sendRedirect("login.html");
        return;
    }

    SysAdmin sysAdmin = (SysAdmin)session.getAttribute("loginuser");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>