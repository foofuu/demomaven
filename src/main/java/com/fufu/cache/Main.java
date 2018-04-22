package com.fufu.cache;

/**
 * Created by zhoujunfu on 2018/4/11.
 */
public class Main {

    public static void main(String[] args) {
       /* LRUCache<String, Integer> lruCache = new LRUCache<>(4);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        lruCache.put("d", 4);
        lruCache.forEach((key, value) -> System.out.println(key + "-->" + value));
        lruCache.get("c");
        lruCache.forEach((key, value) -> System.out.println(key + "-->" + value));*/

       LRUCacheOne<Integer, String> cacheOne = new LRUCacheOne<>(15);
       cacheOne.put(1, "语文");
       cacheOne.put(2, "数学");
       cacheOne.put(3, "英语");
       System.out.println(cacheOne.toString());

       System.out.println("===get key 1===");
       cacheOne.get(1);
       System.out.println(cacheOne.toString());
    }
}
