<%@ page import="com.idleCultivate.support.util.DataDictUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/authorize.jsp" %>
<html>
<head>
    <title>角色选择</title>
    <link rel="stylesheet" href="<%=path%>/css/font.css">
    <link rel="stylesheet" href="<%=path%>/css/xadmin.css">
    <link rel="stylesheet" href="<%=path%>/css/wow/base.css?v=0714">
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path%>/js/xadmin.js"></script>
</head>
<style>
    html, body, div, span {
        letter-spacing: 2px;
    }
</style>
<body>
<div class="bg bg-char"></div>
<div class="layui-fluid">
    <div class="layui-row" style="height: 80%;">
        <form action="/game/main" method="post">
            <input type="hidden" name="characterId" id="characterId"/>
            <c:forEach begin="0" step="1" end="2" var="index">
                <c:choose>
                    <c:when test="${characters[index] != null}">
                        <c:set var="item" value="${characters[index]}"></c:set>
                        <div class="layui-col-md4">
                            <div style="width: 90%;height: 100%;border: 1px solid black;background-color:rgba(230,190,80, 0.5);border: 2px solid rgb(255,235,150);border-radius:5px;color:yellow;padding:5px;">
                                <div>
                                    <label>昵称:</label>
                                    <label>${item.name}</label>
                                </div>
                                <div>
                                    <% request.setAttribute("factionMap", DataDictUtil.faction()); %>
                                    <label>阵营:</label>
                                    <label style="color: ${item.faction == 1 ? "blue" : "red"}">${factionMap.get(item.faction)}</label>
                                </div>
                                <div>
                                    <label>等级:</label>
                                    <label>${item.level}</label>
                                </div>
                                <div>
                                    <% request.setAttribute("raceMap", DataDictUtil.race()); %>
                                    <label>种族:</label>
                                    <label>${raceMap.get(item.race)}</label>
                                </div>
                                <div>
                                    <% request.setAttribute("jobMap", DataDictUtil.job()); %>
                                    <label>职业:</label>
                                    <label>${jobMap.get(item.job)}</label>
                                </div>
                                <div>
                                    <label>地图:</label>
                                    <label>艾尔文森林</label>
                                </div>
                            </div>
                            <div style="position:relative;left:35%;top:80%;">
                                <button type="submit" class="btn-wow"
                                        onclick="$('#characterId').val('${item.id}');">进入游戏
                                </button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="layui-col-md4">
                            <div style="width: 90%;height: 100%;border: 1px solid black;background-color:rgba(230,190,80, 0.5);border: 2px solid rgb(255,235,150);border-radius:5px;color:yellow;padding:5px;">
                                <div>
                                    尚未创建角色
                                </div>
                            </div>
                            <div style="position:relative;left:35%;top:80%;">
                                <button type="button" class="btn-wow"
                                        onclick="location.href='/game/createChar';">创建角色
                                </button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/js/helper.js"></script>
</body>
</html>
