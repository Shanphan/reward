package com.pratice.reward.controller;

import com.pratice.reward.dto.RewardBalanceResponse;
import com.pratice.reward.service.RewardQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardQueryService rewardQueryService;

    public RewardController(RewardQueryService rewardQueryService) {
        this.rewardQueryService = rewardQueryService;
    }

    @GetMapping("/balance")
    public ResponseEntity<RewardBalanceResponse> getBalance(
            @RequestParam String userId) {

        long pendingPoints = rewardQueryService.getPendingPoints(userId);

        return ResponseEntity.ok(
                new RewardBalanceResponse(userId, pendingPoints)
        );
    }
}

