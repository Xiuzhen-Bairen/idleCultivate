<%@ page import="com.idlewow.rms.support.util.DataDictUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>怪物列表</title>
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
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">后台管理</a>
            <a>
              <cite>怪物</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form id="queryForm" class="layui-form layui-col-space5" method="post">
                        <div class="layui-inline layui-show-xs-block">
                            <label class="layui-inline">怪物名称：</label>
                            <input type="text" name="name" autocomplete="off" class="layui-input layui-input-inline"
                                   style="width: 100px;">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <label class="layui-inline">等级范围：</label>
                            <div class="layui-input-inline" style="width: 50px;">
                                <input type="text" name="levelStart" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-inline">-</div>
                            <div class="layui-input-inline" style="width: 50px;">
                                <input type="text" name="levelEnd" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <label class="layui-inline">阵营：</label>
                            <% request.setAttribute("factionMap", DataDictUtil.faction()); %>
                            <div class="layui-inline" style="width: 120px;">
                                <select name="faction">
                                    <option value="">全部</option>
                                    <c:forEach items="${factionMap}" var="item">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <label class="layui-inline">种类：</label>
                            <% request.setAttribute("mobClassMap", DataDictUtil.mobClass()); %>
                            <div class="layui-inline" style="width: 120px;">
                                <select name="mobClass">
                                    <option value="">全部</option>
                                    <c:forEach items="${mobClassMap}" var="item">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <label class="layui-inline">类型：</label>
                            <div class="layui-inline" style="width: 120px;">
                                <% request.setAttribute("mobTypeMap", DataDictUtil.mobType()); %>
                                <select name="mobType">
                                    <option value="">全部</option>
                                    <c:forEach items="${mobTypeMap}" var="item">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-filter="search" type="button" onclick="search();">
                                <i class="layui-icon">&#xe615;</i>查询
                            </button>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" type="reset"> 重置</button>
                        </div>
                    </form>
                    <div class="layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <button type="button" class="layui-btn" onclick="xadmin.open('添加怪物','add',500,500)">
                                <i class="layui-icon"></i>添加怪物
                            </button>
                        </div>
                        <div class="layui-upload layui-inline layui-show-xs-block">
                            <button type="button" class="layui-btn layui-btn-normal" id="btnSelectFile">选择Excel</button>
                            <button type="button" class="layui-btn" id="btnImport">开始导入</button>
                        </div>
                    </div>
                </div>
                <div class="layui-card-body">
                    <table class="layui-table layui-form" id="datatable"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path%>/js/helper.js?v=0603"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/map_mob/list.js?v=06031"></script>
</html>