package com.diguage.proxy;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-08
 */
public interface AfterReturningAdvice {
  void afterReturning(Method method, Object[] args, Object target) throws Throwable;
}
