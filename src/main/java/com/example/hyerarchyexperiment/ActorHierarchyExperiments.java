package com.example.hyerarchyexperiment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;

public class ActorHierarchyExperiments {

    public static void main(String[] args) {

        System.out.println("\n*****************************\n* Akka hierarchy experiment *\n*****************************\n");
        ActorRef<String> testSystem = ActorSystem.create(Main.create(), "testSystem");

        testSystem.tell("start");
    }
}
