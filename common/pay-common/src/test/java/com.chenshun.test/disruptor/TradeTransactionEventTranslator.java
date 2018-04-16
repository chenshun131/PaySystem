package com.chenshun.test.disruptor;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * User: mew <p />
 * Time: 18/4/16 13:21  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {

    private Random random = new Random();

    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        event.setPrice(random.nextDouble() * 9999);
    }

}
