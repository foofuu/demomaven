package com.fufu.proxy;

/**
 * Created by zhoujunfu on 2018/9/6.
 * 经纪人
 */
public class Agent implements ShowService{

    private Star star;

    public Agent(Star star) {
        this.star = star;
    }

    private void getMoney() {
        System.out.println("get money");
    }

    private void writeReceipt() {
        System.out.println("write receipt");
    }

    @Override
    public void sing(String songName) {
        // 唱歌开始前收钱
        getMoney();
        // 明星开始唱歌
        star.sing(songName);
        // 唱歌结束后开发票
        writeReceipt();
    }

    @Override
    public void dance() {
        // 跳舞开始前收钱
        getMoney();
        // 明星开始跳舞
        star.dance();
        // 跳舞结束后开发票
        writeReceipt();
    }

    public static void main(String[] args) {
        Agent agent = new Agent(new Star("Eminem"));
        agent.sing("Mockingbird");
    }


}
