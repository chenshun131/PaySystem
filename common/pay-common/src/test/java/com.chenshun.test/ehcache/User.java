package com.chenshun.test.ehcache;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 16:50  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class User {

    private String id;

    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
