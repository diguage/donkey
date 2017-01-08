package com.diguage.donkey.bean;

import com.diguage.donkey.util.CastUtil;

import java.util.Map;

/**
 * 请求处理对象
 *
 * @author diguage
 * @since 2017-01-07
 */
public class Param {
  private Map<String, Object> paramMap;

  public Param(Map<String, Object> paramMap) {
    this.paramMap = paramMap;
  }

  /**
   * 根据参数名获取 long 型参数值
   *
   * @param name 参数名
   * @return 值
   */
  public long getLong(String name) {
    return CastUtil.castLong(paramMap.get(name));
  }

  /**
   * 获取所有字段信息
   *
   * @return 字段信息Map
   */
  public Map<String, Object> getParamMap() {
    return paramMap;
  }
}
