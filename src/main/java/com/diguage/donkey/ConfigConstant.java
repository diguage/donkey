package com.diguage.donkey;

/**
 * 相关配置项常量
 *
 * @author diguage
 * @since 2017-01-06
 */
public interface ConfigConstant {
  String CONFIG_FILE = "donkey.properties";

  String JDBC_DRIVER = "donkey.jdbc.driver";
  String JDBC_URL = "donkey.jdbc.url";
  String JDBC_USERNAME = "donkey.jdbc.username";
  String JDBC_PASSWORD = "donkey.jdbc.password";

  String APP_BASE_PACKAGE = "donkey.app.base_package";
  String APP_JSP_PATH = "donkey.app.jsp_path";
  String APP_ASSET_PATH = "donkey.app.asset_path";
}
