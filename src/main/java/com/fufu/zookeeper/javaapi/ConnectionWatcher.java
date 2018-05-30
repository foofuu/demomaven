package com.fufu.zookeeper.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhoujunfu on 2018/5/30.
 */
public class ConnectionWatcher implements Watcher, Runnable {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getType() + "--->" + watchedEvent.getState());
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    @Override
    public void run() {
        await();
    }
}
