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
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地图Id</label>
                <div class="layui-input-inline">
                    <input type="text" id="map_id" name="map_id" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">种族</label>
                <div class="layui-input-inline">
                    <input type="text" id="faction" name="faction" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职业</label>
                <div class="layui-input-inline">
                    <input type="text" id="mobClass" name="mobClass" required lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <input type="text" name="level" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生命</label>
                <div class="layui-input-inline">
                    <input type="text" name="hp" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">攻击</label>
                <div class="layui-input-inline">
                    <input type="text" name="att" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">防御</label>
                <div class="layui-input-inline">
                    <input type="text" name="def" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">暴击率</label>
                <div class="layui-input-inline">
                    <input type="text" name="crit" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">闪避率</label>
                <div class="layui-input-inline">
                    <input type="text" name="dodge" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所在坐标</label>
                <div class="layui-input-inline">
                    <input type="text" name="coordinate" required lay-verify="required" autocomplete="off"
                           class="layui-input" onkeyup="value = this.value.replace(/[^\d]/g, '');"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">隐身</label>
                <div class="layui-input-inline">
                    <input type="radio" name="is_hide" value=true title="隐身" required lay-verify="required" autocomplete="off"
                           class="layui-input">
                    <input type="radio" name="is_hide" value=false title="不隐身" required lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">扩展信息</label>
                <div class="layui-input-inline">
                    <input type="text" id="extra_info" name="extra_info" required lay-verify="required"
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
<script type="text/javascript" src="<%=path%>/js/helper.js?v=06011"></script>
<script type="text/javascript" src="<%=path%>/js/wow/manage/monster/add.js?v=1550"></script>
</body>
</html>
