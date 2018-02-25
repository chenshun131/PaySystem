package com.chenshun.test.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * User: mew <p />
 * Time: 18/2/24 10:16  <p />
 * Version: V1.0  <p />
 * Description: 加入组 <p />
 */
public class JoinGroup extends ConnectionWatcher {

    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createdPath = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created " + createdPath);
    }

    public static void main(String[] args) throws Exception {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("ci-server:2181");
        joinGroup.join("zoo", "member1");
        // 阻塞线程，在线程被强制关闭后创建的znode会自动消失
        Thread.sleep(Long.MAX_VALUE);
    }

}
