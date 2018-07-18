package com.fufu.concurrent;

/**
 * Created by zhoujunfu on 2018/7/12.
 */
public class SyncCodeBlock {
    public int i;

    public void syncTask(){
        synchronized (this){
            i++;
        }
    }
}
