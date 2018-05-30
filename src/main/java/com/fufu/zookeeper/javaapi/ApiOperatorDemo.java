package com.fufu.zookeeper.javaapi;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by zhoujunfu on 2018/5/30.
 */
public class ApiOperatorDemo {

    private static final String connectString = "";

    private static final int sessionTimeout = 5000;

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) {
        ConnectionWatcher watcher = new ConnectionWatcher();
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeout, watcher);
            watcher.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
