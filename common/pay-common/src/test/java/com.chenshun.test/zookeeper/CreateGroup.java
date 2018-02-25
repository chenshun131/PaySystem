package com.chenshun.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 00:48  <p />
 * Version: V1.0  <p />
 * Description: 使用zookeeper的Java API来创建一个 /zoo 的组节点，Watcher 实例负责接收 Zookeeper数据变化时产生的事件回调 <p />
 */
public class CreateGroup implements Watcher {

    /** 超时时间，单位毫秒 */
    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * 设置主访问地址
     *
     * @param hosts
     * @throws IOException
     * @throws InterruptedException
     */
    public void connect(String hosts) throws IOException, InterruptedException {
        // 在连接函数中创建 zookeeper 实例，然后建立与服务器的连接。建立连接函数会立即返回，所以需要等待连接建立成功后再进行其他的操作。
        // 使用 CountDownLatch 来阻塞当前线程，直到zookeeper准备就绪
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) { // Watcher interface
        if (event.getState() == KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    /**
     * 组名
     *
     * @param groupName
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        // create 方法通过指定路径创建一个节点
        // 参数包括 : 一是znode的path；二是znode的内容(一个二进制数组)；三是一个access control list(ACL，访问控制列表，这里使用完全开放模式)，最后是 znode的性质
        // znode的性质分为ephemeral和persistent两种。ephemeral性质的znode在创建他的客户端的
        // 会话结束，或者客户端以其他原因断开与服务器的连接时，会被自动删除。而persistent性质
        // 的znode就不会被自动删除，除非客户端主动删除，而且不一定是创建它的客户端可以删除它，其他客户端也可以删除它
        String createdPath = zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created " + createdPath);
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
        // 建了一个 CreateGroup 的对象，然后调用 connect()方法，通过 zookeeper 的 API 与 zookeeper服务器连接
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("ci-server:2181");
        createGroup.create("zoo");
        createGroup.close();
    }

}
