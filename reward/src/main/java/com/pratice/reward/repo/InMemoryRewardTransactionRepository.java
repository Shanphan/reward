package com.pratice.reward.repo;

import com.pratice.reward.entity.RewardTransaction;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRewardTransactionRepository implements RewardTransactionRepository {

    // Key = sourceRefId + type
    private final Map<String, RewardTransaction> store =
            new ConcurrentHashMap<>();

    private String key(String sourceRefId, RewardTransaction.Type type) {
        return sourceRefId + "::" + type.name();
    }

    @Override
    public Optional<RewardTransaction> findBySourceRefIdAndType(
            String sourceRefId,
            RewardTransaction.Type type
    ) {
        return Optional.ofNullable(store.get(key(sourceRefId, type)));
    }

    @Override
    public void save(RewardTransaction transaction) {
        store.put(
                key(transaction.getSourceRefId(), transaction.getType()),
                transaction
        );
    }
}

