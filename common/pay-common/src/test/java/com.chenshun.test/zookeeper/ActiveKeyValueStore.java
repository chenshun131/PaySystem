package com.chenshun.test.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 00:46  <p />
 * Version: V1.0  <p />
 * Description: 创建配置 配置服务  <p />
 */
public class ActiveKeyValueStore extends ConnectionWatcher {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    /** 最大尝试次数参数 */
    private static final int MAX_RETRIES = 10;

    /** 每次尝试间隔的休眠时长 */
    private static final long RETRY_PERIOD_SECONDS = 1000;

    /**
     * 将给定的 key-value 对写入到 ZooKeeper 中
     *
     * @param path
     * @param value
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void write(String path, String value) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(path, false);
        if (stat == null) {
            // 如果 znode 不存在则创建
            zk.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            zk.setData(path, value.getBytes(CHARSET), -1);
        }
    }

    /**
     * 从整体上看两个 write() 方法都应该是幂等方法，所以可以不断的尝试执行它。write2() 是一个改进版，实现在循环中不断的尝试write操作
     *
     * @param path
     * @param value
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void write2(String path, String value) throws InterruptedException, KeeperException {
        int retries = 0;
        while (true) {
            try {
                Stat stat = zk.exists(path, false);
                if (stat == null) {
                    zk.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } else {
                    zk.setData(path, value.getBytes(CHARSET), stat.getVersion());
                }
                return;
            } catch (KeeperException.SessionExpiredException e) {
                throw e;
            } catch (KeeperException e) {
                if (retries++ == MAX_RETRIES) {
                    throw e;
                }
                // sleep then retry
                TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS);
            }
        }
    }

    /**
     * 读取 /config 上的值
     *
     * @param path
     * @param watcher
     * @return
     * @throws InterruptedException
     * @throws KeeperException
     */
    public String read(String path, Watcher watcher) throws InterruptedException, KeeperException {
        // ZooKeeper的 getData() 方法的参数包含：path，一个Watcher对象和一个Stat对象。Stat对
        // 象中含有从 getData() 返回的值，并且负责接收回调信息。这种方式下，调用者不仅可以获
        // 得数据，还能够获得znode的metadata。
        byte[] data = zk.getData(path, watcher, null/*stat*/);
        return new String(data, CHARSET);
    }

}
