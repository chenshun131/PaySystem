package com.chenshun.test.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * User: chenshun131 <p />
 * Time: 18/5/6 18:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class RedisCluster {

    public static void main(String[] args) throws IOException {
        // 数据库链接池配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMinIdle(20);
        config.setMaxWaitMillis(6 * 1000);
        config.setTestOnBorrow(true);

        // Redis集群的节点集合
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("ci-server", 7111));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7112));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7113));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7114));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7115));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7117));
        jedisClusterNodes.add(new HostAndPort("ci-server", 7118));

        // 根据节点集创集群链接对象
        // JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        // 集群各节点集合，超时时间，最多重定向次数，链接池
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 2000, 100, "123456", config);
        System.out.println("value : " + jedisCluster.get(""));

        int num = 1000;
        String key = "wusc";
        String value = "";
        for (int i = 1; i <= num; i++) {
            // 存数据
            jedisCluster.set(key + i, "WuShuicheng" + i);
            // 取数据
            value = jedisCluster.get(key + i);
            System.out.println(key + i + "=" + value);
            // 删除数据
            // jedisCluster.del(key+i);
            // value = jedisCluster.get(key+i);
            // logger.info(key+i + "=" + value);
        }
        jedisCluster.close();
    }

}
