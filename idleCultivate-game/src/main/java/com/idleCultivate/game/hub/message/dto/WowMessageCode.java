package com.idleCultivate.game.hub.message.dto;

public final class WowMessageCode {
    // 客户端发送的消息类型
    public static final String CLoadCache = "30000001";     // 缓存加载

    public static final String CLogin = "30001001";         // 登陆
    public static final String CLoadMap = "30001002";       // 读取地图信息
    public static final String CLoadOnline = "30001003";    // 读取在线列表
    public static final String CLoadCharacter = "30001004"; // 加载角色信息

    public static final String CChat = "30002001";          // 聊天
    public static final String CMove = "30002002";          // 地图移动

    public static final String CBattleMob = "30003001";     // 在线打怪

    // 服务端发送的消息类型
    public static final String SLoadCache = "60000001";     // 缓存加载

    public static final String SLoadMap = "60001002";       // 读取地图信息
    public static final String SLoadOnline = "60001003";    // 读取在线列表
    public static final String SLoadCharacter = "60001004"; // 加载角色信息

    public static final String SChat = "60002001";          // 聊天

    public static final String SBattleMob = "60003001";     // 在线打怪
}
