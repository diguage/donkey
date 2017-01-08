package com.diguage.donkey.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class CollectionUtil {

  /**
   * 集合是否为空
   *
   * @param collection 集合
   * @return 结果
   */
  public static boolean isEmpty(Collection<?> collection) {
    return CollectionUtils.isEmpty(collection);
  }

  /**
   * 集合是否为非空
   *
   * @param collection 集合
   * @return 结果
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }

  /**
   * Map是否为空
   *
   * @param map 集合
   * @return 结果
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return MapUtils.isEmpty(map);
  }

  /**
   * Map是否为非空
   *
   * @param map 集合
   * @return 结果
   */
  public static boolean isNotEmpty(Map<?, ?> map) {
    return !isEmpty(map);
  }
}
