package com.pratice.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RedeemRewardRequest {

    private String userId;
    private String sourceRefId; // checkoutId / orderId
    private long points;
}
