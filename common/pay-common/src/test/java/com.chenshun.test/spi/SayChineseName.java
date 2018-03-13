package com.chenshun.test.spi;

/**
 * User: chenshun131 <p />
 * Time: 18/3/13 23:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SayChineseName implements ISayName {

    @Override
    public void say() {
        System.out.println("精通要饭");
    }

}