package com.example.city;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

  @JmsListener(destination = "local.inmemory.queue")
  public void onMessage(String message){
    System.out.println(message);
  }

}
