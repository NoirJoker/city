package com.example.city;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class CityApplication {

  public static void main(String[] args) {
    SpringApplication.run(CityApplication.class, args);
  }

  @EnableJms
  @Configuration
  public class ActiveMQConfiguration {

    @Bean
    public Queue createQueue(){
      return new ActiveMQQueue("local.inmemory.queue");
    }
  }

}
