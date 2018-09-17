package com.wuhang.springbootproducerconsumer.design2.controller;

import com.wuhang.springbootproducerconsumer.design2.modeldesign.AutoService;
import com.wuhang.springbootproducerconsumer.design2.util.ReflectHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
public class TestController {

  public AutoService getAutoService() {
    AutoService autoService = (AutoService)ReflectHelper.getClassInstance(AutoService.class.getName());
    return autoService;
  }

  public String getStatus() {
    return getAutoService().status();
  }

  public String stop() {
    getAutoService().stop();
    return "ok";
  }

}
