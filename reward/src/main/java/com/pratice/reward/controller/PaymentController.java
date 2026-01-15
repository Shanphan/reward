package com.pratice.reward.controller;

import com.pratice.reward.dto.PaymentRequest;
import com.pratice.reward.kafka.InMemoryKafka;
import com.pratice.reward.event.PaymentSuccessEvent;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final InMemoryKafka kafka;

    public PaymentController(InMemoryKafka kafka) {
        this.kafka = kafka;
    }

    @PostMapping("/complete")
    public void completePayment(@RequestBody PaymentRequest request) {

        // 1️⃣ Payment is assumed successful
        String paymentId = UUID.randomUUID().toString();

        // 2️⃣ Create event
        PaymentSuccessEvent event = new PaymentSuccessEvent(
                paymentId,
                request.getOrderId(),
                request.getUserId(),
                request.getAmount()
        );

        // 3️⃣ Publish event (Kafka Producer)
        kafka.publish(event);
    }
}
