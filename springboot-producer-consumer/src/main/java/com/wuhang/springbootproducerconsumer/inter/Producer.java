package com.wuhang.springbootproducerconsumer.inter;

import java.util.List;
import java.util.concurrent.BlockingDeque;

public class Producer<T> implements Runnable {

  List<T> t;
  BlockingDeque<T> blockingDeque;

  public Producer(BlockingDeque<T> blockingDeque,List<T> t) {
    super();
    this.blockingDeque = blockingDeque;
    this.t = t;
  }

  @Override
  public void run() {
    putMessage();
  }

  private void putMessage() {
    try {
      for (T it : this.t) {
        this.blockingDeque.put(it);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
