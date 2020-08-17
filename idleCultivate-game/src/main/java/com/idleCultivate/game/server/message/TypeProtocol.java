package com.idleCultivate.game.server.message;

public class TypeProtocol {
    // 客户端发送的消息类型

    public static final int TYPE_REGISTER = 10000001;     //注册

    public static final int CLoadCache = 30000001;     // 缓存加载

    public static final int TYPE_LOGIN = 30001001;         // 登陆
    public static final int CLoadMap = 30001002;       // 读取地图信息
    public static final int CLoadOnline = 30001003;    // 读取在线列表
    public static final int CLoadCharacter = 30001004; // 加载角色信息

    public static final int CChat = 30002001;          // 聊天
    public static final int CMove = 30002002;          // 地图移动

    public static final int TYPE_BATTLE = 30003001;     // 在线打怪
    public static final int TYPE_WIZARD = 0; // 登陆请求
    public static final int TYPE_USER = 1; // 登陆请求
    
    // 服务端发送的消息类型
    public static final int SLoadCache = 60000001;     // 缓存加载

    public static final int SLoadMap = 60001002;       // 读取地图信息
    public static final int SLoadOnline = 60001003;    // 读取在线列表
    public static final int SLoadCharacter = 60001004; // 加载角色信息

    public static final int SChat = 60002001;          // 聊天

    public static final int SBattleMob = 60003001;     // 在线打怪
}
