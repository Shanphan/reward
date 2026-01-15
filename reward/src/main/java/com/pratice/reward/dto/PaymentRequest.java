package com.pratice.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentRequest {

    private String orderId;
    private String userId;
    private long amount;
}

