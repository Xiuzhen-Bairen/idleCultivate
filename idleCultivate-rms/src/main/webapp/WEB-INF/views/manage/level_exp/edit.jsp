<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>编辑配置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form:form class="layui-form" method="post" modelAttribute="levelExp">
            <form:hidden path="id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <form:input path="level" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="exp" class="layui-form-label"> <span class="x-red">*</span>经验</form:label>
                <div class="layui-input-inline">
                    <form:input path="exp" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="exp_speed" class="layui-form-label"> <span class="x-red">*</span>经验获取速度</form:label>
                <div class="layui-input-inline">
                    <form:input path="exp_speed" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
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
<script type="text/javascript" src="<%=path%>/js/helper.js?v=06011"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/level_exp/edit.js?v=0510"></script>
</body>
</html>
