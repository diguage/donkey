package com.diguage.proxy;

/**
 * @author diguage
 * @since 2017-01-09
 */
public class HelloProxy implements Hello {
  private Hello hello;

  public HelloProxy() {
    hello = new HelloImpl();
  }

  @Override
  public String say(String name) {
    before();
    String result = hello.say(name);
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
