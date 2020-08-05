package com.idlewow.character.model;

import com.alibaba.fastjson.JSON;
import com.idlewow.common.model.BaseModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Data
public class Character extends BaseModel {
    private String userId;
    private String name;
    private String faction;
    private String race;
    private String job;
    private Integer level;
    private Integer experience;
    private Integer gold;
    private Integer isHide;
    private String extraInfo;

    private static final Logger logger = LogManager.getLogger(Character.class);

    public String getMapId() {
        if (StringUtils.isNotBlank(this.extraInfo)) {
            CharacterExtraInfo characterExtraInfo = JSON.parseObject(this.extraInfo, CharacterExtraInfo.class);
            return characterExtraInfo.getMapId();
        } else {
            logger.warn("extraInfo信息为空，加载mapId失败！");
            return "";
        }
    }

    public void setMapId(String mapId) {
        CharacterExtraInfo characterExtraInfo;
        if (StringUtils.isNotBlank(this.extraInfo)) {
            characterExtraInfo = JSON.parseObject(this.extraInfo, CharacterExtraInfo.class);
            characterExtraInfo.setMapId(mapId);
        } else {
            logger.warn("extraInfo信息为空，将生成新的信息！");
            characterExtraInfo = new CharacterExtraInfo();
            characterExtraInfo.setMapId(mapId);
        }

        this.extraInfo = JSON.toJSONString(characterExtraInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return this.getId().equals(character.getId()) && this.getUserId().equals(character.getUserId()) && this.getName().equals(character.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getUserId(), this.getName());
    }
}
