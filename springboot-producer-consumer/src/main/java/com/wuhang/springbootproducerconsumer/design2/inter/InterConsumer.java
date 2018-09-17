package com.wuhang.springbootproducerconsumer.design2.inter;

public interface  InterConsumer<T> {

  void actionConsumer(T t);

  void unlock(T t);
}
