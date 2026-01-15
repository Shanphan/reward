package com.pratice.reward.controller;

import com.pratice.reward.dto.RedeemRewardRequest;
import com.pratice.reward.service.RewardRedeemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardRedeemController {

    private final RewardRedeemService rewardRedeemService;

    public RewardRedeemController(RewardRedeemService rewardRedeemService) {
        this.rewardRedeemService = rewardRedeemService;
    }

    @PostMapping("/redeem")
    public ResponseEntity<Void> redeemRewards(
            @RequestBody RedeemRewardRequest request
    ) {
        rewardRedeemService.redeemPoints(
                request.getUserId(),
                request.getSourceRefId(),
                request.getPoints()
        );

        return ResponseEntity.ok().build();
    }
}
