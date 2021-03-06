package com.diguage.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-09
 */
@Component
public class GreetingAfterAdvice implements AfterReturningAdvice {
  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
      throws Throwable {
    System.out.println("After");
  }
}
