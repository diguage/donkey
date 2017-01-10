package com.diguage.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author diguage
 * @since 2017-01-09
 */
public class AopMain {
  public static void main(String[] args) {
    System.out.println("--Program AOP------------------------");
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(new GreetingImpl());
    proxyFactory.addAdvice(new GreetingBeforeAdvice());
    proxyFactory.addAdvice(new GreetingAfterAdvice());
    proxyFactory.addAdvice(new GreetingAroundAdvice());
    Greeting greeting = (Greeting) proxyFactory.getProxy();
    greeting.sayHello("D瓜哥！");

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    System.out.println("\n\n--XML AOP-----------------------");
    Greeting greetingXmlProxy = (Greeting) applicationContext.getBean("greetingXmlProxy");
    greetingXmlProxy.sayHello("Xml AOP");

    System.out.println("\n\n--IntroductionAdvice---------------------");
    GreetingImpl greetingIndroProxy =
        (GreetingImpl) applicationContext.getBean("greetingIntroProxy");
    greetingIndroProxy.sayHello("Jone");
    Apology apology = (Apology) greetingIndroProxy;
    apology.saySorry("Honey");

    System.out.println("\n\n--Pointcut---------------------------");
    GreetingImpl greetingPointProxy =
        (GreetingImpl) applicationContext.getBean("greetingPointProxy");
    greetingPointProxy.sayHello("Pointcut");
    greetingPointProxy.goodMorning("Pointcut");
    greetingPointProxy.goodNight("Pointcut");

    System.out.println("\n\n--ThrowAdvice---------------------");
    Greeting greetingProxy = (Greeting) applicationContext.getBean("greetingThrowProxy");
    greetingProxy.sayHello("Rose");
  }
}
