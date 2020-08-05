package com.idleCultivate.character.manager;

import com.alibaba.fastjson.JSON;
import com.idleCultivate.character.mapper.CharacterMapper;
import com.idleCultivate.character.model.Character;
import com.idleCultivate.character.model.CharacterExtraInfo;
import com.idleCultivate.common.Const;
import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.datadict.service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterManager {
    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    DataDictService dataDictService;

    /**
     * 创建角色
     * @param userId 账号ID
     * @param name 角色名称
     * @param faction 阵营
     * @param race 种族
     * @param job 职业
     */
    public void createCharacter(String userId, String name, String faction, String race, String job) {
        Character character = new Character();
        character.setUserId(userId);
        character.setName(name);
        character.setRace(race);
        character.setJob(job);
        character.setCreateUser("idleCultivate");
        CharacterExtraInfo extraInfo = new CharacterExtraInfo();
        extraInfo.setMapId(Const.BornMap.Human);
//        extraInfo.setMapId(this.getBornMapId(race).toString());
        character.setExtraInfo(JSON.toJSONString(extraInfo));
        int effected = characterMapper.createCharacter(character);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    /**
     * 获取账号角色列表
     * @param userId 账号ID
     * @return
     */
    public List<Character> listUserCharacters(String userId) {
        return characterMapper.listUserCharacters(userId);
    }

    /**
     * 根据主键ID查询
     * @param id
     * @return
     */
    public Character find(String id) {
        return characterMapper.find(id);
    }

    /**
     * 根据角色名称查询
     * @param name
     * @return
     */
    public Character findByName(String name) {
        return characterMapper.findByName(name);
    }

    /**
     * 结算更新
     * @param character
     */
    public void updateSettle(Character character) {
        int effected = characterMapper.updateSettle(character);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    /**
     * 获取出生地图ID
     * @param race
     * @return
     */
    private String getBornMapId(String race) {
        DataDict dataDict = dataDictService.findByCode(race);
        if (dataDict.getValue().equals("人类")) {
            return Const.BornMap.Human;
        } else if (dataDict.getValue().equals("暗夜精灵")) {
            return Const.BornMap.NightElf;
        } else if (dataDict.getValue().equals("矮人")) {
            return Const.BornMap.Dwarf;
        } else if (dataDict.getValue().equals("侏儒")) {
            return Const.BornMap.Gnome;
        } else if (dataDict.getValue().equals("兽人")) {
            return Const.BornMap.Orc;
        } else if (dataDict.getValue().equals("牛头人")) {
            return Const.BornMap.Tauren;
        } else if (dataDict.getValue().equals("亡灵")) {
            return Const.BornMap.Undead;
        } else if (dataDict.getValue().equals("巨魔")) {
            return Const.BornMap.Troll;
        } else {
            return "";
        }
    }
}
