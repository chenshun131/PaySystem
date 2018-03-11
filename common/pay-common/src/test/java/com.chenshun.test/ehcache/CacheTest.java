package com.chenshun.test.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 16:26  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class CacheTest {

    public static void main(String[] args) {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("/Users/chenshun131/Desktop/AllMyFile/Study_CodeRepository/GitHub/PaySystem/common/pay-common/src/test/resources/ehcache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");

        // 4. 将元素添加到缓存
        cache.put(element);

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        // 6. 删除元素
        cache.remove("key1");

        // 添加对象数据
        Dog dog = new Dog(1L, "taidi", 2);
        Element element2 = new Element("taidi", dog);
        cache.put(element2);
        // 获取对象数据
        Element value2 = cache.get("taidi");
        Dog dog2 = (Dog) value2.getObjectValue();
        System.out.println(dog2.toString());
        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();
        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }

}
