package com.diguage.donkey;

import com.diguage.donkey.helper.ClassHelper;
import com.diguage.donkey.helper.ControllerHelper;
import com.diguage.donkey.util.BeanHelper;
import com.diguage.donkey.util.ClassUtil;
import com.diguage.donkey.util.IocHelper;

import java.util.stream.Stream;

/**
 * 加载相应的 Helper 类
 *
 * @author diguage
 * @since 2017-01-07
 */
public class HelperLoader {
  public static void init() {
    Class<?>[] classList = {
      ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class
    };
    Stream.of(classList).forEach(c -> ClassUtil.loadClass(c.getName(), true));
  }
}
