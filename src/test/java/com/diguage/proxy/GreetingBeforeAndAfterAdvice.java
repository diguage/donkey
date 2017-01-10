package com.diguage.proxy;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-08
 */
public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("Before--");
  }

  @Override
  public void afterReturning(Method method, Object[] args, Object target) throws Throwable {
    System.out.println("After--");
  }
}
