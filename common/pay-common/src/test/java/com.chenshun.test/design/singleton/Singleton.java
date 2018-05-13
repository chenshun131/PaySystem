package com.chenshun.test.design.singleton;

/**
 * User: chenshun131 <p />
 * Time: 18/5/12 13:36  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Singleton {

    private static class Single {

        private static Singleton instance;

        static {
            System.out.println("Single");
            instance = new Singleton();
        }

        public static Singleton getInstance() {
            return instance;
        }
    }

    public static Singleton getInstance() {
        return Single.getInstance();
    }

    public static void main(String[] args) {
        System.out.println("start");
        Singleton.getInstance();
    }
}
