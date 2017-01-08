package com.diguage.donkey.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author diguage
 * @since 2017-01-07
 */
public class JsonUtil {
  private static final Logger logger = LogManager.getLogger(JsonUtil.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  static {
    OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  /**
   * 将 POJO 转为 JSON
   *
   * @param object 实例对象
   * @param <T> 泛型类
   * @return JSON数据
   */
  public static <T> String toJson(T object) {
    String json;
    try {
      json = OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      logger.error("convert POJO to JSON failure", e);
      throw new RuntimeException(e);
    }
    return json;
  }

  /**
   * 将 JSON 转为 POJO
   *
   * @param json JSON 数据
   * @param type 类型
   * @param <T> 泛型类
   * @return POJO 数据
   */
  public static <T> T fromJson(String json, Class<T> type) {
    T pojo;
    try {
      pojo = OBJECT_MAPPER.readValue(json, type);
    } catch (Exception e) {
      logger.error("convert JSON to POJO failure", e);
      throw new RuntimeException(e);
    }
    return pojo;
  }
}
