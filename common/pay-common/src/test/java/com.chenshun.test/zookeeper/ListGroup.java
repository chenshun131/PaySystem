package com.chenshun.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * User: mew <p />
 * Time: 18/2/24 10:24  <p />
 * Version: V1.0  <p />
 * Description: 成员列表 <p />
 */
public class ListGroup extends ConnectionWatcher {

    public void list(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        try {
            // 调用 getChildren() 方法来获得某一个path下的子节点
            List<String> children = zk.getChildren(path, false);
            if (children.isEmpty()) {
                System.out.printf("No members in group %s\n", groupName);
                System.exit(1);
            }
            for (String child : children) {
                System.out.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            // 当节点不存在的时候就会抛出异常
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("ci-server:2181");
        listGroup.list("zoo");
        listGroup.close();
    }

}
