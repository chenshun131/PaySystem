package com.chenshun.test.spi;

/**
 * User: chenshun131 <p />
 * Time: 18/3/13 23:10  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SayEnglishName implements ISayName {

    @Override
    public void say() {
        System.out.println("Proficient in begging");
    }

}
