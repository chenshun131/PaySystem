package com.chenshun.test.object;

/**
 * User: chenshun131 <p />
 * Time: 18/3/25 16:25  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test {

    private static void testClassloader(String wholeNamePoint) {
        Class<?> point;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            point = loader.loadClass(wholeNamePoint);
            // demo = ClassloaderAndForNameTest.class.getClassLoader().loadClass(wholeNamePoint); // 这个也是可以的
            System.out.println("point   " + point.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testForName(String wholeNamePoint) {
        try {
            Class point = Class.forName(wholeNamePoint);
            System.out.println("point   " + point.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test1() {
        String wholeNamePoint = "com.chenshun.test.object.Point";
        System.out.println("下面是测试Classloader的效果");
        testClassloader(wholeNamePoint);
        System.out.println("----------------------------------");
        System.out.println("下面是测试Class.forName的效果");
        testForName(wholeNamePoint);
    }

}
