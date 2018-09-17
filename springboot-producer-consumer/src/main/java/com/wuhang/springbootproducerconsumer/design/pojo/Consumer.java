package com.wuhang.springbootproducerconsumer.design.pojo;

import com.wuhang.springbootproducerconsumer.design.service.ConsumerAction;
import lombok.Data;

import java.util.concurrent.BlockingDeque;

@Data
public class Consumer<T> implements Runnable {

  ConsumerAction consumerAction;

  BlockingDeque<T> blockingDeque;

  public Consumer(BlockingDeque<T> blockingDeque,ConsumerAction consumerAction) {
    this.blockingDeque = blockingDeque;
    this.consumerAction = consumerAction;
  }

  public Consumer() {
  }

  @Override
  public void run() {
    while (true) {
      try {
        T t = this.blockingDeque.take();
        this.consumerAction.consumedAction(t);
        this.consumerAction.unlock(t);
        System.out.println(Thread.currentThread().getName()+"----->take-"+t.toString());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
