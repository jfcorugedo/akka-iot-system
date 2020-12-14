package com.example.failurehandling;

import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.PreRestart;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class SupervisedActor extends AbstractBehavior<String> {

    static Behavior<String> create() {
        return Behaviors.setup(SupervisedActor::new);
    }

    SupervisedActor(ActorContext<String> context) {
        super(context);

        context.getLog().info("SupervisedActor created!!");
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("fail", this::fail)
                .onSignal(PreRestart.class, this::onPreRestart)
                .onSignal(PostStop.class, this::onPostStop)
                .build();
    }

    private Behavior<String> fail() {
        getContext().getLog().info("SupervisedActor is failing now!!");
        throw new RuntimeException("Unexpected error");
    }

    private Behavior<String> onPreRestart(PreRestart signal) {
        getContext().getLog().info("SupervisedActor will be restarted ({})!!", signal);
        return this;
    }

    private Behavior<String> onPostStop(PostStop signal) {
        getContext().getLog().info("SupervisedActor stopped ({})!!", signal);
        return this;
    }


}
