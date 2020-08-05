package com.idlewow.game.logic.battle;

import com.idlewow.character.model.Character;
import com.idlewow.character.model.LevelProp;
import com.idlewow.character.service.CharacterService;
import com.idlewow.character.service.LevelPropService;
import com.idlewow.common.model.CommonResult;
import com.idlewow.game.GameConst;
import com.idlewow.game.logic.battle.dto.BattleMonster;
import com.idlewow.game.logic.battle.dto.BattlePlayer;
import com.idlewow.game.logic.battle.util.ExpUtil;
import com.idlewow.game.hub.message.vo.battle.BattleMobResult;
import com.idlewow.game.logic.battle.dto.BattleUnit;
import com.idlewow.game.logic.battle.util.BattleUtil;
import com.idlewow.game.hub.message.vo.battle.BattleRound;
import com.idlewow.mob.model.MapMob;
import com.idlewow.mob.service.MapMobService;
import com.idlewow.support.util.CacheUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public final class BattleCore {
    private static final Logger logger = LogManager.getLogger(BattleCore.class);
    // 战斗最大回合数
    private static final Integer MaxRound = 20;
    // 暴击系数
    private static final Integer CriticalFactor = 2;

    @Autowired
    MapMobService mapMobService;
    @Autowired
    LevelPropService levelPropService;
    @Autowired
    CharacterService characterService;

    /**
     * 在线打怪
     *
     * @param character
     * @param mobId
     * @return
     */
    public BattleMobResult battleMapMob(Character character, String mobId) {
        // 获取地图怪物信息
        CommonResult commonResult = mapMobService.find(mobId);
        if (!commonResult.isSuccess()) {
            logger.error("未找到指定怪物：id" + mobId);
            return null;
        }

        // 初始化参战方信息
        MapMob mapMob = (MapMob) commonResult.getData();
        List<BattleUnit> atkList = new LinkedList<>();
        atkList.add(this.getBattlePlayer(character, GameConst.BattleTeam.ATK));
        List<BattleUnit> defList = new LinkedList<>();
        defList.add(this.getBattleMonster(mapMob, GameConst.BattleTeam.DEF));
        List<BattleUnit> battleList = new LinkedList<>();
        battleList.addAll(atkList);
        battleList.addAll(defList);
        battleList = BattleUtil.sortUnitBySpeed(battleList);
        // 回合循环
        BattleMobResult battleMobResult = new BattleMobResult();
        for (int i = 0; i < MaxRound; i++) {
            BattleRound battleRound = new BattleRound(i + 1);
            for (BattleUnit battleUnit : battleList) {
                if (!battleUnit.getIsDefeat()) {
                    // 选定攻击目标
                    BattleUnit targetUnit = null;
                    if (battleUnit.getTeam().equals(GameConst.BattleTeam.ATK)) {
                        Integer targetIndex = new Random().nextInt(defList.size());
                        targetUnit = defList.get(targetIndex);
                    } else if (battleUnit.getTeam().equals(GameConst.BattleTeam.DEF)) {
                        Integer targetIndex = new Random().nextInt(atkList.size());
                        targetUnit = atkList.get(targetIndex);
                    }

                    // 攻方出手ROLL点
                    Integer roll = new Random().nextInt(100);
                    Double miss = (1 - battleUnit.getHitRate() / (battleUnit.getHitRate() + battleUnit.getDodgeRate())) * 100;
                    Double critical = battleUnit.getCriticalRate() * 100;
                    logger.info("round: " + i + "atk: " + battleUnit.getName() + " def: " + targetUnit.getName() + " roll:" + roll + " miss: " + miss + " cri: " + critical);
                    String desc = "";
                    if (roll <= miss) {
                        desc = battleUnit.getName() + " 的攻击未命中 " + targetUnit.getName();
                    } else if (roll <= miss + critical) {
                        Integer damage = BattleUtil.actualDamage(battleUnit.getAP(), targetUnit.getDR()) * CriticalFactor;
                        desc = battleUnit.getName() + " 的攻击暴击，对 " + targetUnit.getName() + " 造成 " + damage + " 点伤害（" + targetUnit.getHp() + " - " + damage + " ）";
                        targetUnit.setHp(targetUnit.getHp() - damage);
                    } else {
                        Integer damage = BattleUtil.actualDamage(battleUnit.getAP(), targetUnit.getDR());
                        desc = battleUnit.getName() + " 的攻击，对 " + targetUnit.getName() + " 造成 " + damage + " 点伤害（" + targetUnit.getHp() + " - " + damage + " ）";
                        targetUnit.setHp(targetUnit.getHp() - damage);
                    }

                    // 检测守方存活
                    if (targetUnit.getHp() <= 0) {
                        targetUnit.setIsDefeat(true);
                        desc += "， " + targetUnit.getName() + " 阵亡";
                        if (battleUnit.getTeam().equals(GameConst.BattleTeam.ATK)) {
                            defList.remove(targetUnit);
                        } else if (battleUnit.getTeam().equals(GameConst.BattleTeam.DEF)) {
                            atkList.remove(targetUnit);
                        }
                    } else {
                        // 检测守方反击动作
                        // todo
                    }

                    battleRound.putMessage(desc);
                    // 检测战斗结束
                    if (atkList.size() == 0 || defList.size() == 0) {
                        Boolean playerWin = defList.size() == 0;
                        battleRound.setEnd(true);
                        battleMobResult.setTotalRound(i);
                        battleMobResult.setPlayerWin(playerWin);
                        String resultMessage = "战斗结束！ " + character.getName() + (playerWin ? " 获得胜利！" : " 不幸战败！");
                        battleMobResult.putBattleRound(battleRound);
                        battleMobResult.setResultMessage(resultMessage);
                        // 玩家获胜 进行战斗结算
                        if (playerWin) {
                            // 经验结算
                            this.settleExp(character.getLevel(), mapMob.getLevel(), character);
                            // 更新角色数据
                            characterService.updateSettle(character);
                        }

                        return battleMobResult;
                    }
                }
            }

            battleMobResult.putBattleRound(battleRound);
        }

        battleMobResult.setTotalRound(MaxRound);
        battleMobResult.setResultMessage("战斗回合数已用尽！守方获胜！");
        return battleMobResult;
    }

    /**
     * 经验值结算
     * @param charLevel 角色等级
     * @param mobLevel 怪物等级
     * @param character 角色信息
     */
    private void settleExp(Integer charLevel, Integer mobLevel, Character character) {
        Integer exp = ExpUtil.getBattleMobExp(charLevel, mobLevel);
        if (exp > 0) {
            Integer levelUpExp = CacheUtil.getLevelExp(charLevel);
            if (character.getExperience() + exp >= levelUpExp) {
                character.setLevel(charLevel + 1);
                character.setExperience(character.getExperience() + exp - levelUpExp);
            } else {
                character.setExperience(character.getExperience() + exp);
            }
        }
    }


    /**
     * 获取角色战斗状态
     * @param character 角色信息
     * @param battleTeam 所属队伍
     * @return
     */
    private BattlePlayer getBattlePlayer(Character character, String battleTeam) {
        LevelProp levelProp = levelPropService.findByJobAndLevel(character.getJob(), character.getLevel());
        BattlePlayer battlePlayer = new BattlePlayer();
        battlePlayer.setId(character.getId());
        battlePlayer.setName(character.getName());
        battlePlayer.setJob(character.getJob());
        battlePlayer.setLevel(character.getLevel());
        battlePlayer.setHp(levelProp.getHp());
        battlePlayer.setStrength(levelProp.getStrength());
        battlePlayer.setStamina(levelProp.getStamina());
        battlePlayer.setAgility(levelProp.getAgility());
        battlePlayer.setIntellect(levelProp.getIntellect());
        battlePlayer.setTeam(battleTeam);
        return battlePlayer;
    }

    /**
     * 获取怪物战斗状态
     * @param mapMob 怪物信息
     * @param battleTeam 所属队伍
     * @return
     */
    private BattleMonster getBattleMonster(MapMob mapMob, String battleTeam) {
        BattleMonster battleMonster = new BattleMonster();
        battleMonster.setId(mapMob.getId());
        battleMonster.setName(mapMob.getName());
        battleMonster.setLevel(mapMob.getLevel());
        battleMonster.setHp(mapMob.getHp());
        battleMonster.setDamage(mapMob.getDamage());
        battleMonster.setArmour(mapMob.getArmour());
        battleMonster.setTeam(battleTeam);
        return battleMonster;
    }
}
