package com.chenshun.test.testoverride;

/**
 * User: mew <p />
 * Time: 18/4/12 09:24  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Father {

    public String name = "Father'name";

    public void method() {
        System.out.println("当前名方法 : " + this.getClass());
    }

}
