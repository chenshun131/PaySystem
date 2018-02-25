package com.chenshun.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * User: chenshun131 <p />
 * Time: 18/2/25 00:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ConfigUpdater {

    public static final String PATH = "/config";

    private ActiveKeyValueStore store;

    private Random random = new Random();

    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws InterruptedException, KeeperException {
        // 不断的循环运行，使用一个随机数不断的随机更新 /config znode上的值
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.printf("Set %s to %s\n", PATH, value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args) throws Exception {
        ConfigUpdater configUpdater = new ConfigUpdater(args[0]);
        configUpdater.run();


        while (true) {
            try {
                ConfigUpdater configUpdater2 = new ConfigUpdater(args[0]);
                configUpdater2.run();
            } catch (KeeperException.SessionExpiredException e) {
                // start a new session
            } catch (KeeperException e) {
                // already retried, so exit
                e.printStackTrace();
                break;
            }
        }
    }

}
