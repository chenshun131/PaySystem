package com.chenshun.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * User: mew <p />
 * Time: 18/4/16 13:13  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

    /**
     * 消费者C3接到消息后的业务处理逻辑
     *
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws Exception
     */
    @Override
    public void onEvent(TradeTransaction arg0, long arg1, boolean arg2) throws Exception {
        // do send jms message
        System.out.println("最后一个消费者C3");
    }

}
