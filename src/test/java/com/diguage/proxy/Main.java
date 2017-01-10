package com.diguage.proxy;

import java.lang.reflect.Proxy;

/**
 * @author diguage
 * @since 2017-01-08
 */
public class Main {
  public static void main(String[] args) {
    HelloProxy helloProxy = new HelloProxy();
    helloProxy.say("Jack");

    System.out.println("\n\n--DynamicProxy---------------------");
    Hello hello = new HelloImpl();
    DynamicProxy dynamicProxy = new DynamicProxy(hello);
    Hello helloDynamincProxy =
        (Hello)
            Proxy.newProxyInstance(
                hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);
    helloDynamincProxy.say("Rose");

    System.out.println("\n\n--DynamicProxy 升级版---------------------");
    DynamicProxy dynamicProxyImprove = new DynamicProxy(hello);
    Hello helloDynamicProxyImprove = dynamicProxyImprove.getProxy();
    helloDynamicProxyImprove.say("Ting");

    System.out.println("\n\n--CgLib---------------------");
    Hello helloCglibProxy = CglibProxy.getInstance().getProxy(HelloImpl.class);
    helloCglibProxy.say("D瓜哥！");
  }
}
