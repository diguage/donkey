package com.diguage.donkey.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class ReflectionUtil {
  private static final Logger logger = LogManager.getLogger(ReflectionUtil.class);

  /**
   * 创建实例
   *
   * @param clazz 类对象
   * @param <T> 泛型类
   * @return 实例对象
   */
  public static <T> T newInstance(Class<T> clazz) {
    T instance = null;
    try {
      instance = clazz.newInstance();
    } catch (Exception e) {
      logger.error("new instance failure", e);
      throw new RuntimeException(e);
    }
    return instance;
  }

  /**
   * 调用方法
   *
   * @param object 实例
   * @param method 方法
   * @param args 参数
   * @return 结果
   */
  public static Object invokeMethod(Object object, Method method, Object... args) {
    Object result;
    try {
      method.setAccessible(true);
      result = method.invoke(object, args);
    } catch (Exception e) {
      logger.error("invoke method failure", e);
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * 设置成员变量属性值
   *
   * @param object 实例对象
   * @param field 属性
   * @param value 值
   */
  public static void setField(Object object, Field field, Object value) {
    try {
      field.setAccessible(true);
      field.set(object, value);
    } catch (IllegalAccessException e) {
      logger.error("set field failure", e);
      throw new RuntimeException(e);
    }
  }
}
