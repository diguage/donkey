package com.diguage.donkey.util;

/**
 * 类型转换工具类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class CastUtil {
  /**
   * 转换成字符串
   *
   * @param obj 待转换对象
   * @return 结果
   */
  public static String castString(Object obj) {
    return castString(obj, "");
  }

  /**
   * 转换成字符串
   *
   * @param obj 待转换对象
   * @param defaultValue 默认值
   * @return 结果
   */
  public static String castString(Object obj, String defaultValue) {
    return obj != null ? String.valueOf(obj) : defaultValue;
  }
  /**
   * 转换成 double
   *
   * @param object 待转换对象
   * @return 结果
   */
  public static double castDouble(Object object) {
    return castDouble(object, 0);
  }

  /**
   * 转换成 double
   *
   * @param object 待转换对象
   * @param defaultValue 默认值
   * @return 结果
   */
  public static double castDouble(Object object, double defaultValue) {
    double result = defaultValue;
    if (object != null) {
      String strValue = castString(object);
      if (StringUtil.isNotEmpty(strValue)) {
        try {
          result = Double.parseDouble(strValue);
        } catch (NumberFormatException e) {
          result = defaultValue;
        }
      }
    }

    return result;
  }

  /**
   * 转换成 long
   *
   * @param object 待转换对象
   * @return 结果
   */
  public static long castLong(Object object) {
    return castLong(object, 0L);
  }

  /**
   * 转换成 long
   *
   * @param object 待转换对象
   * @param defaultValue 默认值
   * @return 结果
   */
  public static long castLong(Object object, long defaultValue) {
    long result = defaultValue;
    if (object != null) {
      String strValue = castString(object);
      if (StringUtil.isNotEmpty(strValue)) {
        try {
          result = Long.parseLong(strValue);
        } catch (NumberFormatException e) {
          result = defaultValue;
        }
      }
    }
    return result;
  }

  /**
   * 转换成 int
   *
   * @param object 待转换对象
   * @return 结果
   */
  public static int castInt(Object object) {
    return castInt(object, 0);
  }

  /**
   * 转换成 int
   *
   * @param object 待转换对象
   * @param defaultValue 默认值
   * @return 结果
   */
  public static int castInt(Object object, int defaultValue) {
    int result = defaultValue;
    if (object != null) {
      String strValue = castString(object);
      if (StringUtil.isNotEmpty(strValue)) {
        try {
          result = Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
          result = defaultValue;
        }
      }
    }
    return result;
  }

  /**
   * 转换成 boolean
   *
   * @param object 待转换对象
   * @return 结果
   */
  public static boolean castBoolean(Object object) {
    return castBoolean(object, false);
  }

  /**
   * 转换成 boolean
   *
   * @param object 待转换对象
   * @param defaultValue 默认值
   * @return 结果
   */
  public static boolean castBoolean(Object object, boolean defaultValue) {
    boolean result = defaultValue;
    if (object != null) {
      result = Boolean.parseBoolean(castString(object));
    }
    return result;
  }
}
