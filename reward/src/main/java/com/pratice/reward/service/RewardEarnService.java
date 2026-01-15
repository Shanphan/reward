package com.pratice.reward.service;

import com.pratice.reward.entity.RewardAccount;
import com.pratice.reward.entity.RewardTransaction;
import com.pratice.reward.repo.RewardAccountRepository;
import com.pratice.reward.repo.RewardTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RewardEarnService {

    private final RewardAccountRepository rewardAccountRepository;
    private final RewardTransactionRepository rewardTransactionRepository;

    public RewardEarnService(
            RewardAccountRepository rewardAccountRepository,
            RewardTransactionRepository rewardTransactionRepository
    ) {
        this.rewardAccountRepository = rewardAccountRepository;
        this.rewardTransactionRepository = rewardTransactionRepository;
    }

    public void earnPoints(String userId, String sourceRefId, long points) {

        // 1️⃣ Idempotency check
        if (rewardTransactionRepository
                .findBySourceRefIdAndType(sourceRefId, RewardTransaction.Type.EARN)
                .isPresent()) {
            return; // already processed
        }

        // 2️⃣ Create ledger entry
        RewardTransaction txn = new RewardTransaction(
                UUID.randomUUID().toString(),
                userId,
                RewardTransaction.Type.EARN,
                points,
                sourceRefId
        );

        rewardTransactionRepository.save(txn);

        // 3️⃣ Update RewardAccount (derived state)
        RewardAccount account = rewardAccountRepository
                .findByUserId(userId)
                .orElse(new RewardAccount(userId, 0));

        account.setTotalPoints(account.getTotalPoints() + points);
        rewardAccountRepository.save(account);
    }
}


