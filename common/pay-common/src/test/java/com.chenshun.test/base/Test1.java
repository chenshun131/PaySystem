package com.chenshun.test.base;

/**
 * User: chenshun131 <p />
 * Time: 18/4/1 10:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test1 {

    final int x1 = 10000;

    final int x2;

    final int x3;

//    final int x4; // 此处将会显示 Variable 'x4' might not have been initialized

    {
        x2 = 20000;
    }

    public Test1() {
        this.x3 = 3000;
    }

}
