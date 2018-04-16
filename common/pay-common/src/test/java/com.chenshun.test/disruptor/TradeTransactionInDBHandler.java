package com.chenshun.test.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * User: mew <p />
 * Time: 18/4/16 13:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        this.onEvent(event);
    }

    /**
     * 消费者C2接到消息后的具体的业务 处理逻辑
     *
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        // 这里做具体的消费业务逻辑，即就是接到消息后的业务处理逻辑
        event.setId(UUID.randomUUID().toString());//随机简单生成下ID 并且打印出来
        System.out.println("第二个消费者C2消费了消息");
    }

}
