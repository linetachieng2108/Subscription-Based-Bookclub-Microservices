package com.example.Subscription.Management.Service.controller;

import com.example.Subscription.Management.Service.entity.Subscription;
import com.example.Subscription.Management.Service.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionContoller {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/plans")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> createSubscription(@RequestParam Long user_id, @RequestParam Long planId) {
//        return ResponseEntity.ok("Subscription successful");
        subscriptionService.createSubscription(user_id, planId);
        return ResponseEntity.ok("Subscription successful");
    }

}
