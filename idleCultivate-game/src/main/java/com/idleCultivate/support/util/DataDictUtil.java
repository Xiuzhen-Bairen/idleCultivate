package com.idleCultivate.support.util;

import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.datadict.model.DataDict;
import com.idleCultivate.datadict.model.DataType;
import com.idleCultivate.datadict.service.DataDictService;
import com.idleCultivate.query.model.DataDictQueryParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataDictUtil implements Serializable {
    private static final Logger logger = LogManager.getLogger(DataDictUtil.class);

    @Autowired
    DataDictService dataDictService;

    public static Map<String, Map<String, String>> ConfigMap = new HashMap<>();

    public void initialize() {
        DataDictQueryParam dataDictQueryParam = new DataDictQueryParam();
        CommonResult commonResult = dataDictService.list(dataDictQueryParam);
        if (commonResult.isSuccess()) {
            List<DataDict> dataDictList = (List<DataDict>) commonResult.getData();
            for (DataDict dataDict : dataDictList) {
                if (ConfigMap.containsKey(dataDict.getParentCode())) {
                    ConfigMap.get(dataDict.getParentCode()).put(dataDict.getCode(), dataDict.getValue());
                } else {
                    Map map = new HashMap();
                    map.put(dataDict.getCode(), dataDict.getValue());
                    ConfigMap.put(dataDict.getParentCode(), map);
                }
            }
        } else {
            logger.error("缓存加载失败！");
        }
    }

    public static Map<String, String> zone() {
        return ConfigMap.get(DataType.Zone.getCode());
    }

    public static Map<String, String> occupy() {
        return ConfigMap.get(DataType.Occupy.getCode());
    }

    public static Map<String, String> race() {
        return ConfigMap.get(DataType.Race.getCode());
    }

    public static Map<String, String> job() {
        return ConfigMap.get(DataType.Job.getCode());
    }

    public static Map<String, String> itemType() {
        return ConfigMap.get(DataType.ItemType.getCode());
    }

    public static Map<String, String> sectType() {
        return ConfigMap.get(DataType.SectType.getCode());
    }

    public static Map<String, String> skillType() {
        return ConfigMap.get(DataType.SkillType.getCode());
    }
}
