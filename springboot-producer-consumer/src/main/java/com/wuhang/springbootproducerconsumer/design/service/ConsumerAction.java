package com.wuhang.springbootproducerconsumer.design.service;

public interface ConsumerAction<T> {

  void consumedAction(T t);

  void unlock(T t);
}
