package com.chenshun.test.object;

/**
 * User: chenshun131 <p />
 * Time: 18/3/25 15:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Obj implements Cloneable {

    private int a = 0;

    public Obj() {
        System.out.println("construct Obj");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void changeA() {
        this.a = 1;
    }

    // 将 protected 作用域改成 public
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        try {
            Obj a = new Obj();
            Obj b = (Obj) a.clone();
            b.changeA();
            System.out.println(a.getA());
            System.out.println(b.getA());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
