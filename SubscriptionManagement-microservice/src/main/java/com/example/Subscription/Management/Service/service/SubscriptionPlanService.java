package com.example.Subscription.Management.Service.service;

import com.example.Subscription.Management.Service.entity.SubscriptionPlan;
import com.example.Subscription.Management.Service.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    // Fetch all subscription plans
    public List<SubscriptionPlan> getAllSubscriptionPlans() {
        return subscriptionPlanRepository.findAll();
    }

    public SubscriptionPlan getSubscriptionPlanById(Long planId) {
        return subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Subscription plan not found"));
    }

    // Create a new subscription plan
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    // Update an existing subscription plan
    public SubscriptionPlan updateSubscriptionPlan(Long planId, SubscriptionPlan updatedPlan) {
        SubscriptionPlan existingPlan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Subscription plan not found"));

        existingPlan.setPlanName(updatedPlan.getPlanName());
        existingPlan.setDescription(updatedPlan.getDescription());
        existingPlan.setPrice(updatedPlan.getPrice());
        existingPlan.setDurationDays(updatedPlan.getDurationDays());

        return subscriptionPlanRepository.save(existingPlan);
    }

    // Delete a subscription plan
    public void deleteSubscriptionPlan(Long planId) {
        subscriptionPlanRepository.deleteById(planId);
    }

}





