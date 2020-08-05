package com.idlewow.character.service;

import com.idlewow.character.model.Character;
import com.idlewow.common.model.CommonResult;

public interface CharacterService {
    CommonResult createCharacter(String userId, String name, String faction, String race, String job);

    CommonResult listUserCharacters(String userId);

    CommonResult find(String id);

    CommonResult updateSettle(Character character);
}
