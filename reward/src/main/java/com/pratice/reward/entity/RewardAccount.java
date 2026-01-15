package com.pratice.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RewardAccount {

    private final String userId;
    private long totalPoints;
}
