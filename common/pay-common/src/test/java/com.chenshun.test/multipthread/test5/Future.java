package com.chenshun.test.multipthread.test5;

/**
 * User: chenshun131 <p />
 * Time: 18/4/27 22:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Future {

    private Product product;

    private boolean down;

    public synchronized void setProduct(Product product) {
        if (down) {
            return;
        }

        this.product = product;
        this.down = true;
        notifyAll();
    }

    public synchronized Product get() {
        while (!down) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

}
