package com.fufu.zookeeper.javaapi;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoujunfu on 2018/5/30.
 */
public class ApiOperatorDemo {

    private static final String connectString = "";

    private static final int sessionTimeout = 5000;

    private ZooKeeper zooKeeper;

    public ApiOperatorDemo() {
        try {
            this.zooKeeper = new ZooKeeper(connectString, sessionTimeout, new ConnectionWatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步创建节点
     * @param path
     * @param data
     */
    public void createSync(String path, String data) {
        if (StringUtils.isBlank(path) || StringUtils.isBlank(data)) {
            throw new RuntimeException("createSync error, null path or data!");
        }
        try {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步创建节点
     * @param path
     * @param data
     */
    public void createAsync(String path, String data) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if (StringUtils.isBlank(path) || StringUtils.isBlank(data)) {
            throw new RuntimeException("createAsync error, null path or data!");
        }

        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String pth, Object ctx, String name) {
                System.out.println("createAsync result: [" + rc + ", " + pth + ", " + ctx + ", " + name);
                countDownLatch.countDown();
            }
        }, "context");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步获取子节点
     * @param path
     * @return
     */
    public List<String> getChildrenSync(String path) {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("getChildrenSync error, null path!");
        }
        try {
            List<String> children = zooKeeper.getChildren(path, false);
            children.forEach(System.out::println);
            return children;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 异步获取子节点
     * @param path
     */
    public void getChildrenAsync(String path) {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("getChildrenAsync error, null path!");
        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper.getChildren(path, false, new AsyncCallback.ChildrenCallback() {
            @Override
            public void processResult(int rc, String pth, Object ctx, List<String> list) {
                System.out.println("getChildrenAsync result: [" + rc + ", " + pth + ", " + ctx);
                list.forEach(System.out::println);
                countDownLatch.countDown();
            }
        },"context");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 同步获取数据
     * @param path
     * @return
     */
    public String getDataSync(String path) {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("getDataSync error, null path!");
        }

        try {
            String result = new String(zooKeeper.getData(path, false, null));
            System.out.println("getDataSync, " + result);
            return result;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 异步获取数据
     * @param path
     */
    public void getDataAsync(String path) {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("getDataAsync error, null path!");
        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper.getData(path, false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String pth, Object context, byte[] data, Stat stat) {
                System.out.println("getDataAsync rc: " + rc + ", path: " + pth + ", data: " + new String(data) + ", context: " + context);
                System.out.println("getDataAsync czxID: " + stat.getCzxid() + ", mzxID: " + stat.getMzxid() + ", version: " + stat.getVersion());
                countDownLatch.countDown();
            }
        }, "context");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步更新数据
     * @param path
     * @param data
     * @return
     */
    public Stat setDataSync(String path, String data) {
        if (StringUtils.isBlank(path) || StringUtils.isBlank(data)) {
            throw new RuntimeException("setDataSync error, null path or data!");
        }
        try {
            return zooKeeper.setData(path, data.getBytes(), -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步更新数据
     * @param path
     * @param data
     */
    public void setDataAsync(String path, String data) {
        if (StringUtils.isBlank(path) || StringUtils.isBlank(data)) {
            throw new RuntimeException("setDataAsync error, null path or data!");
        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper.setData(path, data.getBytes(), -1, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println("setDataAsync rc: " + rc + ", path: " + path + ", stat: " + stat);
                countDownLatch.countDown();
            }
        }, "context");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步检测节点是否存在
     * @param path
     * @return
     */
    public boolean existsSync(String path) {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("existsSync error, null path or data!");
        }

        Stat stat = null;
        try {
            stat = zooKeeper.exists(path, false);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stat == null ? false : true;
    }

    public void existsAsync(String path) {

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        ApiOperatorDemo apiOperatorDemo = new ApiOperatorDemo();
        apiOperatorDemo.getDataAsync("/MyConfig");
    }
}
