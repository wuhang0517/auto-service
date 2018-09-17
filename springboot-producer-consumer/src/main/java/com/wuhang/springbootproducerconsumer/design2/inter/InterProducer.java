package com.wuhang.springbootproducerconsumer.design2.inter;

import java.util.List;

public interface InterProducer<T> {

  List<T> createMessage();
}
