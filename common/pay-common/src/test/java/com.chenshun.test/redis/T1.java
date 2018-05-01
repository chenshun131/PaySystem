package com.chenshun.test.redis;

import redis.clients.jedis.Jedis;

/**
 * User: chenshun131 <p />
 * Time: 18/4/29 11:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    private Jedis redis;

//    String get(final String key) {
//        V v = redis.get(key);
//        String value = v.getValue();
//        long timeout = v.getTimeout();
//        if (v.timeout <= System.currentTimeMillis()) {
//            // 异步更新后台异常执行
//            threadPool.execute(new Runnable() {
//                public void run() {
//                    String keyMutex = "mutex:" + key;
//                    if (redis.setnx(keyMutex, "1")) {
//                        // 3 min timeout to avoid mutex holder crash
//                        redis.expire(keyMutex, 3 * 60);
//                        String dbValue = db.get(key);
//                        redis.set(key, dbValue);
//                        redis.del(keyMutex);
//                    }
//                }
//            });
//        }
//        return value;
//    }

}
