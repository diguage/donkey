package com.diguage.donkey.util;

/**
 * 数组辅助类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class ArrayUtil {
  /**
   * 是否为非空
   *
   * @param array 数组
   * @param <T> 泛型类
   * @return 结果
   */
  public static <T> boolean isNotEmpty(T[] array) {
    return !isEmpty(array);
  }

  /**
   * 是否为空
   *
   * @param array 数组
   * @param <T> 泛型类
   * @return 结果
   */
  public static <T> boolean isEmpty(T[] array) {
    return array == null || array.length == 0;
  }
}
