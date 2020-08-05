package com.idleCultivate.game.hub.message.vo.battle;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BattleRound implements Serializable {
    // 当前回合数
    private Integer number;
    // 回合内战斗记录
    private List<String> messages;
    // 是否战斗结束
    private Boolean end;

    public BattleRound() {
        this.messages = new ArrayList<>();
        this.end = false;
    }

    public BattleRound(Integer roundNum) {
        this();
        this.number = roundNum;
    }

    public void putMessage(String message) {
        this.messages.add(message);
    }
}
