package com.chenshun.test.design.factory.abstractfactory;

/**
 * User: mew <p />
 * Time: 18/4/2 11:29  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class LuxuryCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new LuxuryEngine();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }

}
