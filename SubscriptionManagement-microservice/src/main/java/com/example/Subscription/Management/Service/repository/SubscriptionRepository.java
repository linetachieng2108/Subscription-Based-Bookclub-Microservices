package com.example.Subscription.Management.Service.repository;

import com.example.Subscription.Management.Service.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
//    List<Subscription> findByUserId(Long userId);
}
