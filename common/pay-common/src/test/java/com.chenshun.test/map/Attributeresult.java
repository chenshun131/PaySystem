package com.chenshun.test.map;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 18/1/25 16:01  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Attributeresult {

    String value;

    String result;

    public Attributeresult() {
    }

    public Attributeresult(String value, String result) {
        this.value = value;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attributeresult attributeresult = (Attributeresult) o;
        if (attributeresult.result == null || attributeresult.value == null) {
            return false;
        }
        if (value.equals(attributeresult.value) && result.equals(attributeresult.result)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public static void main(String[] args) {
        Map<Attributeresult, Integer> valuelist = new HashMap<>();
        valuelist.put(new Attributeresult("mim", "yes"), 20);
        valuelist.put(new Attributeresult("mim", "no"), 1);
        valuelist.put(new Attributeresult("xunying", "yes"), 60);
        valuelist.put(new Attributeresult("xunying", "no"), 2);
        valuelist.put(new Attributeresult("mini", "no"), 2);

        Attributeresult attributeresult = new Attributeresult("xunying", "yes");
        if (valuelist.containsKey(attributeresult)) {
            System.out.println("存在 : " + valuelist.get(attributeresult));
        } else {
            valuelist.put(new Attributeresult("mini", "yes"), 30);
        }
    }

}
