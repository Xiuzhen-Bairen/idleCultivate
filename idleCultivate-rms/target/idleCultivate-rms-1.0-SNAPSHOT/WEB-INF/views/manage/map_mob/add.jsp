<%@ page import="com.idleCultivate.rms.support.util.DataDictUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>添加怪物</title>
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
                <label for="name" class="layui-form-label"> <span class="x-red">*</span>怪物名称 </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="mapName" class="layui-form-label"> <span class="x-red">*</span>所在地图 </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="mapId" name="mapId"/>
                    <input type="text" id="mapName" name="mapName" required lay-verify="required"
                           autocomplete="off" class="layui-input" readonly="readonly" onclick="userControl.chooseMap();">
                    <button type="button" class="layui-btn layui-inline"
                            onclick="userControl.chooseMap();"><i class="layui-icon">&#xe615;</i>选择地图
                    </button>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="faction" class="layui-form-label"> <span class="x-red">*</span>阵营 </label>
                <div class="layui-input-inline">
                    <% request.setAttribute("factionMap", DataDictUtil.faction()); %>
                    <select name="faction" id="faction">
                        <option value="">请选择阵营</option>
                        <c:forEach items="${factionMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="mobClass" class="layui-form-label"> <span class="x-red">*</span>怪物种类 </label>
                <div class="layui-input-inline">
                    <% request.setAttribute("mobClassMap", DataDictUtil.mobClass()); %>
                    <select name="mobClass" id="mobClass">
                        <option value="">请选择怪物种类</option>
                        <c:forEach items="${mobClassMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="mobType" class="layui-form-label"> <span class="x-red">*</span>怪物类型 </label>
                <div class="layui-input-inline">
                    <% request.setAttribute("mobTypeMap", DataDictUtil.mobType()); %>
                    <select name="mobType" id="mobType">
                        <option value="">请选择怪物类型</option>
                        <c:forEach items="${mobTypeMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="level" class="layui-form-label"> <span class="x-red">*</span>等级 </label>
                <div class="layui-input-inline">
                    <input type="text" id="level" name="level" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="hp" class="layui-form-label"> <span class="x-red">*</span>生命值 </label>
                <div class="layui-input-inline">
                    <input type="text" id="hp" name="hp" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="damage" class="layui-form-label"> <span class="x-red">*</span>伤害 </label>
                <div class="layui-input-inline">
                    <input type="text" id="damage" name="damage" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="armour" class="layui-form-label"> <span class="x-red">*</span>护甲 </label>
                <div class="layui-input-inline">
                    <input type="text" id="armour" name="armour" required lay-verify="required"
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
<script type="text/javascript" src="<%=path%>/js/helper.js?v=0601"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/map_mob/add.js?v=0601"></script>
</body>
</html>
