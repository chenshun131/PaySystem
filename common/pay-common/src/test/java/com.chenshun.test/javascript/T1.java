package com.chenshun.test.javascript;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * User: mew <p />
 * Time: 18/2/22 10:19  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public final class T1 {

    @Test
    public void t1() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

    @Test
    public void t2() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(new FileReader("/Users/mew/Desktop/AllMyFile/CompanyReposity/GitHub/PaySystem/common/pay-common/src/test/java/com.chenshun" +
                ".test/javascript/script.js"));

        // 先得把脚本引擎转换为 Invocable
        Invocable invocable = (Invocable) engine;

        // NashornScriptEngine 实现了 Invocable 接口且定义一个调用JavaScript函数的方法 invokeFunction ，传入函数名即可
        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());

        invocable.invokeFunction("fun2", new Date()); // JS Class Definition: [object java.util.Date]
        invocable.invokeFunction("fun2", LocalDateTime.now()); // JS Class Definition: [object java.time.LocalDateTime]
    }

    static String fun1(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }

}
