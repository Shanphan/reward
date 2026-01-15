package com.pratice.reward.kafka;

import com.pratice.reward.event.EventHandler;
import com.pratice.reward.event.PaymentSuccessEvent;
import com.pratice.reward.service.RewardEarnService;
import org.springframework.stereotype.Component;

@Component
public class RewardEventConsumer implements EventHandler<PaymentSuccessEvent> {

    private final RewardEarnService rewardEarnService;

    public RewardEventConsumer(
            RewardEarnService rewardEarnService,
            InMemoryKafka kafka
    ) {
        this.rewardEarnService = rewardEarnService;

        // Register consumer (Kafka subscribe)
        kafka.subscribe(this);
    }

    @Override
    public void handle(PaymentSuccessEvent event) {

        long points = calculatePoints(event.getAmountPaid());

        rewardEarnService.earnPoints(
                event.getUserId(),
                event.getOrderId(),   // sourceRefId
                points
        );
    }

    private long calculatePoints(long amountPaid) {
        // Example rule: ₹100 = 1 point
        return amountPaid / 100;
    }
}
