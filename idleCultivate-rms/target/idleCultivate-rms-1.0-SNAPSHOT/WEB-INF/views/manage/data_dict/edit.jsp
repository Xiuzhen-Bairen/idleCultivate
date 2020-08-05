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
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form:form class="layui-form" method="post" modelAttribute="dataDict">
            <form:hidden path="id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">编码</label>
                <div class="layui-input-inline">
                    <form:input path="code" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="parentCode" class="layui-form-label">父编码</form:label>
                <div class="layui-input-inline">
                    <form:input path="parentCode" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="value" class="layui-form-label"> <span class="x-red">*</span>值</form:label>
                <div class="layui-input-inline">
                    <form:input path="value" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="remark" class="layui-form-label"> <span class="x-red"></span>备注</form:label>
                <div class="layui-input-inline">
                    <form:input path="remark" autocomplete="off" class="layui-input"/>
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
<script type="text/javascript" src="<%=path%>/js/wow/manage/data_dict/edit.js?v=0510"></script>
</body>
</html>
