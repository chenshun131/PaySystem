package spring3.xml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class VlidationAspect {

	public void validateArgs(JoinPoint joinPoint){
		System.out.println("-->validate:" + Arrays.asList(joinPoint.getArgs()));
	}
	
}
