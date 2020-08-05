package com.idlewow.game.hub.message.dto.server;

import com.idlewow.game.hub.message.vo.character.CharacterVO;
import lombok.Data;

@Data
public final class SLoadCharacterMessage extends ServerMessage {
    private CharacterVO character;
}
