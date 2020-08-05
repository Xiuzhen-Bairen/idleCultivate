package com.idleCultivate.character.mapper;

import java.util.List;
import com.idleCultivate.character.model.Character;

public interface CharacterMapper {
    Character find(String id);

    Character findByName(String name);

    int createCharacter(Character character);

    List<Character> listUserCharacters(String userId);

    int updateSettle(Character character);
}
