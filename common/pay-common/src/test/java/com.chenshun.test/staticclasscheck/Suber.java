package com.chenshun.test.staticclasscheck;

/**
 * User: chenshun131 <p />
 * Time: 18/1/30 22:04  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Suber extends Super {

    public void doSuber() {
        System.out.println("天天编码");
    }

    public static void main(String[] args) {
        System.out.println(new Suber().me().toString());
        Super supe1 = new Suber().me();
        // supe1.doSuber(); // 错误 编译器认定supe1 指向的时 Super，其没有 doSuber() 方法
        supe1.doSuper(); // 正确
        ((Suber) new Suber().me()).doSuber(); // supe1 实际上是 Suber 类，强转可行

        // ((Brother) (new Suber().me())).beBad(); // 在Eclipse编译器成功 运行期会报 java.lang.ClassCastException: Suber cannot be cast to Brother
        // 在 Intellij 中会直接检查出错误
    }

}
