package com.wuhang.springbootproducerconsumer.test;

import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {

  private final BlockingDeque blockingDeque;

  public Consumer(BlockingDeque blockingDeque) {
    this.blockingDeque = blockingDeque;
  }


  @Override
  public void run() {
    try {
      Thread.sleep(10000);
      while (true) {
          System.out.println("消费者:" + Thread.currentThread().getName()+blockingDeque.take());
          Thread.sleep(2000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
