package com.idleCultivate.game.hub.message.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.dozermapper.core.Mapper;
import com.idleCultivate.character.service.CharacterService;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.game.GameConst;
import com.idleCultivate.game.GameWorld;
import com.idleCultivate.character.model.Character;
import com.idleCultivate.game.hub.message.dto.WowMessage;
import com.idleCultivate.game.hub.message.dto.WowMessageCode;
import com.idleCultivate.game.hub.message.dto.WowMessageHeader;
import com.idleCultivate.game.hub.message.dto.client.CBattleMobMessage;
import com.idleCultivate.game.hub.message.dto.client.CChatMessage;
import com.idleCultivate.game.hub.message.dto.client.CLoadCacheMessage;
import com.idleCultivate.game.hub.message.dto.client.CLoadCharacterMessage;
import com.idleCultivate.game.hub.message.dto.client.CLoadMapMessage;
import com.idleCultivate.game.hub.message.dto.client.CLoginMessage;
import com.idleCultivate.game.hub.message.dto.client.CMoveMessage;
import com.idleCultivate.game.hub.message.dto.client.CLoadOnlineMessage;
import com.idleCultivate.game.hub.message.dto.server.SBattleMobMessage;
import com.idleCultivate.game.hub.message.dto.server.SChatMessage;
import com.idleCultivate.game.hub.message.dto.server.SLoadCacheMessage;
import com.idleCultivate.game.hub.message.dto.server.SLoadCharacterMessage;
import com.idleCultivate.game.hub.message.dto.server.SLoadMapMessage;
import com.idleCultivate.game.hub.message.dto.server.SLoadOnlineMessage;
import com.idleCultivate.game.hub.message.vo.character.CharacterVO;
import com.idleCultivate.game.hub.message.vo.map.MapCoordVO;
import com.idleCultivate.game.hub.message.vo.map.MapInfoVO;
import com.idleCultivate.game.hub.message.vo.OnlineInfoVO;
import com.idleCultivate.game.hub.message.vo.map.WowMapVO;
import com.idleCultivate.game.hub.message.vo.mob.MapMobVO;
import com.idleCultivate.game.logic.battle.BattleCore;
import com.idleCultivate.game.hub.message.vo.battle.BattleMobResult;
import com.idleCultivate.map.model.MapCoord;
import com.idleCultivate.map.model.WowMap;
import com.idleCultivate.map.service.MapCoordService;
import com.idleCultivate.map.service.WowMapService;
import com.idleCultivate.mob.model.MapMob;
import com.idleCultivate.mob.service.MapMobService;
import com.idleCultivate.support.util.CacheUtil;
import com.idleCultivate.support.util.DozerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandler {
    private static final Logger logger = LogManager.getLogger(MessageHandler.class);

    @Autowired
    Mapper dozerMapper;
    @Autowired
    CharacterService characterService;
    @Autowired
    WowMapService wowMapService;
    @Autowired
    MapMobService mapMobService;
    @Autowired
    MapCoordService mapCoordService;
    @Autowired
    BattleCore battleCore;

    /**
     * 消息处理
     *
     * @param session session
     * @param message 消息
     */
    public void handleMessage(Session session, String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        WowMessageHeader header = JSON.parseObject(jsonObject.getString("header"), WowMessageHeader.class);
        if (!this.verifyMessageHeader(header).isSuccess()) {
            logger.warn("MessageHeader校验不通过！" + message);
            return;
        }

        String messageCode = header.getMessageCode();
        String content = jsonObject.getString("content");
        switch (messageCode) {
            case WowMessageCode.CLoadCache:
                CLoadCacheMessage cLoadCacheMessage = JSON.parseObject(content, CLoadCacheMessage.class);
                this.handleLoadCacheMessage(session, cLoadCacheMessage);
                break;
            case WowMessageCode.CLogin:
                CLoginMessage cLoginMessage = JSON.parseObject(content, CLoginMessage.class);
                this.handleLoginMessage(session, cLoginMessage);
                break;
            case WowMessageCode.CLoadMap:
                CLoadMapMessage cLoadMapMessage = JSON.parseObject(content, CLoadMapMessage.class);
                this.handleLoadMapMessage(session, cLoadMapMessage);
            case WowMessageCode.CLoadOnline:
                CLoadOnlineMessage cLoadOnlineMessage = JSON.parseObject(content, CLoadOnlineMessage.class);
                this.handleLoadOnlineMessage(session, cLoadOnlineMessage);
                break;
            case WowMessageCode.CLoadCharacter:
                CLoadCharacterMessage cLoadCharacterMessage = JSON.parseObject(content, CLoadCharacterMessage.class);
                this.handleLoadCharacterMessage(session, cLoadCharacterMessage);
                break;
            case WowMessageCode.CChat:
                CChatMessage cChatMessage = JSON.parseObject(content, CChatMessage.class);
                this.handleChatMessage(session, cChatMessage);
                break;
            case WowMessageCode.CMove:
                CMoveMessage cMoveMessage = JSON.parseObject(content, CMoveMessage.class);
                this.handleMoveMessage(session, cMoveMessage);
                break;
            case WowMessageCode.CBattleMob:
                CBattleMobMessage cBattleMobMessage = JSON.parseObject(content, CBattleMobMessage.class);
                this.handleBattleMobMessage(session, cBattleMobMessage);
            default:
                break;
        }
    }

    /**
     * 处理缓存加载消息
     *
     * @param session session
     * @param message 消息
     */
    private void handleLoadCacheMessage(Session session, CLoadCacheMessage message) {
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SLoadCache);
        SLoadCacheMessage content = new SLoadCacheMessage();
        Map<String, Integer> levelExpMap = new HashMap<>();
        for (Integer key : CacheUtil.levelExpMap.keySet()) {
            levelExpMap.put(key.toString(), CacheUtil.levelExpMap.get(key));
        }

        content.setLevelExpMap(levelExpMap);
        WowMessage wowMessage = new WowMessage<>(header, content);
        this.sendOne(session, wowMessage);
    }

    /**
     * 处理登陆消息
     *
     * @param session session
     * @param message 消息
     */
    private void handleLoginMessage(Session session, CLoginMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        String mapId = character.getMapId();
        this.sendLoadCharacter(session, character);
        this.sendLoadMap(session, mapId);
        this.sendLoadOnlineToMap(mapId);
    }

    /**
     * 处理加载地图信息消息
     *
     * @param session session
     * @param message 消息
     */
    private void handleLoadMapMessage(Session session, CLoadMapMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        String mapId = character.getMapId();
        this.sendLoadMap(session, mapId);
    }

    /**
     * 处理加载在线列表消息
     *
     * @param session session
     * @param message 消息
     */
    private void handleLoadOnlineMessage(Session session, CLoadOnlineMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        String mapId = character.getMapId();
        this.sendLoadOnline(session, mapId);
    }

    /**
     * 加载角色信息
     * @param session session
     * @param message 消息
     */
    private void handleLoadCharacterMessage(Session session, CLoadCharacterMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        this.sendLoadCharacter(session, character);
    }

    /**
     * 处理聊天消息
     *
     * @param session session
     * @param message 消息
     */
    private void handleChatMessage(Session session, CChatMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SChat);
        SChatMessage response = new SChatMessage();
        response.setSendId(character.getId());
        response.setSendName(character.getName());
        response.setRecvId(message.getRecvId());
        response.setRecvName(message.getRecvName());
        response.setMessage(message.getMessage());
        response.setChannel(message.getChannel());
        WowMessage wowMessage = new WowMessage<>(header, response);
        String chatChannel = message.getChannel();
        if (chatChannel.equals(GameConst.ChatChannel.Local)) {
            List<Character> mapChars = GameWorld.MapCharacter.get(character.getMapId());
            for (Character mapChar : mapChars) {
                Session recvSession = GameWorld.OnlineSession.get(mapChar.getId());
                if (recvSession != null && recvSession.isOpen()) {
                    this.sendOne(recvSession, wowMessage);
                }
            }
        } else if (chatChannel.equals(GameConst.ChatChannel.World)) {
            this.sendAll(wowMessage);
        } else if (chatChannel.equals(GameConst.ChatChannel.Whisper)) {
            Session recvSession = GameWorld.OnlineSession.get(message.getRecvId());
            if (recvSession != null && recvSession.isOpen()) {
                this.sendOne(session, wowMessage);
                this.sendOne(recvSession, wowMessage);
            } else {
                // todo 发送错误消息
            }
        } else {
            // todo 其他频道聊天待实现
        }
    }

    /**
     * 地图移动
     *
     * @param session session
     * @param message 消息
     */
    private void handleMoveMessage(Session session, CMoveMessage message) {
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        String fromMapId = character.getMapId();
        String destMapId = message.getDestMapId();
        character.setMapId(destMapId);
        GameWorld.MapCharacter.get(fromMapId).remove(character);
        GameWorld.MapCharacter.get(destMapId).add(character);
        GameWorld.OnlineCharacter.get(session.getId()).setMapId(destMapId);
        // 通知玩家更新地图信息
        this.sendLoadMap(session, destMapId);
        // 通知原地图玩家更新在线列表
        this.sendLoadOnlineToMap(fromMapId);
        // 通知目标地图玩家更新在线列表
        this.sendLoadOnlineToMap(destMapId);
    }

    /**
     * 在线战斗
     * @param session session
     * @param message 消息
     */
    private void handleBattleMobMessage(Session session, CBattleMobMessage message){
        Character character = GameWorld.OnlineCharacter.get(session.getId());
        BattleMobResult battleMobResult = battleCore.battleMapMob(character, message.getMobId());
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SBattleMob);
        SBattleMobMessage response = new SBattleMobMessage();
        response.setBattleMobResult(battleMobResult);
        WowMessage wowMessage = new WowMessage<>(header, response);
        this.sendOne(session, wowMessage);
    }

    private CommonResult verifyMessageHeader(WowMessageHeader header) {
        if (StringUtils.isBlank(header.getMessageCode())) {
            return CommonResult.fail("消息编码不能为空！");
        }

        if (StringUtils.isBlank(header.getRequestTime())) {
            return CommonResult.fail("请求时间不能为空！");
        }

        return CommonResult.success("校验通过！");
    }

    /**
     * 给指定客户端发送消息
     *
     * @param session    客户端session
     * @param wowMessage 消息对象
     */
    private void sendOne(Session session, WowMessage wowMessage) {
        try {
            String message = JSON.toJSONString(wowMessage);
            session.getBasicRemote().sendText(message);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 给所有客户端发送消息
     *
     * @param wowMessage 消息对象
     */
    private void sendAll(WowMessage wowMessage) {
        try {
            String message = JSON.toJSONString(wowMessage);
            Collection<Session> sessions = GameWorld.OnlineSession.values();
            for (Session session : sessions) {
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 发送加载角色消息
     * @param session sesion
     * @param character 角色信息
     */
    private void sendLoadCharacter(Session session, Character character){
        CharacterVO characterVO = dozerMapper.map(character, CharacterVO.class);
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SLoadCharacter);
        SLoadCharacterMessage content = new SLoadCharacterMessage();
        content.setCharacter(characterVO);
        WowMessage<SLoadCharacterMessage> wowMessage = new WowMessage<>(header, content);
        this.sendOne(session, wowMessage);
    }

    /**
     * 发送加载地图消息
     *
     * @param session session
     * @param mapId   地图id
     */
    private void sendLoadMap(Session session, String mapId) {
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SLoadMap);
        MapInfoVO mapInfoVO = this.loadMapInfo(mapId);
        SLoadMapMessage content = new SLoadMapMessage();
        content.setMapInfo(mapInfoVO);
        WowMessage<SLoadMapMessage> wowMessage = new WowMessage<>(header, content);
        this.sendOne(session, wowMessage);
    }

    /**
     * 发送加载在线列表消息给指定的玩家
     *
     * @param session session
     * @param mapId   地图id
     */
    private void sendLoadOnline(Session session, String mapId) {
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SLoadOnline);
        OnlineInfoVO onlineInfoVO = this.loadOnlineInfo(mapId);
        SLoadOnlineMessage content = new SLoadOnlineMessage();
        content.setOnlineInfo(onlineInfoVO);
        WowMessage<SLoadOnlineMessage> wowMessageLoadOnline = new WowMessage<>(header, content);
        this.sendOne(session, wowMessageLoadOnline);
    }

    /**
     * 发送加载在线列表消息给指定地图的玩家
     *
     * @param mapId 地图id
     */
    private void sendLoadOnlineToMap(String mapId) {
        WowMessageHeader header = new WowMessageHeader(WowMessageCode.SLoadOnline);
        OnlineInfoVO onlineInfoVO = this.loadOnlineInfo(mapId);
        SLoadOnlineMessage content = new SLoadOnlineMessage();
        content.setOnlineInfo(onlineInfoVO);
        WowMessage<SLoadOnlineMessage> wowMessageLoadOnline = new WowMessage<>(header, content);
        List<Character> mapChars = GameWorld.MapCharacter.get(mapId);
        for (Character mapChar : mapChars) {
            this.sendOne(GameWorld.OnlineSession.get(mapChar.getId()), wowMessageLoadOnline);
        }
    }

    /**
     * 读取地图信息
     *
     * @param mapId 地图ID
     * @return
     */
    private MapInfoVO loadMapInfo(String mapId) {
        MapInfoVO mapInfoVO = new MapInfoVO();
        CommonResult commonResult = wowMapService.find(mapId);
        if (commonResult.isSuccess()) {
            WowMap wowMap = (WowMap) commonResult.getData();
            WowMapVO wowMapVO = dozerMapper.map(wowMap, WowMapVO.class);
            mapInfoVO.setMap(wowMapVO);
        }

        List<MapCoord> mapCoordList = mapCoordService.listByFromMapId(mapId);
        List<MapCoordVO> mapCoordVOList = DozerUtil.mapList(mapCoordList, MapCoordVO.class, dozerMapper);
        mapInfoVO.setMapCoords(mapCoordVOList);

        return mapInfoVO;
    }

    /**
     * 读取在线列表
     *
     * @param mapId 地图ID
     * @return
     */
    private OnlineInfoVO loadOnlineInfo(String mapId) {
        OnlineInfoVO onlineInfoVO = new OnlineInfoVO();
        List<MapMob> mapMobList = mapMobService.listByMapId(mapId);
        List<MapMobVO> mapMobVOList = DozerUtil.mapList(mapMobList, MapMobVO.class, dozerMapper);
        onlineInfoVO.setMapMobList(mapMobVOList);
        List<Character> mapCharacterList = GameWorld.MapCharacter.get(mapId);
        List<CharacterVO> mapCharacterVOList = DozerUtil.mapList(mapCharacterList, CharacterVO.class, dozerMapper);
        onlineInfoVO.setMapCharacterList(mapCharacterVOList);
        return onlineInfoVO;
    }
}
