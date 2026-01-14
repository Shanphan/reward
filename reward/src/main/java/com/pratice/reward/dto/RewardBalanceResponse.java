package com.pratice.reward.dto;

public class RewardBalanceResponse {

    private final String userId;
    private final long pendingPoints;

    public RewardBalanceResponse(String userId, long pendingPoints) {
        this.userId = userId;
        this.pendingPoints = pendingPoints;
    }

    public String getUserId() {
        return userId;
    }

    public long getPendingPoints() {
        return pendingPoints;
    }
}

