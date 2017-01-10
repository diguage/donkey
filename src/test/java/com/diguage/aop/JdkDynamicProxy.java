package com.diguage.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author diguage
 * @since 2017-01-09
 */
public class JdkDynamicProxy implements InvocationHandler {
  private Object target;

  public JdkDynamicProxy(Object target) {
    this.target = target;
  }

  @SuppressWarnings("unchecked")
  public <T> T getProxy() {
    return (T)
        Proxy.newProxyInstance(
            target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before();
    Object result = method.invoke(target, args);
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
