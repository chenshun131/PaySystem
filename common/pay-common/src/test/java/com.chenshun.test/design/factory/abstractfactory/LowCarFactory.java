package com.chenshun.test.design.factory.abstractfactory;

/**
 * User: mew <p />
 * Time: 18/4/2 11:29  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class LowCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new LowEngine();
    }

    @Override
    public Seat createSeat() {
        return new LowSeat();
    }

    @Override
    public Tyre createTyre() {
        return new LowTyre();
    }

}
