package com.wuhang.springbootproducerconsumer.inter;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingDeque;

public class Consumer<T> implements Runnable {

  @Autowired
  DoSomething doSomething;

  BlockingDeque<T> blockingDeque;


  public Consumer(BlockingDeque<T> blockingDeque,DoSomething doSomething) {
    this.blockingDeque = blockingDeque;
    this.doSomething = doSomething;
  }


  @Override
  public void run() {
    try {
      while (true) {
        T t = blockingDeque.take();
        System.out.println(t.toString());
        doSomething.doSometing(t);
        doSomething.unlock(t);
        System.out.println(t.toString());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
