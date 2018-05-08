package wusc.edu.pay.common.core.mybatis.cache.redis;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import wusc.edu.pay.common.utils.cache.redis.SerializeUtils;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: RedisCache <br/>
 * Function: Mybatis 接口 实现 redis 功能 类<br/>
 * date: 2014-8-13 下午5:12:27 <br/>
 * <per>
 * 1 ，只有在SQLMap.xml 文件中添加了 缓存实现类才能生效如下：
 * <cache eviction="LRU" type="wusc.edu.pay.common.utils.cache.redis.mybatisImpl.MybatiesRedisCache" />
 * 2， 必须是在事务正常提交下才会触发putObject(Object key, Object value) 以列队方式加入缓存
 * 3，默认的SQLMAP.xml  <select ... flushCache="false" useCache="true"/>
 * <insert ... flushCache="true"/>
 * <update ... flushCache="true"/>
 * <delete ... flushCache="true"/>
 * flushCache= 'true'  表示当前操作 会清空本接口命名空间下的所有缓存
 * 可更改 <select ... useCache="false"> 表示当前操作禁用缓存数据
 * </per>
 *
 * @author laich
 */
public class RedisCache implements Cache {

    private static Logger log = LoggerFactory.getLogger(RedisCache.class);

    /** The ReadWriteLock. 什么作用 */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        log.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        Jedis jedis = null;
        int result = 0;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            result = Integer.valueOf(jedis.dbSize().toString());
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (log.isDebugEnabled()) {
            log.debug("putObject:" + key.hashCode() + "=" + value);
        }
        if (log.isInfoEnabled()) {
            log.info("put to redis sql :" + key.toString());
        }
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            jedis.set(SerializeUtils.serialize(key.hashCode()), SerializeUtils.serialize(value));
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Object getObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            System.out.println(key.hashCode());
            System.out.println(SerializeUtils.serialize(key.hashCode()));
            System.out.println(jedis.get(SerializeUtils.serialize(key.hashCode())));
            value = SerializeUtils.unSerialize(jedis.get(SerializeUtils.serialize(key.hashCode())));
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("getObject:" + key.hashCode() + "=" + value);
        }
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            value = jedis.expire(SerializeUtils.serialize(key.hashCode()), 0);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
        } catch (Exception e) {
            e.printStackTrace();
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("getObject:" + key.hashCode() + "=" + value);
        }
        return value;
    }

    @Override
    public void clear() {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CachePool.getInstance().getJedis();
            jedis.flushDB();
            jedis.flushAll();
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            if (borrowOrOprSuccess && jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
