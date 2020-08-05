package com.idlewow.game.logic.battle.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public abstract class BattleUnit implements Serializable {
    private UUID uuid;
    private String id;
    private String name;
    private Integer level;
    private Integer hp;
    private String team;
    private Boolean isDefeat;


    public BattleUnit() {
        this.uuid = UUID.randomUUID();
        this.isDefeat = false;
    }

    /**
     * 获取攻击强度Attack Power
     *
     * @return
     */
    public abstract Integer getAP();

    /**
     * 获取护甲等级Armour Class
     *
     * @return
     */
    public abstract Integer getAC();

    /**
     * 获取速度值 Speed
     *
     * @return
     */
    public abstract Integer getSpeed();

    /**
     * 获取命中率Hit Rate
     *
     * @return
     */
    public abstract Double getHitRate();

    /**
     * 获取闪避率Dodge Rate
     *
     * @return
     */
    public abstract Double getDodgeRate();

    /**
     * 获取暴击率Critical Rate
     *
     * @return
     */
    public abstract Double getCriticalRate();

    /**
     * 获取伤害减免Damage Reduce
     *
     * @return
     */
    public Double getDR() {
        Integer ac = this.getAC();
        return ac / (ac + 85 * this.level + 400.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BattleUnit) {
            BattleUnit battleUnit = (BattleUnit) obj;
            return this.uuid.equals(battleUnit.getUuid());
        }

        return false;
    }
}
