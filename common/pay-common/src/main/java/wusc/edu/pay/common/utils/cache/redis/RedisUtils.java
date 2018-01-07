package wusc.edu.pay.common.utils.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @描述: Redis缓存工具类.
 * @作者: ZhangWensi.
 * @创建: 2014-6-13,上午11:19:24
 * @版本: V1.0
 */
public class RedisUtils {

    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    /** 默认缓存时间(单位秒 设置成一个钟) */
    private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;

    /** 连接池 **/
    private static JedisSentinelPool jedisSentinelPool;

    /**
     * 释放redis资源
     *
     * @param jedis
     */
    private static void releaseResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 删除Redis中的所有key
     */
    public static void flushAll() {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.flushAll();
        } catch (Exception e) {
            logger.error("Cache清空失败：" + e);
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) <br/>
     *
     * @param key
     *         键 <br/>
     * @param object
     *         缓存对象 <br/>
     * @return true or false <br/>
     */
    public static Boolean save(Object key, Object object) {
        return save(key, object, DEFAULT_CACHE_SECONDS);
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key
     *         键 . <br/>
     * @param object
     *         缓存对象 . <br/>
     * @param seconds
     *         过期时间（单位为秒）.<br/>
     * @return true or false .
     */
    public static Boolean save(Object key, Object object, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key
     *         键.<br/>
     * @return Object <br/>
     */
    public static Object get(Object key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            byte[] obj = jedis.get(SerializeUtils.serialize(key));
            return obj == null ? null : SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param key
     * @return
     */
    public static Boolean del(Object key) {
        Jedis jedis = null;
        try {
            logger.debug("key : " + key);
            jedis = jedisSentinelPool.getResource();
            jedis.del(SerializeUtils.serialize(key));
            return true;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     */
    public static Boolean del(Object... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.del(SerializeUtils.serialize(keys));
            return true;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * @param key
     * @param seconds
     *         超时时间（单位为秒）
     * @return
     */
    public static Boolean expire(Object key, int seconds) {

        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("Cache设置超时时间失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Boolean addHash(String key, Object field, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            jedis.hset(SerializeUtils.serialize(key), SerializeUtils.serialize(field), SerializeUtils.serialize(value));
            return true;
        } catch (Exception e) {
            logger.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从指定hash中拿一个对象
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            byte[] obj = jedis.hget(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("Cache读取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从hash中删除指定filed的值
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean delHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return result == 1;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 拿到缓存中所有符合pattern的key
     *
     * @param pattern
     * @return
     */
    public static Set<byte[]> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.keys(("*" + pattern + "*").getBytes());
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return new HashSet<>();
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 获得hash中的所有key value
     *
     * @param key
     * @return
     */
    public static Map<byte[], byte[]> getAllHash(Object key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.hgetAll(SerializeUtils.serialize(key));
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(Object key) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis = jedisSentinelPool.getResource();
            result = jedis.exists(SerializeUtils.serialize(key));
            return result;
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
        RedisUtils.jedisSentinelPool = jedisSentinelPool;
    }

    public static JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }

}
