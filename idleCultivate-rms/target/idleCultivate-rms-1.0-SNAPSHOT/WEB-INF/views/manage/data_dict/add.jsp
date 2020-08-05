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
                <label class="layui-form-label">编码</label>
                <div class="layui-input-inline">
                    <input type="text" name="code" required lay-verify="required" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">父编码</label>
                <div class="layui-input-inline">
                    <input type="text" name="parentCode" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">值</label>
                <div class="layui-input-inline">
                    <input type="text" name="value" required lay-verify="required" autocomplete="off"
                           class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-inline">
                    <input type="text" name="remark" autocomplete="off" class="layui-input"/>
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
<script type="text/javascript" src="<%=path%>/js/helper.js?v=06011"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/data_dict/add.js?v=1550"></script>
</body>
</html>
