package com.chenshun.test.design.proxy.dynamic;

/**
 * User: mew <p />
 * Time: 18/4/4 09:22  <p />
 * Version: V1.0  <p />
 * Description: 真实角色（明星艺人） <p />
 */
public class RealStar implements Star {

    @Override
    public void confer() {
        System.out.println("RealStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("RealStar.signContract()");
    }

    @Override
    public void bookTicket() {
        System.out.println("RealStar.bookTicket()");
    }

    @Override
    public void sing() {
        // 真实角色的操作 : 真正的业务逻辑
        System.out.println("张学友.sing()");
    }

    @Override
    public void collectMoney() {
        System.out.println("RealStar.collectMoney()");
    }

}
