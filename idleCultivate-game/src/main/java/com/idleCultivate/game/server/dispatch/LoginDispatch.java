package com.idleCultivate.game.server.dispatch;

import com.idleCultivate.game.login.LoginProtocol;
import com.idleCultivate.game.server.message.SocketMessage;
import com.idleCultivate.game.server.message.TypeProtocol;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import com.idleCultivate.util.cipher.MD5Util;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("login")
public class LoginDispatch implements DispatchService {
    private static final Logger logger = LogManager.getLogger(LoginDispatch.class);
    public UserAccount userAccount = null;
//    public Wizard wizard = null;

    @Autowired
    UserService userService;

    @Override
    public void dispatch(ChannelHandlerContext ctx, SocketMessage message)
    {
        switch (message.getArea()) {
            case LoginProtocol.Area_LoginRequest:
                loginResponse(ctx,message);
                break;
            default:
                break;
        }
    }
    /*
     * **检测用户登录是否密码错误，用户名不存在等，返回int对应的不同类型
     */
    public int loginCheck(ChannelHandlerContext ctx, SocketMessage request)
    {
        List<String> message = request.getMessage();
        String username = message.get(0);
        String password = message.get(1);
        try {
            if (message.isEmpty()) {
                logger.info("Invalid Message!");
                return LoginProtocol.Login_InvalidMessage;
            } else {
                logger.info("Username:" + username + " login");
                String cipher = MD5Util.md5(password);
                //login
                userAccount = userService.login(username, cipher);
                if (userAccount != null) {
//                    httpSession.setAttribute(GameWorld.SK_User, userAccount);
                    return LoginProtocol.Login_Succeed;
                } else {
                    return LoginProtocol.Login_InvalidPassword;
                    //some times retun Login_InvalidUsername is better
//                return LoginProtocol.Login_InvalidUsername;
                }
            }
        } catch (Exception e) {
            logger.error("Dispatch message failed", e.getMessage());
            return LoginProtocol.Login_UnknownError;
        }
    }
    public void loginResponse(ChannelHandlerContext ctx, SocketMessage request)
    {
        SocketMessage response = new SocketMessage();
        int command = loginCheck(ctx, request);
        response.setType(TypeProtocol.TYPE_LOGIN);
        response.setArea(LoginProtocol.Area_LoginResponse);
        response.setCommand(command);
        response.setMessage(request.getMessage());
        ctx.writeAndFlush(response);
        if (command == LoginProtocol.Login_Succeed)
        {
            loginUser(ctx,request);//如果成功就登陆用户,并开始新手向导
        }
    }
    /**
     * 登陆用户，并开始新手向导
     * @param ctx
     */
    public void loginUser(ChannelHandlerContext ctx, SocketMessage socketMessage)
    {
//        user = UserMySQL.getInstance().initUser(User.getUserByChannel(ctx.channel()));
//        user.setWizard(WizardMySQL.getInstance().initWizard(user.getUserID()));
        SocketMessage message = new SocketMessage();
        message.setType(TypeProtocol.TYPE_WIZARD);
//        message.setArea(WizardProtocol.Wizard_Create_Request);
//        message.setCommand(user.getWizard().getStepIndex());
        message.setMessage(null);
        ctx.writeAndFlush(message);
    }
}
