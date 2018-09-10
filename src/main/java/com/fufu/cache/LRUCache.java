package com.fufu.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoujunfu on 2018/4/11.
 * LRU缓存，通过继承LinkedHashMap实现
 */
public class LRUCache<K,V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;


    public LRUCache(int max_cache_size) {
        // 设置大小，使其永远无法扩容
        super((int)Math.ceil(max_cache_size/0.75), 0.75f, true);
        MAX_CACHE_SIZE = max_cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }
}
