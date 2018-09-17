package com.wuhang.springbootproducerconsumer.inter;

public interface DoSomething<T> {

  void doSometing(T t);

  void unlock(T t);
}
