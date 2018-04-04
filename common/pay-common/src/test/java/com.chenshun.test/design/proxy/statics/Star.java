package com.chenshun.test.design.proxy.statics;

/**
 * User: mew <p />
 * Time: 18/4/4 08:51  <p />
 * Version: V1.0  <p />
 * Description: 抽象角色：提供代理角色和真实角色对外提供的公共方法 <p />
 */
public interface Star {

    /**
     * 面谈
     */
    void confer();

    /**
     * 签合同
     */
    void signContract();

    /**
     * 订票
     */
    void bookTicket();

    /**
     * 唱歌
     */
    void sing();

    /**
     * 收尾款
     */
    void collectMoney();

}
