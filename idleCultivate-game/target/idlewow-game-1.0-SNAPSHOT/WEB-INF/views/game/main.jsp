<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<html>
<head>
    <title>Idle WOW</title>
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <link rel="stylesheet" href="<%=path%>/css/wow/base.css?v=0714">
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
</head>
<body>
<div>
    <div class="bg bg-main"></div>
    <div class="layui-fluid ">
        <div class="layui-row" style="height:28%;">
            <div class="layui-col-md3 panel-wow">
                <!-- 角色信息 -->
                <div>
                    <div>
                        <label>角色:</label>
                        <label id="char-name"></label>
                    </div>
                    <div>
                        <label>阵营:</label>
                        <label id="char-faction"></label>
                    </div>
                    <div>
                        <label>种族:</label>
                        <label id="char-race"></label>
                    </div>
                    <div>
                        <label>职业:</label>
                        <label id="char-job"></label>
                    </div>
                    <div>
                        <label>等级:</label>
                        <label id="char-level"></label>
                    </div>
                    <div>
                        <label>经验:</label>
                        <label id="char-exp" class="lbl-exp"></label>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6 panel-wow" style="overflow-y:scroll;">
                <!-- 地图信息 -->
                <div>
                    <h5 style="letter-spacing: 4px;padding: 5px;"><b id="mapName"></b></h5>
                    <hr class="hr-wow-map"/>
                    <div id="mapDesc"></div>
                </div>
            </div>
            <div class="layui-col-md3" style="padding: 1px;">
                <!-- 地图图片和锚点 -->
                <img id="mapImg" src="" style="width: 100%;height: 100%;opacity: 0.8;border-radius: 10px;"
                     usemap="#map-coords"/>
                <map id="map-coords" name="map-coords"></map>
            </div>
        </div>
        <div class="layui-row" style="height:65%;">
            <div class="layui-col-md9">
                <div class="layui-row" style="height: 90%">
                    <!-- 聊天记录 -->
                    <div class="layui-col-md4 panel-wow">
                        <h5>聊天记录</h5>
                        <div class="msg-chat">
                        </div>
                    </div>
                    <!-- 战斗记录 -->
                    <div class="layui-col-md8 panel-wow" style="overflow-y: scroll;">
                        <h5>战斗记录</h5>
                        <hr/>
                        <div class="msg-battle">
                        </div>
                    </div>
                </div>
                <div class="layui-row" style="height: 10%;">
                    <div class="layui-col-md12 panel-wow">
                        <!-- 发言框 -->
                        <div style="vertical-align: middle;padding: 5px;">
                            <select style="height: 27px;" id="chat-channel" class="chat chat-channel"
                                    onchange="changeChatChannel();">
                                <option value="1">当前</option>
                                <option value="2">世界</option>
                                <option value="3" disabled="disabled">阵营</option>
                                <option value="4" disabled="disabled">组队</option>
                                <option value="5" style="display: none;">私聊</option>
                            </select>
                            <input type="hidden" id="chat-whisperId"/>
                            对
                            <input type="text" id="chat-whisperName" style="width: 80px;height:27px;"
                                   value="所有人" readonly="readonly"/>
                            说：
                            <div style="width: 75%;float: right;">
                                <input type="text" id="chat-msg" style="width:80%; height: 28px;"/>
                                <button onclick="chat();" class="btn-wow">发送 (Enter)</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md3 panel-wow" style="overflow-y: scroll;">
                <div class="layui-tab layui-tab-card" style="margin-top: 0;">
                    <!-- 在线列表 -->
                    <ul class="layui-tab-title" style="margin-top: 0;">
                        <li class="layui-this" style="margin: 0;padding: 0;">全部</li>
                        <li style="margin: 0;padding: 0;">玩家</li>
                        <li style="margin: 0;padding: 0;">怪物</li>
                        <li style="margin: 0;padding: 0;">NPC</li>
                    </ul>
                    <div class="layui-tab-content">
                        <!-- 全部 -->
                        <div class="layui-tab-item layui-show" id="online-all" style="height:100%;">
                            <c:forEach items="${mobs}" var="item">
                                <div class="layui-row">
                                    <div class="layui-col-md9">
                                        <label>${item.name}</label>
                                        <label> - 等级：${item.level}</label>
                                    </div>
                                    <div class="layui -col-md3">
                                        <button type="button" style="height:14px;line-height: 14px;"
                                                onclick="wowClient.battle('${character.id}','${item.id}');">战斗
                                        </button>
                                        <button type="button" style="height:14px;line-height:14px;" onclick="guaji();">
                                            挂机
                                        </button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- 玩家 -->
                        <div class="layui-tab-item" id="online-player" style="height:100%;">
                        </div>
                        <!-- 怪物 -->
                        <div class="layui-tab-item" id="online-mob" style="height:100%;">
                            <c:forEach items="${mobs}" var="item">
                                <div class="layui-row">
                                    <div class="layui-col-md9">
                                        <label>${item.name}</label>
                                        <label> - 等级：${item.level}</label>
                                    </div>
                                    <div class="layui -col-md3">
                                        <button type="button" style="height:14px;line-height: 14px;"
                                                onclick="wowClient.battle('${character.id}','${item.id}');">战斗
                                        </button>
                                        <button type="button" style="height:14px;line-height:14px;" onclick="guaji();">
                                            挂机
                                        </button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- NPC -->
                        <div class="layui-tab-item" id="online-npc" style="height:100%;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/js/wow/wow.js?v=0522"></script>
<script type="text/javascript" src="<%=path%>/js/wow/game/main.js?v=0522"></script>
</body>
</html>
