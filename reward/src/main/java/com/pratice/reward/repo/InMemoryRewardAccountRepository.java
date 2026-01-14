package com.pratice.reward.repo;

import com.pratice.reward.entity.RewardAccount;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRewardAccountRepository implements RewardAccountRepository {

    // Acts like a DB table: userId -> RewardAccount
    private final Map<String, RewardAccount> store = new ConcurrentHashMap<>();

    @Override
    public Optional<RewardAccount> findByUserId(String userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public void save(RewardAccount rewardAccount) {
        store.put(rewardAccount.getUserId(), rewardAccount);
    }
}

