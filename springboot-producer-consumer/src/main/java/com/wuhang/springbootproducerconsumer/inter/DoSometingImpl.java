package com.wuhang.springbootproducerconsumer.inter;

import org.springframework.stereotype.Service;

@Service
public class DoSometingImpl implements DoSomething<User>{

  @Override
  public void doSometing(User user) {
    System.out.println(user.getUsername()+"--"+user.getPassword());
  }

  @Override
  public void unlock(User user) {
    user.setLock("1");
  }
}