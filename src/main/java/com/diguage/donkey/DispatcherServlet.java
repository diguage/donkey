package com.diguage.donkey;

import com.diguage.donkey.bean.Data;
import com.diguage.donkey.bean.Handler;
import com.diguage.donkey.bean.Param;
import com.diguage.donkey.bean.View;
import com.diguage.donkey.helper.ConfigHelper;
import com.diguage.donkey.helper.ControllerHelper;
import com.diguage.donkey.util.ArrayUtil;
import com.diguage.donkey.util.BeanHelper;
import com.diguage.donkey.util.CodecUtil;
import com.diguage.donkey.util.JsonUtil;
import com.diguage.donkey.util.ReflectionUtil;
import com.diguage.donkey.util.StreamUtil;
import com.diguage.donkey.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author diguage
 * @since 2017-01-07
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
  @Override
  public void init(ServletConfig servletConfig) throws ServletException {
    HelperLoader.init();
    ServletContext servletContext = servletConfig.getServletContext();

    ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
    String patterns = ConfigHelper.getAppJspPath() + "*";
    jspServlet.addMapping(patterns);

    ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
    String assetPatterns = ConfigHelper.getAppAssetPath() + "*";
    defaultServlet.addMapping(assetPatterns);
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String requestMethod = request.getMethod().toLowerCase();
    String requestPath = request.getPathInfo();
    Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
    if (handler != null) {
      Class<?> controllerClass = handler.getControllerClass();
      Object controllerBean = BeanHelper.getBean(controllerClass);
      Map<String, Object> paramMap = new HashMap<>();
      Enumeration<String> parameterNames = request.getParameterNames();
      while (parameterNames.hasMoreElements()) {
        String paramName = parameterNames.nextElement();
        String paramValue = request.getParameter(paramName);
        paramMap.put(paramName, paramValue);
      }

      String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
      if (StringUtil.isNotEmpty(body)) {
        String[] params = StringUtil.splitString(body, "&");
        if (ArrayUtil.isNotEmpty(params)) {
          Stream.of(params)
              .map(p -> StringUtil.splitString(p, "="))
              .filter(a -> ArrayUtil.isNotEmpty(a) && a.length == 2)
              .forEach(a -> paramMap.put(a[0], a[1]));
        }
      }
      Param param = new Param(paramMap);
      Method actionMethod = handler.getActionMethod();
      Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
      if (result instanceof View) {
        View view = (View) result;
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
          if (path.startsWith("/")) {
            response.sendRedirect(request.getContextPath() + path);
          } else {
            view.getModel()
                .entrySet()
                .parallelStream()
                .forEach(e -> request.setAttribute(e.getKey(), e.getValue()));

            request
                .getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                .forward(request, response);
          }
        }
      } else if (result instanceof Data) {
        Data data = (Data) result;
        Object model = data.getModel();
        if (model != null) {
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          PrintWriter writer = response.getWriter();
          String json = JsonUtil.toJson(model);
          writer.write(json);
          writer.flush();
          writer.close();
        }
      }
    }
  }
}
