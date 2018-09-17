package com.wuhang.springbootproducerconsumer.design2.main;

import com.wuhang.springbootproducerconsumer.design2.inter.impl.UserConsumerImpl;
import com.wuhang.springbootproducerconsumer.design2.inter.impl.UserProducerImpl;
import com.wuhang.springbootproducerconsumer.design2.modeldesign.AutoService;
import com.wuhang.springbootproducerconsumer.inter.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/test")
public class Test implements CommandLineRunner {

  AutoService<User> autoService = new AutoService<>(1,3);

  @Override
  public void run(String... args) throws Exception {

    autoService.setInterProducer(new UserProducerImpl());
    autoService.setInterConsumer(new UserConsumerImpl());
    autoService.start();
  }

  @RequestMapping("/status")
  public String getStatus() {
    return autoService.status();
  }

  @RequestMapping("/stop")
  public String setStop() {
    autoService.stop();
    return "ok";
  }
}
