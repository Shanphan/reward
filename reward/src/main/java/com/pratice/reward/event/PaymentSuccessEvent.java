package com.pratice.reward.event;

import java.time.Instant;

public class PaymentSuccessEvent {

    private final String paymentId;
    private final String orderId;
    private final String userId;
    private final long amountPaid;
    private final Instant occurredAt;

    public PaymentSuccessEvent(
            String paymentId,
            String orderId,
            String userId,
            long amountPaid
    ) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.occurredAt = Instant.now();
    }

    public String getPaymentId() { return paymentId; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public long getAmountPaid() { return amountPaid; }
}

