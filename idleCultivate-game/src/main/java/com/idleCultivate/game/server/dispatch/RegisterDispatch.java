package com.idleCultivate.game.server.dispatch;

import com.idleCultivate.game.register.RegisterProtocol;
import com.idleCultivate.game.server.message.SocketMessage;
import com.idleCultivate.game.server.message.TypeProtocol;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("register")
public class RegisterDispatch implements DispatchService {
    private static final Logger log = LogManager.getLogger(RegisterDispatch.class);
    public UserAccount userAccount = null;
//    public Wizard wizard = null;

    @Autowired
    UserService userService;

    @Override
    public void dispatch(ChannelHandlerContext ctx, SocketMessage message)
    {
        switch (message.getArea()) {
            case RegisterProtocol.Area_RegisterRequest:
                registerResponse(ctx,message);
                break;
            default:
                break;
        }
    }
    /*
     * **注册用户，检测用户登录是否用户名重复等，返回int对应的不同类型
     */
    public int registerUser(ChannelHandlerContext ctx, SocketMessage request)
    {
        List<String> message = request.getMessage();
        String username = message.get(0);
        String password = message.get(1);
        String ip = message.get(2);
        if (message.isEmpty())
        {
            log.info("Invalid Message!");
            return RegisterProtocol.Register_InvalidMessage;
        }else{
            log.info("Username:"+ username + " register");
            //register
            userAccount = userService.register(username, password,ip);
            if (userAccount != null){
                return RegisterProtocol.Register_Succeed;
            }else{
                return RegisterProtocol.Register_InvalidUsername;
            }
        }

    }
    public void registerResponse(ChannelHandlerContext ctx, SocketMessage request)
    {
        SocketMessage response = new SocketMessage();
        int command = registerUser(ctx,request);
        response.setType(TypeProtocol.TYPE_REGISTER);
        response.setArea(RegisterProtocol.Area_RegisterResponse);
        response.setCommand(command);
        response.setMessage(request.getMessage());
        ctx.writeAndFlush(response);
        if (command == RegisterProtocol.Register_Succeed)
        {
            //如果用户注册成功
        }
    }
}
