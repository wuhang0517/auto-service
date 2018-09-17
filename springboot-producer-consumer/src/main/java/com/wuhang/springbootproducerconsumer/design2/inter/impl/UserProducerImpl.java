package com.wuhang.springbootproducerconsumer.design2.inter.impl;

import com.wuhang.springbootproducerconsumer.design2.inter.InterProducer;
import com.wuhang.springbootproducerconsumer.design2.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserProducerImpl implements InterProducer<User> {

  private List<User> userList = new ArrayList<>();
  private static AtomicInteger count = new AtomicInteger();
  @Override
  public List<User> createMessage() {

    for (int i =0;i<5;i++) {
      User user = new User();
      user.setUsername("ww"+count.incrementAndGet());
      user.setPassword("ww"+count.incrementAndGet());
      user.setLock("0");
      this.userList.add(user);
    }
    try {
      Thread.sleep(2*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return userList;
  }
}
