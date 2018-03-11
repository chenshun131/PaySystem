package com.chenshun.test.bean;

import java.io.Serializable;

/**
 * User: chenshun131 <p />
 * Time: 18/3/11 14:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class User implements Serializable {

    /** 该字段必须存在 */
    private static final long serialVersionUID = 4780096862366471996L;

    /** setter和getter可以没有 */
    private String name;

    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
