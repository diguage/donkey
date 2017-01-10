package com.diguage.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author diguage
 * @since 2017-01-10
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    return super.invoke(invocation);
  }

  @Override
  public String saySorry(String name) {
    String result = "Sorry, " + name;
    System.out.println(result);
    return result;
  }
}
