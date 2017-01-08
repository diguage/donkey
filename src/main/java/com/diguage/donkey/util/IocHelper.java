package com.diguage.donkey.util;

import com.diguage.donkey.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class IocHelper {
  static {
    Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
    if (CollectionUtil.isNotEmpty(beanMap)) {
      for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
        Class<?> beanClass = entry.getKey();
        Object beanInstance = entry.getValue();
        Field[] beanFields = beanClass.getDeclaredFields();
        if (ArrayUtil.isNotEmpty(beanFields)) {
          for (Field field : beanFields) {
            if (field.isAnnotationPresent(Inject.class)) {
              Class<?> beanFieldClass = field.getType();
              Object beanFieldInstance = beanMap.get(beanFieldClass);
              if (beanFieldInstance != null) {
                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
              }
            }
          }
        }
      }
    }
  }
}
