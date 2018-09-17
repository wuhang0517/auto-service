package com.wuhang.springbootproducerconsumer.design2.util;

public class ReflectHelper {

  private ReflectHelper() {
    throw new IllegalStateException("ReflectHelper class");
  }

  public static Object getClassInstance(String className) {
    Object result = null;
    try {
      Class<?> c = Class.forName(className);
      result = c.newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | ClassNotFoundException | SecurityException e) {
    }
    return result;
  }
}
