package com.diguage.proxy;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-08
 */
public interface MethodBeforeAdvice {
  void before(Method method, Object[] args, Object target) throws Throwable;
}
