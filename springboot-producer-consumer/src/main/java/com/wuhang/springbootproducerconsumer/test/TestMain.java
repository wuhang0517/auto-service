package com.wuhang.springbootproducerconsumer.test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TestMain {

  public static void main(String[] args) {
    BlockingDeque blockingDeque = new LinkedBlockingDeque();
    Producer producer = new Producer(blockingDeque);
    Consumer consumer = new Consumer(blockingDeque);

    Thread tp = new Thread(producer);
    Thread tc = new Thread(consumer);
    Thread tc1 = new Thread(consumer);

    tp.start();
    tc.start();
    tc1.start();
  }
}
