package com.chenshun.test.jvm;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * User: chenshun131 <p />
 * Time: 18/4/7 23:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class JSDemo {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("JavaScript");

        Object value = se.eval("function add(a, b) { return a + b; } add(2, 3);");
        System.out.println(value);
    }

}
