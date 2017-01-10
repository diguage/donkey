package com.diguage.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-09
 */
public class CglibDynamicProxy implements MethodInterceptor {
  private static final CglibDynamicProxy instance = new CglibDynamicProxy();

  private CglibDynamicProxy() {}

  public static CglibDynamicProxy getInstance() {
    return instance;
  }

  @SuppressWarnings("unchecked")
  public <T> T getProxy(Class<T> clazz) {
    return (T) Enhancer.create(clazz, this);
  }

  @Override
  public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    before();
    Object result = methodProxy.invokeSuper(target, args);
    after();
    return result;
  }

  private void before() {
    System.out.println("Before");
  }

  private void after() {
    System.out.println("After");
  }
}
