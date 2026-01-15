package com.pratice.reward.repo;

import com.pratice.reward.entity.RewardTransaction;

import java.util.Optional;

public interface RewardTransactionRepository {

    Optional<RewardTransaction> findBySourceRefIdAndType(
            String sourceRefId,
            RewardTransaction.Type type
    );

    void save(RewardTransaction transaction);
}
