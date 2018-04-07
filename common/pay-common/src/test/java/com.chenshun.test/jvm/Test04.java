package com.chenshun.test.jvm;

import org.junit.Test;

import java.util.Scanner;

/**
 * User: chenshun131 <p />
 * Time: 18/4/5 14:38  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Test04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    @Test
    public void test() {
        Thread.getAllStackTraces().forEach((thread, stackTraceElements) -> {
            System.out.println("Thread name is" + thread.getName());
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                System.out.println("\t" + stackTraceElement.toString());
            }
        });
    }

}
