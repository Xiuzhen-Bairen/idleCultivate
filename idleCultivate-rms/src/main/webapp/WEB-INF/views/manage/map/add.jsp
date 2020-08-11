<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.idleCultivate.rms.support.util.DataDictUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>添加地图</title>
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
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label for="name" class="layui-form-label"> <span class="x-red">*</span>地图名称 </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="occupy" class="layui-form-label"> <span class="x-red">*</span>地图归属 </label>
                <div class="layui-input-inline">
                    <% request.setAttribute("occupyMap", DataDictUtil.occupy()); %>
                    <select name="occupy" id="occupy">
                        <option value="">请选择地图归属</option>
                        <c:forEach items="${occupyMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="description" class="layui-form-label"> 地图描述 </label>
                <div class="layui-input-inline">
                    <textarea name="description" id="description" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-filter="add" lay-submit type="button">添加</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/js/helper.js?v=0531"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/map/add.js?v=0531"></script>
</body>
</html>
