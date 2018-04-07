package com.chenshun.test.jvm;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 13:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class StackAllocation {

    public StackAllocation obj;

    /**
     * 方法返回 StackAllocation对象，发生逃逸
     *
     * @return
     */
    public StackAllocation getInstance() {
        return obj == null ? new StackAllocation() : obj;
    }

    /**
     * 为成员属性赋值，发生逃逸
     */
    public void setStackAllocation() {
        this.obj = new StackAllocation();
    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useStackAllocation() {
        StackAllocation s = new StackAllocation();
    }

    /**
     * 引用成员变量值，会发生逃逸
     */
    public void useStackAllocation2() {
        StackAllocation s = getInstance();
    }

}
