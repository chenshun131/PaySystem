package com.chenshun.test.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: chenshun131 <p />
 * Time: 18/3/15 21:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataSource {

    String value();

}
