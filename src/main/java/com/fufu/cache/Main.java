package com.fufu.cache;

import com.fufu.spring.lifecycle.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Integer a = 5;
        if (a != null && a == 5 || a == 4) {
            System.out.println("in ");
        }

        System.out.println("健康,我的".replaceAll(",", "/"));


        List< Car> list = new ArrayList<>();

        Car car1 = new Car();
        car1.setName("xxx");
        Car car2 = new Car();
        car2.setName("yyy");

        list.add(car1);
        list.add(car2);


       String abn =  list.stream().map(Car::getName).collect(Collectors.joining(",")).toString();


        Map<String, Car> collect = list.stream().collect(Collectors.toMap(Car::getName, car -> car));



        collect.get("xxx").setPrice(123);

        System.out.println();



    }
}
