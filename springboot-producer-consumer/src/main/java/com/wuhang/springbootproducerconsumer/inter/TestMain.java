package com.wuhang.springbootproducerconsumer.inter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


public class TestMain {

  @Autowired
  DoSomething doSomething;

  public void run(String... args) throws Exception {
    List<User> list = new ArrayList();

    list.add(new User("www","www","0"));
    list.add(new User("www1", "www1","0"));
    list.add(new User("www2", "www2","0"));
    list.add(new User("www3", "www3","0"));
    list.add(new User("www4", "www4","0"));

    BlockingDeque<User> blockingDeque = new LinkedBlockingDeque<>();
    Producer producer = new Producer(blockingDeque,list);


    Consumer<User> consumer = new Consumer(blockingDeque,doSomething);
    Executor producerExector = Executors.newScheduledThreadPool(1);
    producerExector.execute(producer);

    Executor consumerExector = Executors.newScheduledThreadPool(3);
    for (int i = 0; i < 3; i++) {
      consumerExector.execute(consumer);
    }
  }
}
