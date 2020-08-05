package com.idlewow.game.logic.battle.util;

/**
 * 经验值计算工具
 */
public class ExpUtil {
    // 60年代经验值基数
    private static final Integer Exp60 = 45;
    // 70年代经验值技术
    private static final Integer Exp70 = 235;

    /**
     * 计算打怪经验
     *
     * @param charLevel 人物等级
     * @param mobLevel  怪物等级
     * @return
     */
    public static Integer getBattleMobExp(int charLevel, int mobLevel) {
        // 计算基础经验值
        int baseExp = 0;
        if (mobLevel < 61) {
            baseExp = Exp60 + charLevel * 5;
        } else if (mobLevel < 71) {
            baseExp = Exp70 + charLevel * 5;
        }

        // 根据级别调整
        int exp = 0;
        int nonExpLevel = getNonExpLevel(charLevel);
        if (mobLevel <= nonExpLevel) {
            exp = 0;
        } else if (charLevel == mobLevel) {
            exp = baseExp;
        } else if (charLevel < mobLevel) {
            int levelDiff = mobLevel - charLevel;
            if (levelDiff > 4) {
                levelDiff = 4;
            }

            exp = (int) (baseExp * (1 + 0.05 * levelDiff));
        } else if (charLevel > mobLevel) {
            int levelDiff = charLevel - mobLevel;
            int zd = getZD(charLevel);
            exp = baseExp * (1 - levelDiff / zd);
        }

        return exp;
    }

    /**
     * 获取无经验的怪物等级
     *
     * @param charLevel 人物等级
     * @return
     */
    private static int getNonExpLevel(int charLevel) {
        if (charLevel >= 1 && charLevel <= 5) {
            return 0;
        } else if (charLevel >= 6 && charLevel <= 39) {
            return charLevel - charLevel / 10 - 5;
        } else if (charLevel >= 40 && charLevel <= 59) {
            return charLevel - charLevel / 5 - 1;
        } else {
            return charLevel - 9;
        }
    }

    /**
     * 获取ZD系数
     *
     * @param charLevel 人物等级
     * @return
     */
    private static int getZD(int charLevel) {
        switch (charLevel) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 5;
            case 8:
            case 9:
                return 6;
            case 10:
            case 11:
                return 7;
            case 12:
            case 13:
            case 14:
            case 15:
                return 8;
            case 16:
            case 17:
            case 18:
            case 19:
                return 9;
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                return 11;
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                return 12;
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                return 13;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return 14;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                return 15;
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                return 16;
            default:
                return 17;
        }
    }
}
