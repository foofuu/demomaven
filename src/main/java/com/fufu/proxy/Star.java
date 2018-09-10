package com.fufu.proxy;

import org.springframework.stereotype.Component;

/**
 * Created by zhoujunfu on 2018/9/6.
 * 明星类
 */
@Component
public class Star implements ShowService{
    private String name;

    @Override
    public void sing(String songName) {
        System.out.println(this.name + " sing a song: " + songName);
    }

    @Override
    public void dance() {
        System.out.println(this.name + "dance");
    }

    public Star(String name) {
        this.name = name;
    }

    public Star() {
    }

    public static void main(String[] args) {
        Star star = new Star("Eminem");
        star.sing("Mockingbird");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
