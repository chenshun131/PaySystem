package com.chenshun.test.ehcache.service;

import com.chenshun.test.ehcache.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "HelloWorldCache")
public class EhcacheServiceImpl implements EhcacheService {

    // value的值和ehcache.xml中的配置保持一致
    @Cacheable(key = "#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Cacheable(key = "#key")
    @Override
    public String getDataFromDB(String key) {
        System.out.println("从数据库中获取数据...");
        return key + ":" + String.valueOf(Math.round(Math.random() * 1000000));
    }

    @CacheEvict(value = "HelloWorldCache", key = "#key")
    @Override
    public void removeDataAtDB(String key) {
        System.out.println("从数据库中删除数据");
    }

    @CachePut(value = "HelloWorldCache", key = "#key")
    @Override
    public String refreshData(String key) {
        System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random() * 1000000));
    }

    @Cacheable(value = "UserCache", key = "'user:' + #userId")
    public User findById(String userId) {
        System.out.println("模拟从数据库中查询数据");
        return new User("1", "mengdee");
    }

    @Cacheable(value = "UserCache", condition = "#userId.length()<12")
    public boolean isReserved(String userId) {
        System.out.println("UserCache:" + userId);
        return false;
    }

    // 清除掉UserCache中某个指定key的缓存
    @CacheEvict(value = "UserCache", key = "'user:' + #userId")
    public void removeUser(String userId) {
        System.out.println("UserCache remove:" + userId);
    }

    // 清除掉UserCache中全部的缓存
    @CacheEvict(value = "UserCache", allEntries = true)
    public void removeAllUser() {
        System.out.println("UserCache delete all");
    }

}
