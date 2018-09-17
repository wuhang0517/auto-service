package com.wuhang.springbootproducerconsumer.design2.inter.impl;

import com.wuhang.springbootproducerconsumer.design2.inter.InterConsumer;
import com.wuhang.springbootproducerconsumer.design2.model.User;

public class UserConsumerImpl implements InterConsumer<User> {
  @Override
  public void actionConsumer(User user) {
    System.out.println(Thread.currentThread().getName()+"-消费-"+user.toString());
  }

  @Override
  public void unlock(User user) {

  }
}
