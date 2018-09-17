package com.wuhang.springbootproducerconsumer.design.service.impl;

import com.wuhang.springbootproducerconsumer.design.model.User;
import com.wuhang.springbootproducerconsumer.design.service.ConsumerAction;

public class ConsumerActionImpl implements ConsumerAction<User> {
  @Override
  public void consumedAction(User user) {
    System.out.println("action--" +user.toString());
  }

  @Override
  public void unlock(User user) {
    user.setLock("1");
  }
}
