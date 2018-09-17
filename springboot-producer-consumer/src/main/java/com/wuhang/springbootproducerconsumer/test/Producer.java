package com.wuhang.springbootproducerconsumer.test;

import java.util.concurrent.BlockingDeque;

public class Producer implements Runnable {

  private final BlockingDeque blockingDeque;


  public Producer(BlockingDeque blockingDeque) {
    this.blockingDeque = blockingDeque;
  }

  int task = 1;

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println("生产者:"+Thread.currentThread().getName()+task);
        blockingDeque.put(task);
        ++task;
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
