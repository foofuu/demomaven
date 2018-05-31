package com.fufu.zookeeper.javaapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by zhoujunfu on 2018/5/31.
 */
public class DataWatcher implements Watcher, Runnable {
    private ZooKeeper zooKeeper;
    private String dataPath;

    public DataWatcher(ZooKeeper zooKeeper, String dataPath) {
        this.zooKeeper = zooKeeper;
        this.dataPath = dataPath;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.printf("Event Received: %s", watchedEvent.toString());
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged){
            try {
                printData();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(dataPath, this, null);
        System.out.printf("Current Data @ ZK Path %s: %s", dataPath, new String(data));
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
