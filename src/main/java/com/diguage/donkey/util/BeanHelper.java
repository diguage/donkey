package com.diguage.donkey.util;

import com.diguage.donkey.helper.ClassHelper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Bean 助手类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class BeanHelper {
  /** 定义 Bean 映射（用户存放 Bean 类与 Bean 实例的映射关系） */
  private static final ConcurrentMap<Class<?>, Object> BEAN_MAP = new ConcurrentHashMap<>();

  static {
    Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
    //    BEAN_MAP.putAll(
    //        beanClassSet
    //            .parallelStream()
    //            .collect(Collectors.toMap(c -> c, ReflectionUtil::newInstance)));

    //    beanClassSet.parallelStream().collect(Collectors.toMap(c -> c, ReflectionUtil::newInstance));

    //    beanClassSet
    //        .parallelStream()
    //        .map(ReflectionUtil::newInstance)
    //        .forEach(o -> BEAN_MAP.put(o.getClass(), o));
    for (Class<?> clazz : beanClassSet) {
      BEAN_MAP.put(clazz, ReflectionUtil.newInstance(clazz));
    }
  }

  public static Map<Class<?>, Object> getBeanMap() {
    return BEAN_MAP;
  }

  /**
   * 获取 Bean 实例
   *
   * @param clazz 类
   * @param <T> 泛型类
   * @return 实例
   */
  public static <T> T getBean(Class<T> clazz) {
    if (!BEAN_MAP.containsKey(clazz)) {
      throw new RuntimeException("can not get bean by class: " + clazz);
    }
    return (T) BEAN_MAP.get(clazz);
  }
}
