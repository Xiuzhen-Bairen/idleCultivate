package com.idleCultivate.game.hub;

import com.idleCultivate.character.model.Character;
import com.idleCultivate.character.service.CharacterService;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.config.HttpSessionConfigurator;
import com.idleCultivate.game.GameWorld;
import com.idleCultivate.game.hub.message.handler.MessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint(value = "/hub", configurator = HttpSessionConfigurator.class)
public class MessageHub {
    private static final Logger logger = LogManager.getLogger(MessageHub.class);

    @Autowired
    MessageHandler messageHandler;
    @Autowired
    CharacterService characterService;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("[websocket][" + session.getId() + "]建立连接");
        try {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getSimpleName());
            if (httpSession == null) {
                logger.error("[websocket][" + session.getId() + "]获取HttpSession失败！");
                throw new Exception("获取HttpSession失败！");
            }


            if (httpSession.getAttribute(GameWorld.SK_CharId) == null) {
                logger.error("[websocket][" + session.getId() + "]获取角色Id为空！");
                throw new Exception("获取角色ID为空！");
            }

            String charId = httpSession.getAttribute(GameWorld.SK_CharId).toString();
            CommonResult commonResult = characterService.find(charId);
            if (commonResult.isSuccess()) {
                Character character = (Character) commonResult.getData();
                /* 加载成功，添加缓存 */
                GameWorld.OnlineSession.put(charId, session);
                GameWorld.OnlineCharacter.put(session.getId(), character);
                GameWorld.MapCharacter.get(character.getMapId()).add(character);
            } else {
                logger.error("加载角色信息失败！charId:" + charId + " message:" + commonResult.getMessage());
                throw new Exception("加载角色信息失败！");
            }
        } catch (Exception ex) {
            logger.error("[websocket][" + session.getId() + "]建立连接异常：" + ex.getMessage(), ex);
            this.closeSession(session, ex.getMessage());
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        logger.info("[websocket][" + session.getId() + "]接收消息：" + message);
        messageHandler.handleMessage(session, message);
    }

    @OnClose
    public void onClose(Session session) {
        logger.info("[websocket][" + session.getId() + "]关闭连接");
        /* 清理缓存 */
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        GameWorld.OnlineSession.remove(character.getId());
        GameWorld.OnlineCharacter.remove(session.getId());
        GameWorld.MapCharacter.get(character.getMapId()).remove(character);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        logger.error("[websocket][" + session.getId() + "]发生异常：" + t.getMessage(), t);
    }

    private void closeSession(Session session, String message) {
        try {
            logger.info("[websocket][" + session.getId() + "]关闭连接，原因：" + message);
            CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, message);
            session.close(closeReason);
        } catch (Exception ex) {
            logger.error("[websocket]关闭连接异常：" + ex.getMessage(), ex);
        }
    }
}
