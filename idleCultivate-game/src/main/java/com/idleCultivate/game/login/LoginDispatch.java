package com.idleCultivate.game.login;

import com.idleCultivate.game.GameWorld;
import com.idleCultivate.game.util.SocketModel;
import com.idleCultivate.game.util.TypeProtocol;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import com.idleCultivate.util.cipher.MD5Util;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginDispatch {
    private static final Logger log = LogManager.getLogger(LoginDispatch.class);
    private static LoginDispatch instance = new LoginDispatch();
    public static LoginDispatch getInstance()
    {
        return instance;
    }
    public UserAccount userAccount = null;
//    public Wizard wizard = null;

    @Autowired
    UserService userService;
    @Autowired
    private HttpSession httpSession;

    public void dispatch(ChannelHandlerContext ctx, SocketModel message)
    {
        switch (message.getArea()) {
            case LoginProtocol.Area_LoginRequest:
                LoginResponse(ctx,message);
                break;
            default:
                break;
        }
    }
    /*
     * **检测用户登录是否密码错误，用户名不存在等，返回int对应的不同类型
     */
    public int LoginCheck(ChannelHandlerContext ctx,SocketModel request)
    {
        List<String> message = request.getMessage();
        String username = message.get(0);
        String password = message.get(1);
        if (message.isEmpty())
        {
            return LoginProtocol.Login_InvalidMessage;
        }else{
            log.info("Username:"+ username + "login");
            String cipher = MD5Util.md5(password);
            //login
            userAccount = userService.login(username, cipher);
            if (userAccount != null){
                httpSession.setAttribute(GameWorld.SK_User, userAccount);
                return LoginProtocol.Login_Succeed;
            }else{
                return LoginProtocol.Login_InvalidPassword;
                //some times retun Login_InvalidUsername is better
//                return LoginProtocol.Login_InvalidUsername;
            }
        }

    }
    public void LoginResponse(ChannelHandlerContext ctx,SocketModel request)
    {
        SocketModel response = new SocketModel();
        int command = LoginCheck(ctx, request);
        response.setType(TypeProtocol.TYPE_LOGIN);
        response.setArea(LoginProtocol.Area_LoginResponse);
        response.setCommand(command);
        response.setMessage(request.getMessage());
        ctx.writeAndFlush(response);
        if (command == LoginProtocol.Login_Succeed)
        {

            LoginUser(ctx,request);//如果成功就登陆用户,并开始新手向导
        }
    }
    /**
     * 登陆用户，并开始新手向导
     * @param ctx
     */
    public void LoginUser(ChannelHandlerContext ctx,SocketModel socketModel)
    {
//        user = UserMySQL.getInstance().initUser(User.getUserByChannel(ctx.channel()));
//        user.setWizard(WizardMySQL.getInstance().initWizard(user.getUserID()));
        SocketModel message = new SocketModel();
        message.setType(TypeProtocol.TYPE_WIZARD);
//        message.setArea(WizardProtocol.Wizard_Create_Request);
//        message.setCommand(user.getWizard().getStepIndex());
        message.setMessage(null);
        ctx.writeAndFlush(message);
    }
}
