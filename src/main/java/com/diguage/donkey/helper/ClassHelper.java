package com.diguage.donkey.helper;

import com.diguage.donkey.annotation.Controller;
import com.diguage.donkey.annotation.Service;
import com.diguage.donkey.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 类操作助手类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class ClassHelper {
  /** 定义类集合（用于存放所加载的类） */
  private static final Set<Class<?>> CLASS_SET;

  static {
    String basePackage = ConfigHelper.getAppBasePackage();
    CLASS_SET = ClassUtil.getClassSet(basePackage);
  }

  /**
   * 获取应用包名下的所有类
   *
   * @return Class集合
   */
  public static Set<Class<?>> getClassSet() {
    return CLASS_SET;
  }

  /**
   * 获取应用包名下所有 Service 类
   *
   * @return Class 集合
   */
  public static Set<Class<?>> getServiceClassSet() {
    return getClassesByAnnotation(Service.class);
  }

  /**
   * 获取应用包名下所有 Controller 类
   *
   * @return Class 集合
   */
  public static Set<Class<?>> getControllerClassSet() {
    return getClassesByAnnotation(Controller.class);
  }

  /**
   * 获取应用包名下所有 Bean 类(包括：Service、Controller 等)
   *
   * @return Class 集合
   */
  public static Set<Class<?>> getBeanClassSet() {
    Set<Class<?>> classSet = getControllerClassSet();
    classSet.addAll(getServiceClassSet());
    return classSet;
  }

  /**
   * 获取 注解 标注的类
   *
   * @param annoClass 注解类
   * @return 筛选出来的 Class 集合
   */
  private static Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annoClass) {
    return CLASS_SET
        .parallelStream()
        .filter(c -> c.isAnnotationPresent(annoClass))
        .collect(Collectors.toSet());
  }
}
