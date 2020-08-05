package com.idleCultivate.game.logic.battle.dto;

import lombok.Data;

@Data
public class BattleMonster extends BattleUnit {
    private Integer damage;
    private Integer armour;

    @Override
    public Integer getAP() {
        return this.damage;
    }

    @Override
    public Integer getAC() {
        return this.armour;
    }

    @Override
    public Integer getSpeed() {
        return 0;
    }

    @Override
    public Double getHitRate() {
        return 0.95;
    }

    @Override
    public Double getDodgeRate() {
        return 0.05;
    }

    @Override
    public Double getCriticalRate() {
        return 0.05;
    }
}
