package com.idleCultivate.game.hub.message.vo.battle;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BattleMobResult implements Serializable {
    // 总回合数
    private Integer totalRound;
    // 回合列表
    private List<BattleRound> roundList;
    // 是否玩家获胜
    private Boolean playerWin;
    // 战斗结果信息
    private String resultMessage;

    public BattleMobResult() {
        this.roundList = new ArrayList<>();
    }

    public void putBattleRound(BattleRound battleRound) {
        this.roundList.add(battleRound);
    }
}
