package com.pratice.reward.service;

import com.pratice.reward.entity.RewardAccount;
import com.pratice.reward.entity.RewardTransaction;
import com.pratice.reward.repo.RewardAccountRepository;
import com.pratice.reward.repo.RewardTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RewardRedeemService {

    private final RewardAccountRepository rewardAccountRepository;
    private final RewardTransactionRepository rewardTransactionRepository;

    public RewardRedeemService(
            RewardAccountRepository rewardAccountRepository,
            RewardTransactionRepository rewardTransactionRepository
    ) {
        this.rewardAccountRepository = rewardAccountRepository;
        this.rewardTransactionRepository = rewardTransactionRepository;
    }

    /**
     * Strongly consistent, transactional redeem
     */
    public synchronized void redeemPoints(
            String userId,
            String sourceRefId,
            long pointsToRedeem
    ) {

        // 1️⃣ Idempotency check
        if (rewardTransactionRepository
                .findBySourceRefIdAndType(sourceRefId, RewardTransaction.Type.REDEEM)
                .isPresent()) {
            return; // already redeemed
        }

        // 2️⃣ Load account (lock is implicit via synchronized)
        RewardAccount account = rewardAccountRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalStateException("Reward account not found"));

        // 3️⃣ Balance check (NO negative balance allowed)
        if (account.getTotalPoints() < pointsToRedeem) {
            throw new IllegalStateException("Insufficient reward balance");
        }

        // 4️⃣ Create ledger entry FIRST
        RewardTransaction redeemTxn = new RewardTransaction(
                UUID.randomUUID().toString(),
                userId,
                RewardTransaction.Type.REDEEM,
                -pointsToRedeem,
                sourceRefId
        );

        rewardTransactionRepository.save(redeemTxn);

        // 5️⃣ Update derived balance
        account.setTotalPoints(account.getTotalPoints() - pointsToRedeem);
        rewardAccountRepository.save(account);
    }
}

