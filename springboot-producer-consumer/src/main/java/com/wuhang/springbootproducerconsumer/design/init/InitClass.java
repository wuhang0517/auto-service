package com.wuhang.springbootproducerconsumer.design.init;

import com.wuhang.springbootproducerconsumer.design.pojo.Consumer;
import com.wuhang.springbootproducerconsumer.design.pojo.Producer;
import com.wuhang.springbootproducerconsumer.design.service.ConsumerAction;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Data
public class InitClass<T> {

  //生产者线程数
  private int producerThreadPoolSize;

  //消费者线程数
  private int consumerThreadPoolSize;

  //队列大小
  private int blockingQueueSize;

  //message
  private List<T> list;

  @Autowired
  ConsumerAction<T> consumerAction;

  public InitClass(int producerThreadPoolSize, int consumerThreadPoolSize, int blockingQueueSize, List<T> list,ConsumerAction consumerAction) {
    this.producerThreadPoolSize = producerThreadPoolSize;
    this.consumerThreadPoolSize = consumerThreadPoolSize;
    this.blockingQueueSize = blockingQueueSize;
    this.list = list;
    this.consumerAction = consumerAction;
  }

  public InitClass() {
  }

  public void action() throws Exception{
    BlockingDeque<T> blockingDeque = new LinkedBlockingDeque<>(this.blockingQueueSize);
    Producer<T> producer = new Producer<>(blockingDeque, list);
    Executor producerExecutor = Executors.newScheduledThreadPool(this.producerThreadPoolSize);
    //创建生产者
    for (int i = 0; i < this.producerThreadPoolSize; i++) {
      producerExecutor.execute(producer);
    }
    Executor comsumerExecutor = Executors.newScheduledThreadPool(this.consumerThreadPoolSize);
    Consumer<T> consumer = new Consumer<>(blockingDeque, this.consumerAction);
    //创建消费者
    for (int i = 0; i < this.consumerThreadPoolSize; i++) {
      comsumerExecutor.execute(consumer);
    }
  }
}
