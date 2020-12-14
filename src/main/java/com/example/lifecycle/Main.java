package com.example.lifecycle;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n*****************************\n* Akka lifecycle experiment *\n*****************************\n");
        ActorRef<String> testSystem = ActorSystem.create(StartStopActor1.create(), "testSystem");

        testSystem.tell("stop");
    }
}
