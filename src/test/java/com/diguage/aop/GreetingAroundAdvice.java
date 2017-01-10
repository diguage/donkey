package com.diguage.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author diguage
 * @since 2017-01-10
 */
@Component
public class GreetingAroundAdvice implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    before();
    Object result = invocation.proceed();
    after();
    return result;
  }

  private void before() {
    System.out.println("Before--");
  }

  private void after() {
    System.out.println("After--");
  }
}
