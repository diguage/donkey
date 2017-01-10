package com.diguage.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author diguage
 * @since 2017-01-08
 */
public class CglibProxy implements MethodInterceptor {
  private static final CglibProxy instance = new CglibProxy();

  private CglibProxy() {}

  public static CglibProxy getInstance() {
    return instance;
  }

  public <T> T getProxy(Class<T> clazz) {
    return (T) Enhancer.create(clazz, this);
  }

  @Override
  public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy)
      throws Throwable {
    before();
    Object result = proxy.invokeSuper(object, args);
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
