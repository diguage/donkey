package com.diguage.donkey.helper;

import com.diguage.donkey.ConfigConstant;
import com.diguage.donkey.util.PropsUtil;

import java.util.Properties;

/**
 * 属性文件助手类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class ConfigHelper {
  private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

  /**
   * 获取 JDBC 驱动
   *
   * @return 驱动类名
   */
  public static String getJdbcDriver() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
  }

  /**
   * 获取 JDBC URL
   *
   * @return JDBC URL
   */
  public static String getJdbcUrl() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
  }

  /**
   * 获取 JDBC 用户名
   *
   * @return JDBC 用户名
   */
  public static String getJdbcUsername() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
  }

  /**
   * 获取 JDBC 密码
   *
   * @return JDBC 密码
   */
  public static String getJdbcPassword() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
  }

  /**
   * 获取应用基础包名
   *
   * @return
   */
  public static String getAppBasePackage() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
  }

  /**
   * 获取应用 JSP 路径，默认是 /WEB-INF/view/
   *
   * @return JSP 路径
   */
  public static String getAppJspPath() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
  }

  /**
   * 获取应用静态资源路径，默认是 /asset/
   *
   * @return 静态资源路径
   */
  public static String getAppAssetPath() {
    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
  }
}
