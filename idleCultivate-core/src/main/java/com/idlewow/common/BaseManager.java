package com.idlewow.common;

import com.idlewow.common.model.PageList;
import com.idlewow.common.model.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseManager<T> {
    @Autowired
    public BaseMapper<T> baseMapper;

    public void insert(T t) {
        int effected = baseMapper.insert(t);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    public void batchInsert(List<T> list) {
        int splitSize = 100;
        int index = 0;
        int total = list.size();
        while (index <= total) {
            int end = index + splitSize;
            if (end > total) {
                end = total;
            }

            List<T> sublist = list.subList(index, end);
            int effected = baseMapper.batchInsert(sublist);
            if (effected == 0) {
                throw new RuntimeException("sql effected 0 rows");
            }

            index += splitSize;
        }
    }

    public void update(T t) {
        int effected = baseMapper.update(t);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    public void delete(String id) {
        int effected = baseMapper.delete(id);
        if (effected == 0) {
            throw new RuntimeException("sql effected 0 rows");
        }
    }

    public T find(String id) {
        T t = (T)baseMapper.find(id);
        return t;
    }

    public List<T> list(QueryParam queryParam) {
        List<T> list = baseMapper.list(queryParam);
        return list;
    }

    public PageList<T> pageList(QueryParam queryParam) {
        PageList<T> pageList = new PageList<>();
        int count = baseMapper.count(queryParam);
        List<T> list = baseMapper.list(queryParam);
        pageList.setTotalCount(count);
        pageList.setRows(list);
        pageList.setPageParam(queryParam.getPageParam());
        return pageList;
    }
}
