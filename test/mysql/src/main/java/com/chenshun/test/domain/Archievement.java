package com.chenshun.test.domain;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Archievement {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Archievement [id=" + id + ", name=" + name + "]";
    }

}
