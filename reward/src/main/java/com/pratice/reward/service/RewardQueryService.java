package com.pratice.reward.service;

import com.pratice.reward.entity.RewardAccount;
import com.pratice.reward.repo.RewardAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class RewardQueryService {

    private final RewardAccountRepository rewardAccountRepository;

    public RewardQueryService(RewardAccountRepository rewardAccountRepository) {
        this.rewardAccountRepository = rewardAccountRepository;
    }

    public long getPendingPoints(String userId) {
        return rewardAccountRepository
                .findByUserId(userId)
                .map(RewardAccount::getTotalPoints)
                .orElse(0L);
    }
}

