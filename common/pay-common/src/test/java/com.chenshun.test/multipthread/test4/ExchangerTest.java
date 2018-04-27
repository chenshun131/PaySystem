package com.chenshun.test.multipthread.test4;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 21:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A"; // A录入银行流水数据
                exgr.exchange(A);
            } catch (InterruptedException ignored) {
            }
        });
        threadPool.execute(() -> {
            try {
                String B = "银行流水B"; // B录入银行流水数据
                String A = exgr.exchange("B");
                System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是：" + A + ",B录入是：" + B);
            } catch (InterruptedException ignored) {
            }
        });
        threadPool.shutdown();
        // 打印
        // A和B数据是否一致：false,A录入的是：银行流水A,B录入是：银行流水B
    }

}
