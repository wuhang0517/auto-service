package com.wuhang.springbootproducerconsumer.design2.modeldesign;

import com.wuhang.springbootproducerconsumer.design2.inter.InterConsumer;
import com.wuhang.springbootproducerconsumer.design2.inter.InterProducer;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;



public class AutoService<T> {

  //生产者线程数
  private int threadProducePoolSize;

  //消费者线程数
  private int threadConsumerPoolSize;

  //阻塞对列
  private BlockingDeque<T> blockingDeque;

  //生产者生产数据行为
  private InterProducer interProducer;

  //消费者消费数据行为
  private InterConsumer interConsumer;

  //停止标志
  private boolean stopDeque = true;

  //生产者线程池
  private Executor executorProducer;

  //生产者
  private Producer producer;

  //消费者线程池
  private Executor executorConsumer;

  //消费者
  private Consumer consumer;

  public AutoService(int threadProducePoolSize, int threadConsumerPoolSize) {
    this.threadProducePoolSize = threadProducePoolSize;
    this.threadConsumerPoolSize = threadConsumerPoolSize;
    this.blockingDeque = new LinkedBlockingDeque<>(10);
    this.executorProducer = Executors.newScheduledThreadPool(threadProducePoolSize);
    this.producer = new Producer();
    this.executorConsumer = Executors.newScheduledThreadPool(threadConsumerPoolSize);
    this.consumer = new Consumer();
  }

  public InterProducer getInterProducer() {
    return interProducer;
  }

  public void setInterProducer(InterProducer interProducer) {
    this.interProducer = interProducer;
  }

  public InterConsumer getInterConsumer() {
    return interConsumer;
  }

  public void setInterConsumer(InterConsumer interConsumer) {
    this.interConsumer = interConsumer;
  }

  public void stop() {
    this.stopDeque = false;
  }

  public void start() {
    this.stopDeque = true;
    for (int i = 0; i < this.threadProducePoolSize; i++) {
      this.executorProducer.execute(this.producer);
    }
    for (int i = 0; i < this.threadConsumerPoolSize; i++) {
      this.executorConsumer.execute(this.consumer);
    }
  }

  public String status() {
    String status = "";
    if (this.stopDeque = false && this.blockingDeque.size() == 0) {
      //关闭状态
      status = "1";
    } else if (this.stopDeque = false && this.blockingDeque.size() != 0) {
      //正在关闭
      status = "2";
    } else if (this.stopDeque = true) {
      //运行状态
      status = "3";
    }
    return status;
  }

  class Producer implements Runnable {

    @Override
    public void run() {
      while (stopDeque) {
        List<T> interProducerMessage = interProducer.createMessage();
        try {
          for (Iterator<T> iterator = interProducerMessage.iterator(); iterator.hasNext(); ) {
            T t = iterator.next();
            blockingDeque.put(t);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {
      while (blockingDeque.size() != 0 || stopDeque) {
        try {
          T t = blockingDeque.take();
          interConsumer.actionConsumer(t);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }

}
