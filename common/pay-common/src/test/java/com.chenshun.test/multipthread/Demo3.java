package com.chenshun.test.multipthread;

/**
 * User: mew <p />
 * Time: 18/4/10 09:29  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Demo3 {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("runnable")) {
            @Override
            public void run() {
                // 此处是覆盖，优先执行这个 run方法
                System.out.println("sub");

                // 调用父类中的 run方法
                super.run();
            }
        }.start();
    }

}
