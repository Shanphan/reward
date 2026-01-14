package com.pratice.reward.repo;

import com.pratice.reward.entity.RewardAccount;

import java.util.Optional;

public interface RewardAccountRepository {

    Optional<RewardAccount> findByUserId(String userId);

    void save(RewardAccount rewardAccount);
}