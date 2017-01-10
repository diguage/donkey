package com.diguage.aop;

/**
 * @author diguage
 * @since 2017-01-09
 */
public class Main {
  public static void main(String[] args) {
    Greeting greeting = new JdkDynamicProxy(new GreetingImpl()).getProxy();
    greeting.sayHello("D瓜哥！");

    System.out.println("\n\n--CgLib----------------");
    Greeting cglibGreeting = CglibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
    cglibGreeting.sayHello("Rose");
  }
}
