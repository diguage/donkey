package com.diguage.donkey.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class StreamUtil {
  private static final Logger logger = LogManager.getLogger(StreamUtil.class);

  /**
   * 从输入流中获取字符串
   *
   * @param inputStream 输入流
   * @return 字符串
   */
  public static String getString(InputStream inputStream) {
    StringBuilder sb = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      logger.error("get string failure", e);
      throw new RuntimeException(e);
    }
    return sb.toString();
  }
}
