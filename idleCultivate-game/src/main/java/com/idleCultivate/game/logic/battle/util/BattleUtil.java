package com.idleCultivate.game.logic.battle.util;

import com.idleCultivate.game.logic.battle.dto.BattleUnit;

import java.util.LinkedList;
import java.util.List;

public class BattleUtil {
    /**
     * 获取实际伤害
     *
     * @param ap 攻击强度
     * @param dr 伤害减免
     * @return
     */
    public static Integer actualDamage(Integer ap, Double dr) {
        Double damage = ap - ap * dr;
        return damage.intValue();
    }

    /**
     * 战斗排序，速度大的单位靠前（速度相等的单位出手顺序不确定）
     *
     * @param list
     * @return
     */
    public static List<BattleUnit> sortUnitBySpeed(List<BattleUnit> list) {
        return sort(list);
    }

    private static List<BattleUnit> sort(List<BattleUnit> list) {
        int size = list.size();
        if (size == 1)
            return list;
        int lSize = size / 2;
        int rSize = size - lSize;
        List<BattleUnit> leftList = new LinkedList<>();
        List<BattleUnit> rightList = new LinkedList<>();
        for (int i = 0; i < lSize; i++) {
            leftList.add(list.get(i));
        }

        for (int i = 0; i < rSize; i++) {
            rightList.add(list.get(lSize + i));
        }

        List<BattleUnit> newLeft = sort(leftList);
        List<BattleUnit> newRight = sort(rightList);
        return merge(newLeft, newRight);
    }

    private static List<BattleUnit> merge(List<BattleUnit> leftList, List<BattleUnit> rightList) {
        if (leftList.size() == 0)
            return rightList;
        if (rightList.size() == 0)
            return leftList;

        int lSize = leftList.size();
        int rSize = rightList.size();
        int size = lSize + rSize;
        List<BattleUnit> sortedList = new LinkedList<>();
        int lIndex = 0;
        int rIndex = 0;
        for (int i = 0; i < size; i++) {
            if (lIndex == lSize) {
                sortedList.add(rightList.get(rIndex++));
            } else if (rIndex == rSize) {
                sortedList.add(leftList.get(lIndex++));
            } else if (leftList.get(lIndex).getSpeed() >= rightList.get(rIndex).getSpeed()) {
                sortedList.add(leftList.get(lIndex++));
            } else {
                sortedList.add(rightList.get(rIndex++));
            }
        }

        return sortedList;
    }
}
