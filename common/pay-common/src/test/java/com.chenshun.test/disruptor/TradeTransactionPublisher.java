package com.chenshun.test.disruptor;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * User: mew <p />
 * Time: 18/4/16 13:14  <p />
 * Version: V1.0  <p />
 * Description: 创建一个发送消费事件 的类，用于发送要处理的消息 <p />
 */
public class TradeTransactionPublisher implements Runnable {

    Disruptor<TradeTransaction> disruptor;

    private CountDownLatch latch;

    private static int LOOP = 10000000;//模拟一千万次交易的发生

    public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeTransactionEventTranslator tradeTransloator = new TradeTransactionEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            System.out.println("生产者成功向Ringbuffer中发送了一条消息");
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }

}
