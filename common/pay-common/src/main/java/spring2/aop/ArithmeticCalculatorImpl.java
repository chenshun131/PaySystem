package spring2.aop;

import org.springframework.stereotype.Component;

/**
 * User: chenshun131 <p />
 * Time: 18/3/3 13:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }

}
