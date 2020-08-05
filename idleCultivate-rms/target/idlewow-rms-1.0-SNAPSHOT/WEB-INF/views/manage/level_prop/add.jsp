<%@ page import="com.idlewow.rms.support.util.DataDictUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>添加配置</title>
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
                <label class="layui-form-label"> <span class="x-red">*</span>职业 </label>
                <div class="layui-input-inline">
                    <% request.setAttribute("jobMap", DataDictUtil.job()); %>
                    <select name="job">
                        <option value="">请选择职业</option>
                        <c:forEach items="${jobMap}" var="job">
                            <option value="${job.key}">${job.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <input type="text" name="level" required lay-verify="required"
                           autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生命值</label>
                <div class="layui-input-inline">
                    <input type="text" name="hp" required lay-verify="required"
                           autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">力量</label>
                <div class="layui-input-inline">
                    <input type="text" name="strength" required lay-verify="required"
                           autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">敏捷</label>
                <div class="layui-input-inline">
                    <input type="text" name="agility" required lay-verify="required"
                           autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">智力</label>
                <div class="layui-input-inline">
                    <input type="text" name="intellect" required lay-verify="required"
                           autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">耐力</label>
                <div class="layui-input-inline">
                    <input type="text" name="stamina" required lay-verify="required"
                           autocomplete="off" class="layui-input">
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
<script type="text/javascript" src="<%=path%>/js/helper.js"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/level_prop/add.js?v=1550"></script>
</body>
</html>
