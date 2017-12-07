package wusc.edu.pay.common.core.mybatis.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import wusc.edu.pay.common.utils.cache.redis.RedisUtils;

/**
 * ClassName: CachePool <br/>
 * Function:  redis 初始化与连接池实现<br/>
 * <per>
 * 由于需结合Mybatis实现 不与Spring redis注解实现混用。
 * 与Spring redis注解实现 各独立实现各自功能。
 * </per>
 * date: 2014-8-13 下午4:49:36 <br/>
 *
 * @author laich
 */
public class CachePool {

    private JedisSentinelPool pool;

    private static final CachePool cachePool = new CachePool();

    /**
     * 单例模式
     */
    public static CachePool getInstance() {
        return cachePool;
    }

    private CachePool() {
        pool = RedisUtils.getJedisSentinelPool();
    }

    public Jedis getJedis() {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = pool.getResource();
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
        jedis = pool.getResource();
        return jedis;
    }

    public JedisSentinelPool getJedisPool() {
        return this.pool;
    }

}
