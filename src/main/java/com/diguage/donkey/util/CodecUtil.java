package com.diguage.donkey.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码和解码工具类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class CodecUtil {
  private static final Logger logger = LogManager.getLogger(CodecUtil.class);

  /**
   * 将 URL 编码
   *
   * @param source 原始字符串
   * @return 编码后的字符串
   */
  public static String encodeURL(String source) {
    String target;
    try {
      target = URLEncoder.encode(source, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      logger.error("encode url failure", e);
      throw new RuntimeException(e);
    }
    return target;
  }

  /**
   * 将 URL 解码
   *
   * @param source 原始字符串
   * @return 解码后的字符串
   */
  public static String decodeURL(String source) {
    String target;
    try {
      target = URLDecoder.decode(source, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      logger.error("encode url failure", e);
      throw new RuntimeException(e);
    }
    return target;
  }
}
