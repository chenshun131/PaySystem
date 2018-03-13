package com.chenshun.test.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * User: chenshun131 <p />
 * Time: 18/3/13 23:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestSPI {

    @Test
    public void test() {
        ServiceLoader<ISayName> loaders = ServiceLoader.load(ISayName.class);
        for (ISayName sayName : loaders) {
            sayName.say();
        }
    }

}
