package com.diguage.donkey.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类操作工具类
 *
 * @author diguage
 * @since 2017-01-06
 */
public class ClassUtil {
  private static final Logger logger = LogManager.getLogger(ClassUtil.class);

  /**
   * 获取类加载器
   *
   * @return 类加载器
   */
  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  /**
   * 加载类
   *
   * @param className 类名
   * @param isInitialized 是否初始化
   * @return Class对象
   */
  public static Class<?> loadClass(String className, boolean isInitialized) {
    Class<?> result;
    try {
      result = Class.forName(className, isInitialized, getClassLoader());
    } catch (ClassNotFoundException e) {
      logger.error("load class failure", e);
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * 获取指定包名下的所有类
   *
   * @param packageName 包名
   * @return 类集合
   */
  public static Set<Class<?>> getClassSet(String packageName) {
    Set<Class<?>> result = new HashSet<>();
    try {
      Enumeration<URL> resources = getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
      while (resources.hasMoreElements()) {
        URL url = resources.nextElement();
        if (url != null) {
          String protocol = url.getProtocol();
          if ("file".equals(protocol)) {
            String packagePath = url.getPath().replaceAll("%20", " ");
            addClass(result, packagePath, packageName);
          } else if ("jar".equals(protocol)) {
            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
            if (jarURLConnection != null) {
              JarFile jarFile = jarURLConnection.getJarFile();
              if (jarFile != null) {
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                  JarEntry jarEntry = jarEntries.nextElement();
                  String jarEntryName = jarEntry.getName();
                  if (jarEntryName.endsWith(".class")) {
                    String className =
                        jarEntryName
                            .substring(0, jarEntryName.lastIndexOf("."))
                            .replaceAll("/", ".");
                    doAddClass(result, className);
                  }
                }
              }
            }
          }
        }
      }
    } catch (IOException e) {
      logger.error("get class set failure", e);
      throw new RuntimeException(e);
    }
    return result;
  }

  private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
    File[] files =
        new File(packagePath)
            .listFiles(f -> (f.isFile() && f.getName().endsWith(".class")) || f.isDirectory());
    for (File file : files) {
      String fileName = file.getName();
      if (file.isFile()) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        if (StringUtil.isNotEmpty(packageName)) {
          className = packageName + "." + className;
        }
        doAddClass(classSet, className);
      } else {
        String subPackagePath = fileName;
        String subPackageName = fileName;
        if (StringUtil.isNotEmpty(packageName)) {
          subPackagePath = packagePath + "/" + subPackagePath;
          subPackageName = packageName + "." + subPackageName;
        }
        addClass(classSet, subPackagePath, subPackageName);
      }
    }
  }

  private static void doAddClass(Set<Class<?>> classSet, String className) {
    classSet.add(loadClass(className, false));
  }
}
