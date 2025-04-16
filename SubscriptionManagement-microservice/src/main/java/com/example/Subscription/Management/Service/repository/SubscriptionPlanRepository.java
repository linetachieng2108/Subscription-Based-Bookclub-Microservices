package com.example.Subscription.Management.Service.repository;

import com.example.Subscription.Management.Service.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
}
