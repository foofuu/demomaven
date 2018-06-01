package com.fufu.zookeeper.javaapi;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by zhoujunfu on 2018/5/31.
 */
public class DataWatcher implements Watcher, Runnable {
    private static final String connectString = "";

    private static final int sessionTimeout = 5000;

    private static final String dataPath = "/MyConfig";

    private ZooKeeper zooKeeper;

    public DataWatcher() {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, this);
            if (zooKeeper != null) {
                if (zooKeeper.exists(dataPath, this) == null ) {
                    zooKeeper.create(dataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
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

    public void printData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(dataPath, this, null);
        String dataStr = new String(data);
        System.out.printf("Current Data @ ZK Path %s: %s", dataPath, dataStr);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            try {
                printData();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();

        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        DataWatcher dataWatcher = new DataWatcher();
        dataWatcher.run();
    }
}
