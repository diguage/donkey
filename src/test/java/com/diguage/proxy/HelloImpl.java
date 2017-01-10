package com.diguage.proxy;

/**
 * @author diguage
 * @since 2017-01-08
 */
public class HelloImpl implements Hello {
  @Override
  public String say(String name) {
    String result = "Hi, " + name;
    System.out.println(result);
    return result;
  }
}
