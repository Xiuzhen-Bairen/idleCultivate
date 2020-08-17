package com.idleCultivate.game.register;

public class RegisterProtocol {
    /*
     * Register_Area
     * **/
    public static final int Area_RegisterRequest = 0; // 登陆请求
    public static final int Area_RegisterResponse = 1; //登录应答

    /*
     * Register_Command
     * **/
    public static final int Register_InvalidMessage = 0;//无效消息
    public static final int Register_InvalidUsername = 1;//用户名重复

    public static final int Register_Succeed = 10;//登陆成功
}
