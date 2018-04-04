package com.chenshun.test.design.proxy.statics;

/**
 * User: mew <p />
 * Time: 18/4/4 08:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ProxyStar implements Star {

    /** 真实对象的引用(明星) */
    private Star star;

    /**
     * 通过构造器给真实角色赋值
     *
     * @param star
     */
    public ProxyStar(Star star) {
        this.star = star;
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar.signContract()");
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar.bookTicket()");
    }

    @Override
    public void sing() {
        // 真实对象的操作（明星唱歌）
        star.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar.collectMoney()");
    }

}
