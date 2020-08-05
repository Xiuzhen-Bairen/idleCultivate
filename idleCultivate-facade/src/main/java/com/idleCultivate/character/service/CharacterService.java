package com.idleCultivate.character.service;

import com.idleCultivate.character.model.Character;
import com.idleCultivate.common.model.CommonResult;

public interface CharacterService {
    CommonResult createCharacter(String userId, String name, String faction, String race, String job);

    CommonResult listUserCharacters(String userId);

    CommonResult find(String id);

    CommonResult updateSettle(Character character);
}
