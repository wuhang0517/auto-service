package com.wuhang.springbootproducerconsumer.design.pojo;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Data
public class Producer<T> implements Runnable{

  BlockingDeque <T> blockingDeque;

  List<T> list;

  private int producerThreadPoolSize;

  public Producer(BlockingDeque<T> blockingDeque, List<T> list) {
    this.blockingDeque = blockingDeque;
    this.list = list;
  }

  @Override
  public void run() {
    try {
      while (true) {
        putMessage();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void putMessage() throws InterruptedException{
    if (null == this.list && this.list.size() == 0) {
      return;
    }
    for (Iterator<T> it=this.list.iterator();it.hasNext();) {
      T t = it.next();
      this.blockingDeque.put(t);
    }
  }
}
