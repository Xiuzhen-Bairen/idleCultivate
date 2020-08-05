<%@ page import="com.idlewow.rms.support.util.DataDictUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>编辑怪物</title>
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
        <form:form class="layui-form" method="post" modelAttribute="mapMob">
            <form:hidden path="id"/>
            <div class="layui-form-item">
                <form:label path="name" class="layui-form-label"> <span class="x-red">*</span>怪物名称 </form:label>
                <div class="layui-input-inline">
                    <form:input path="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="mapName" class="layui-form-label"> <span class="x-red">*</span>所在地图 </form:label>
                <div class="layui-input-inline">
                    <form:hidden path="mapId" lay-verify="required"/>
                    <form:input path="mapName" lay-verify="required" autocomplete="off" class="layui-input" readonly="true" onclick="userControl.chooseMap();" />
                    <button type="button" class="layui-btn layui-inline"
                            onclick="userControl.chooseMap();"><i class="layui-icon">&#xe615;</i>选择地图
                    </button>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="faction" class="layui-form-label"> <span class="x-red">*</span>阵营 </form:label>
                <div class="layui-input-inline">
                    <form:select path="faction" items="<%= DataDictUtil.faction() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="mobClass" class="layui-form-label"> <span class="x-red">*</span>怪物种类 </form:label>
                <div class="layui-input-inline">
                    <form:select path="mobClass" items="<%= DataDictUtil.mobClass() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="mobType" class="layui-form-label"> <span class="x-red">*</span>怪物类型 </form:label>
                <div class="layui-input-inline">
                    <form:select path="mobType" items="<%= DataDictUtil.mobType() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="level" class="layui-form-label"> <span class="x-red">*</span>等级 </form:label>
                <div class="layui-input-inline">
                    <form:input path="level" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="hp" class="layui-form-label"> <span class="x-red">*</span>生命值 </form:label>
                <div class="layui-input-inline">
                    <form:input path="hp" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="damage" class="layui-form-label"> <span class="x-red">*</span>伤害 </form:label>
                <div class="layui-input-inline">
                    <form:input path="damage" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="armour" class="layui-form-label"> <span class="x-red">*</span>护甲 </form:label>
                <div class="layui-input-inline">
                    <form:input path="armour" lay-verify="required" autocomplete="off" class="layui-input"/>
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
<script type="text/javascript" src="<%=path%>/js/helper.js?v=0601"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/map_mob/edit.js?v=0601"></script>
</body>
</html>
