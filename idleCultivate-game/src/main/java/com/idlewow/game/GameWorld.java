package com.idlewow.game;

import com.idlewow.character.model.Character;

import javax.websocket.Session;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class GameWorld {
    /* Session Key */
    public static final String SK_User = "idw_user";
    public static final String SK_CharId = "idw_char_id";

    // key = session id
    public static Map<String, Character> OnlineCharacter = new ConcurrentHashMap<>();
    // key = char id
    public static Map<String, Session> OnlineSession = new ConcurrentHashMap<>();
    // key = map id
    public static Map<String, CopyOnWriteArrayList<Character>> MapCharacter = new ConcurrentHashMap<>();

}
