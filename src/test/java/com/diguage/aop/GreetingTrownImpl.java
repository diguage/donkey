package com.diguage.aop;

import org.springframework.stereotype.Component;

/**
 * @author diguage
 * @since 2017-01-09
 */
@Component
public class GreetingTrownImpl implements Greeting {
  @Override
  public String sayHello(String name) {
    String result = "Hi," + name;
    System.out.println(result);
    throw new RuntimeException("OK!!!");
  }

  public void goodMorning(String name) {
    System.out.println("Good Morning, " + name);
  }

  public void goodNight(String name) {
    System.out.println("Good night, " + name);
  }
}
