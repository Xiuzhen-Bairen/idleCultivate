package com.idlewow.game.hub.message.dto.server;

import com.idlewow.game.hub.message.vo.battle.BattleMobResult;
import lombok.Data;

@Data
public class SBattleMobMessage extends ServerMessage {
    private BattleMobResult battleMobResult;
}
