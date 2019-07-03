package com.xlx.xxwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @GetMapping("/")
  public String index(){
    return "index";
  }
}
