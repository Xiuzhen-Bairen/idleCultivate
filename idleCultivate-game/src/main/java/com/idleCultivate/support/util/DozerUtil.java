package com.idleCultivate.support.util;

import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerUtil {
    public static <T, V> List<V> mapList(List<T> list, Class<V> targetClass, Mapper mapper) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            List<V> result = new ArrayList<>();
            for (T source : list) {
                V target = mapper.map(source, targetClass);
                result.add(target);
            }

            return result;
        }
    }
}
