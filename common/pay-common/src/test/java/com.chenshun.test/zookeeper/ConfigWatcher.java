package com.chenshun.test.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 01:02  <p />
 * Version: V1.0  <p />
 * Description: 以观察者身份，创建一个 ActiveKeyValueStore 对象，并且在启动以后调用 read() 函数获得相关数据 <p />
 */
public class ConfigWatcher implements Watcher {

    private ActiveKeyValueStore store;

    public ConfigWatcher(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void displayConfig() throws InterruptedException, KeeperException {
        String value = store.read(ConfigUpdater.PATH, this);
        System.out.printf("Read %s as %s\n", ConfigUpdater.PATH, value);
    }

    @Override
    public void process(WatchedEvent event) {
        // 当 ConfigUpadater 更新znode时，ZooKeeper将触发一个 EventType.NodeDataChanged 的事件
        // 给观察者。 ConfigWatcher 将在他的 process() 函数中获得这个时间，并将显示读取到的最新的版本的配置数据
        if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                displayConfig();
            } catch (InterruptedException e) {
                System.err.println("Interrupted. Exiting.");
                Thread.currentThread().interrupt();
            } catch (KeeperException e) {
                System.err.printf("KeeperException: %s. Exiting.\n", e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 由于观察模式的触发是一次性的，所以每次都要调用 ActiveKeyValueStore 的 read() 方法，这样才能获得未来的更新数据。
        // 我们不能确保一定能够接受到更新通知事件，因为在接受观察事件和下一次读取之间的窗口期内，znode可能被改变 (有可能很多次)，
        // 但是client可能没有注册观察模式，所以client不会接到znode改变的通知。在配置服务中这不是一个什么问题，
        // 因为client只关心配置数据的最新版本。然而，建议读者关注一下这个潜在的问题

        ConfigWatcher configWatcher = new ConfigWatcher(args[0]);
        configWatcher.displayConfig();
        // stay alive until process is killed or thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }

}
