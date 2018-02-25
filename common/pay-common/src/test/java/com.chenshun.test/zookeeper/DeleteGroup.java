package com.chenshun.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * User: mew <p />
 * Time: 18/2/24 14:21  <p />
 * Version: V1.0  <p />
 * Description: 删除分组 <p />
 */
public class DeleteGroup extends ConnectionWatcher {

    public void delete(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            for (String child : children) {
                zk.delete(path + "/" + child, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("ci-server:2181");
        deleteGroup.delete("zoo");
        deleteGroup.close();
    }

}
