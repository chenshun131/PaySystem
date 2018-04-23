package com.chenshun.test.multipthread.test;

/**
 * User: mew <p />
 * Time: 18/4/20 10:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class User {

    private String name;

    public volatile int old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

}
