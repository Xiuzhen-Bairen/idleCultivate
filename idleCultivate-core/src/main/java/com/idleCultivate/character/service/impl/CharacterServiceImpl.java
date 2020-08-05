package com.idleCultivate.character.service.impl;

import com.idleCultivate.character.manager.CharacterManager;
import com.idleCultivate.character.model.Character;
import com.idleCultivate.character.service.CharacterService;
import com.idleCultivate.common.model.CommonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("characterService")
public class CharacterServiceImpl implements CharacterService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    CharacterManager characterManager;

    @Override
    public CommonResult createCharacter(String userId, String name, String faction, String race, String job) {
        try {
            List<Character> charList = characterManager.listUserCharacters(userId);
            if (charList.size() >= 3) {
                return CommonResult.fail("最多创建3个角色！");
            }

            Character character = characterManager.findByName(name);
            if (character != null) {
                return CommonResult.fail("角色昵称已存在！");
            }

            characterManager.createCharacter(userId, name, faction, race, job);
            return CommonResult.success();
        } catch (Exception ex) {
            logger.error("创建角色失败：" + ex.getMessage(), ex);
            return CommonResult.fail("创建角色失败！");
        }
    }

    @Override
    public CommonResult listUserCharacters(String userId) {
        try {
            List<Character> characterList = characterManager.listUserCharacters(userId);
            return CommonResult.success("", characterList);
        } catch (Exception ex) {
            logger.error("获取用户角色列表失败：" + ex.getMessage(), ex);
            return CommonResult.fail("获取用户角色列表失败");
        }
    }

    @Override
    public CommonResult find(String id) {
        try {
            Character character = characterManager.find(id);
            return CommonResult.success("", character);
        } catch (Exception ex) {
            logger.error("读取角色失败：" + ex.getMessage(), ex);
            return CommonResult.fail("读取角色失败");
        }
    }

    @Override
    public CommonResult updateSettle(Character character) {
        try {
            characterManager.updateSettle(character);
            return CommonResult.success("", character);
        } catch (Exception ex) {
            logger.error("角色更新结算失败：" + ex.getMessage(), ex);
            return CommonResult.fail("角色更新结算失败");
        }
    }
}
