package com.pratice.reward.entity;

import java.time.Instant;

public class RewardTransaction {

    public enum Type {
        EARN,
        REDEEM,
        EXPIRE
    }

    private final String txnId;
    private final String userId;
    private final Type type;
    private final long points;
    private final String sourceRefId;
    private final Instant createdAt;

    public RewardTransaction(
            String txnId,
            String userId,
            Type type,
            long points,
            String sourceRefId
    ) {
        this.txnId = txnId;
        this.userId = userId;
        this.type = type;
        this.points = points;
        this.sourceRefId = sourceRefId;
        this.createdAt = Instant.now();
    }

    public String getTxnId() {
        return txnId;
    }

    public String getUserId() {
        return userId;
    }

    public Type getType() {
        return type;
    }

    public long getPoints() {
        return points;
    }

    public String getSourceRefId() {
        return sourceRefId;
    }
}

