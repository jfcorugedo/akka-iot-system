package com.example;

import akka.actor.typed.ActorSystem;

import java.io.IOException;

public class AkkaQuickstart {
  public static void main(String[] args) throws IOException {
    //#actor-system
    final ActorSystem<GreeterMain.SayHello> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");
    //#actor-system

    //#main-send-messages
    greeterMain.tell(new GreeterMain.SayHello("Charles"));
    //#main-send-messages

    try {
      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    }  finally {
      greeterMain.terminate();
    }
  }
}
