package com.diguage.donkey.bean;

import com.diguage.donkey.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 *
 * @author diguage
 * @since 2017-01-07
 */
public class View {
  /** 视图路径 */
  private String path;
  private Map<String, Object> model;

  public View(String path) {
    this.path = path;
    model = new HashMap<>();
  }

  public View addModel(String key, Object value) {
    model.put(key, value);
    return this;
  }

  public String getPath() {
    return path;
  }

  public Map<String, Object> getModel() {
    return model;
  }
}
