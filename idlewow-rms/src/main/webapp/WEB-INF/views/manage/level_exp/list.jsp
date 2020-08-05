<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>升级经验配置</title>
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
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">后台管理</a>
            <a>
              <cite>升级经验配置</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form id="queryForm" class="layui-form layui-col-space8" method="post">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="level" placeholder="请输入等级" autocomplete="off" class="layui-input"
                                   onkeyup="value = this.value.replace(/[^\d]/g, '');">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit lay-filter="search" type="button" onclick="search();">
                                <i class="layui-icon">&#xe615;</i>查询
                            </button>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" type="button" onclick="reset();"> 重置</button>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button type="button" class="layui-btn" onclick="xadmin.open('添加配置','add',500,500)">
                                <i class="layui-icon"></i>添加配置
                            </button>
                        </div>
                        <div class="layui-upload layui-inline layui-show-xs-block">
                            <button type="button" class="layui-btn layui-btn-normal" id="btnSelectFile">选择Excel</button>
                            <button type="button" class="layui-btn" id="btnImport">开始导入</button>
                        </div>
                    </form>

                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form" id="datatable"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path%>/js/helper.js?v=06011"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/level_exp/list.js?v=0601"></script>
</html>