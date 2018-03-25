package com.chenshun.test.object;

import java.io.*;

/**
 * User: chenshun131 <p />
 * Time: 18/3/25 16:06  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class People implements Serializable {

    private static final long serialVersionUID = 8561166444504657722L;

    private String name;

    public People() {
        this.name = "6点A君";
        System.out.println("construct people");
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {
        People p = new People();
        System.out.println(p);
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            FileOutputStream fos = new FileOutputStream("test.out");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        People p1;
        try {
            FileInputStream fis = new FileInputStream("test.out");
            ois = new ObjectInputStream(fis);
            p1 = (People) ois.readObject();
            System.out.println(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
