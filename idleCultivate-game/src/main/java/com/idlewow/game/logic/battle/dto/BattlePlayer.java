package com.idlewow.game.logic.battle.dto;

import com.idlewow.game.GameConst;
import lombok.Data;

@Data
public class BattlePlayer extends BattleUnit {
    private String job;
    private Integer strength;
    private Integer agility;
    private Integer stamina;
    private Integer intellect;

    @Override
    public Integer getAP() {
        if (this.job.equals(GameConst.Job.Warrior)) {
            return this.getLevel() * 3 + this.strength * 2 - 20;
        } else {
            // todo 其他职业攻强公式待实现
            return 100;
        }
    }

    @Override
    public Integer getAC() {
        return this.stamina * 2;
    }

    @Override
    public Integer getSpeed() {
        return this.agility;
    }

    @Override
    public Double getHitRate() {
        return 0.95;
    }

    @Override
    public Double getDodgeRate() {
        return 0.0005 * this.agility;
    }

    @Override
    public Double getCriticalRate() {
        return 0.0005 * this.agility;
    }
}
