package com.example.hyerarchyexperiment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Main extends AbstractBehavior<String> {

    static Behavior<String> create() {
        return Behaviors.setup(Main::new);
    }
    private Main(ActorContext<String> context) {
        super(context);
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder().onMessageEquals("start", this::start).build();
    }

    private Behavior<String> start() {
        ActorRef<String> firstActor = getContext().spawn(PrintMyActorRefActor.create(), "first-actor");
        getContext().getLog().info("First actor {}", firstActor);

        firstActor.tell("printit");

        return Behaviors.same();
    }
}
