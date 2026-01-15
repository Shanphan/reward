package com.pratice.reward.kafka;

import com.pratice.reward.event.EventHandler;
import com.pratice.reward.event.PaymentSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryKafka {

    private final List<EventHandler<PaymentSuccessEvent>> subscribers = new ArrayList<>();

    public void publish(PaymentSuccessEvent event) {
        // async simulation (non-blocking)
        for (EventHandler<PaymentSuccessEvent> handler : subscribers) {
            handler.handle(event);
        }
    }

    public void subscribe(EventHandler<PaymentSuccessEvent> handler) {
        subscribers.add(handler);
    }
}

