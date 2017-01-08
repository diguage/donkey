package com.diguage.donkey.helper;

import com.diguage.donkey.annotation.Action;
import com.diguage.donkey.bean.Handler;
import com.diguage.donkey.bean.Request;
import com.diguage.donkey.util.ArrayUtil;
import com.diguage.donkey.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author diguage
 * @since 2017-01-07
 */
public class ControllerHelper {
  /** 存放请求与处理器的映射关系 */
  private static final ConcurrentMap<Request, Handler> ACTION_MAP = new ConcurrentHashMap<>();

  static {
    Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
    if (CollectionUtil.isNotEmpty(controllerClassSet)) {
      for (Class<?> controllerClass : controllerClassSet) {
        Method[] methods = controllerClass.getDeclaredMethods();
        if (ArrayUtil.isNotEmpty(methods)) {
          for (Method method : methods) {
            if (method.isAnnotationPresent(Action.class)) {
              Action action = method.getAnnotation(Action.class);
              String mapping = action.value();
              if (mapping.matches("\\w+:/\\w*")) {
                String[] array = mapping.split(":");
                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                  String requestMethod = array[0];
                  String requestPath = array[1];
                  Request request = new Request(requestMethod, requestPath);
                  Handler handler = new Handler(controllerClass, method);
                  ACTION_MAP.put(request, handler);
                }
              }
            }
          }
        }
      }
    }
  }

  public static Handler getHandler(String requestMethod, String requestPath) {
    Request request = new Request(requestMethod, requestPath);
    return ACTION_MAP.get(request);
  }
}
