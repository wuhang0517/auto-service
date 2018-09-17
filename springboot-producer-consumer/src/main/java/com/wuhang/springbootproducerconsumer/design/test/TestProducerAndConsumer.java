package com.wuhang.springbootproducerconsumer.design.test;

import com.wuhang.springbootproducerconsumer.design.init.InitClass;
import com.wuhang.springbootproducerconsumer.design.model.User;
import com.wuhang.springbootproducerconsumer.design.pojo.Consumer;
import com.wuhang.springbootproducerconsumer.design.pojo.Producer;
import com.wuhang.springbootproducerconsumer.design.service.ConsumerAction;
import com.wuhang.springbootproducerconsumer.design.service.impl.ConsumerActionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

public class TestProducerAndConsumer  {

  public static void main(String[] args) throws Exception{


  }

  public void run(String... args) throws Exception {
    ConsumerAction<User> consumerAction = new ConsumerActionImpl();

    int producerThreadPoolSize=1;
    int consumerThreadPoolSize=4;
    int blockingQueueSize=5;

    InitClass<User> initClass = new InitClass<>();
    initClass.setProducerThreadPoolSize(producerThreadPoolSize);
    initClass.setConsumerThreadPoolSize(consumerThreadPoolSize);
    initClass.setBlockingQueueSize(blockingQueueSize);
    initClass.setConsumerAction(consumerAction);

    initClass.action();
  }
}
