<%@ page import="com.idleCultivate.rms.support.util.DataDictUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>编辑地图</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form:form class="layui-form" method="post" modelAttribute="wowMap">
            <form:hidden path="id"/>
            <div class="layui-form-item">
                <form:label path="name" class="layui-form-label"> <span class="x-red">*</span>地图名称 </form:label>
                <div class="layui-input-inline">
                    <form:input path="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="occupy" class="layui-form-label"> <span class="x-red">*</span>地图归属 </form:label>
                <div class="layui-input-inline">
                    <form:select path="occupy" items="<%= DataDictUtil.occupy() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="description" class="layui-form-label"> 地图描述 </form:label>
                <div class="layui-input-inline">
                    <form:textarea path="description" class="layui-textarea"></form:textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-filter="edit" lay-submit type="button">修改</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/js/helper.js?v=0531"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/map/edit.js?v=0510"></script>
</body>
</html>
