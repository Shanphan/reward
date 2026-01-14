package com.pratice.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter

public class RewardAccount {

    private final String userId;
    private long totalPoints;
}
