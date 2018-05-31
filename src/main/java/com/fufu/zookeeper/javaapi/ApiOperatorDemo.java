package com.fufu.zookeeper.javaapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoujunfu on 2018/5/30.
 */
public class ApiOperatorDemo {

    private static final String connectString = "198.13.56.226:2181";

    private static final int sessionTimeout = 5000;

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) {
        //ConnectionWatcher watcher = new ConnectionWatcher();
        DataWatcher dataWatcher = new DataWatcher(zooKeeper, "/myzk");
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, dataWatcher);
            TimeUnit.SECONDS.sleep(5);
            zooKeeper.setData("/myzk", "hahae".getBytes(), -1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
