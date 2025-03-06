package com.example.Subscription.Management.Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionRepository, Long> {
    List<SubscriptionRepository> findByUserId(Long userId);
}
