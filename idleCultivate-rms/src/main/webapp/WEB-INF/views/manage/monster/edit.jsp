<%@ page import="com.idleCultivate.rms.support.util.DataDictUtil" %>
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
        <form:form class="layui-form" method="post" modelAttribute="monster">
            <form:hidden path="id"/>
            <div class="layui-form-item">
                <form:label path="name" class="layui-form-label"> <span class="x-red">*</span>名称</form:label>
                <div class="layui-input-inline">
                    <form:input path="name" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="map_id" class="layui-form-label"> <span class="x-red">*</span>地图Id</form:label>
                <div class="layui-input-inline">
                    <form:input path="map_id" lay-verify="required" autocomplete="off" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-type">
                <form:label path="race" class="layui-form-label"> <span class="x-red">*</span>种族 </form:label>
                <div class="layui-input-inline">
                    <form:select path="race" items="<%=DataDictUtil.race() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-type">
                <form:label path="job" class="layui-form-label"> <span class="x-red">*</span>职业 </form:label>
                <div class="layui-input-inline">
                    <form:select path="job" items="<%=DataDictUtil.job() %>"></form:select>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="level" class="layui-form-label"> <span class="x-red">*</span>等级</form:label>
                <div class="layui-input-inline">
                    <form:input path="level" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="hp" class="layui-form-label"> <span class="x-red">*</span>生命</form:label>
                <div class="layui-input-inline">
                    <form:input path="hp" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="att" class="layui-form-label"> <span class="x-red">*</span>攻击</form:label>
                <div class="layui-input-inline">
                    <form:input path="att" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="def" class="layui-form-label"> <span class="x-red">*</span>防御/form:label>
                <div class="layui-input-inline">
                    <form:input path="def" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="crit" class="layui-form-label"> <span class="x-red">*</span>暴击率</form:label>
                <div class="layui-input-inline">
                    <form:input path="crit" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="dodge" class="layui-form-label"> <span class="x-red">*</span>闪避率</form:label>
                <div class="layui-input-inline">
                    <form:input path="dodge" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="coordinate" class="layui-form-label"> <span class="x-red">*</span>所在坐标</form:label>
                <div class="layui-input-inline">
                    <form:input path="coordinate" lay-verify="required" autocomplete="off" class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <form:label path="is_hide" class="layui-form-label"> <span class="x-red">*</span>隐身</form:label>
                    <form:radiobutton path ="is_hide" label="隐身" value="true" lay-verify="required" autocomplete="off"
                                      class="layui-input"/>
                    <form:radiobutton path ="is_hide" label="不隐身" value="false" lay-verify="required" autocomplete="off"
                                      class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <form:label path="extra_info" class="layui-form-label"> <span class="x-red">*</span>扩展信息</form:label>
                <div class="layui-input-inline">
                    <form:input path="extra_info" lay-verify="required" autocomplete="off" class="layui-input"/>
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
<script type="text/javascript" src="<%=path%>/js/wow/manage/monster/edit.js?v=0510"></script>
</body>
</html>
