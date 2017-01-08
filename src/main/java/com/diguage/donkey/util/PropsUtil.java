package com.diguage.donkey.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties 工具类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class PropsUtil {
  private static final Logger logger = LogManager.getLogger(PropsUtil.class);

  /**
   * 载入 Properties 文件
   *
   * @param fileName 文件名
   * @return Properties 对象
   */
  public static Properties loadProps(String fileName) {
    Properties result = null;
    InputStream is = null;
    try {
      is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
      if (is == null) {
        throw new FileNotFoundException(fileName + " file is not found.");
      }
      result = new Properties();
      result.load(is);
    } catch (IOException e) {
      logger.error("load properties file failure", e);
    } finally {

      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          logger.error("close input stream failure", e);
        }
      }
    }
    return result;
  }

  /**
   * 获取 Properties 中的某项的值
   *
   * @param props Properties对象
   * @param key Key
   * @return 值
   */
  public static String getString(Properties props, String key) {
    return getString(props, key, "");
  }

  /**
   * 获取 Properties 中的某项的值
   *
   * @param props Properties对象
   * @param key Key
   * @param defaultValue
   * @return 值
   */
  public static String getString(Properties props, String key, String defaultValue) {
    String result = defaultValue;
    if (props.containsKey(key)) {
      result = props.getProperty(key);
    }
    return result;
  }

  /**
   * 获取 Properties 中的某项的整数值
   *
   * @param props Properties对象
   * @param key Key
   * @return 值
   */
  public static int getInt(Properties props, String key) {
    return getInt(props, key, 0);
  }

  /**
   * 获取 Properties 中的某项的整数值
   *
   * @param props Properties对象
   * @param key Key
   * @param defaultValue 默认值
   * @return 值
   */
  public static int getInt(Properties props, String key, int defaultValue) {
    int result = defaultValue;
    if (props.containsKey(key)) {
      result = CastUtil.castInt(props.getProperty(key));
    }
    return result;
  }
}
