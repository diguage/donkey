package com.diguage.donkey.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class StringUtil {
  /**
   * 字符串是否为空
   *
   * @param value 字符串
   * @return 结果
   */
  public static boolean isEmpty(String value) {
    if (value != null) {
      value = value.trim();
    }
    return StringUtils.isEmpty(value);
  }

  /**
   * 字符串是否为非空
   *
   * @param value 字符串
   * @return 结果
   */
  public static boolean isNotEmpty(String value) {
    return !isEmpty(value);
  }

  public static String[] splitString(String body, String de) {
    return new String[0];
  }
}
