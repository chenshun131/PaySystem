package com.chenshun.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * User: mew <p />
 * Time: 18/4/16 13:10  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    /**
     * 消费者C1接到消息后的业务处理逻辑
     *
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        // do something....
        System.out.println("第一个消费者C1");
    }

}
