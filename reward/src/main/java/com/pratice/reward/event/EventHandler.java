package com.pratice.reward.event;

public interface EventHandler<T> {
    void handle(T event);
}

