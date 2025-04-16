package com.example.Subscription.Management.Service.controller;

import com.example.Subscription.Management.Service.entity.SubscriptionPlan;
import com.example.Subscription.Management.Service.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription-plans")
public class SubscriptionPlanController {
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    // Get all subscription plans
    @GetMapping
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanService.getAllSubscriptionPlans();
    }

    @PostMapping
    public SubscriptionPlan createSubscriptionPlan(@RequestBody SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanService.createSubscriptionPlan(subscriptionPlan);
    }

    // Update an existing subscription plan
    @PutMapping("/{planId}")
    public SubscriptionPlan updateSubscriptionPlan(
            @PathVariable Long planId,
            @RequestBody SubscriptionPlan updatedPlan) {
        return subscriptionPlanService.updateSubscriptionPlan(planId, updatedPlan);
    }

    // Delete a subscription plan
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deleteSubscriptionPlan(@PathVariable Long planId) {
        subscriptionPlanService.deleteSubscriptionPlan(planId);
        return ResponseEntity.noContent().build();
    }

}
