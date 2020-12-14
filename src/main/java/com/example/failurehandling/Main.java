package com.example.failurehandling;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n********************************\n* Akka failure handling experiment *\n********************************\n");
        ActorRef<String> testSystem = ActorSystem.create(SupervisingActor.create(), "testSystem");

        testSystem.tell("failChild");
    }
}
