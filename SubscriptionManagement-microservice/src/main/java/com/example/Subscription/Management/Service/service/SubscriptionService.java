package com.example.Subscription.Management.Service.service;

import com.example.Subscription.Management.Service.UserManagementClient;
import com.example.Subscription.Management.Service.entity.Subscription;
import com.example.Subscription.Management.Service.entity.SubscriptionPlan;
import com.example.Subscription.Management.Service.repository.SubscriptionPlanRepository;
import com.example.Subscription.Management.Service.repository.SubscriptionRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private UserManagementClient userManagementClient;

    public List<Subscription> getAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    public Subscription createSubscription(Long user_id, Long planId) {
        //Call User Service to check if user exists
        Boolean userExists = restTemplate.getForObject("http://localhost:8080/users/exists/{userId}" , Boolean.class, user_id);

        if (Boolean.FALSE.equals(userExists)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
        }
        // Fetch user details from User Management Service
//        Boolean user = userManagementClient.getUserById(user_id);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }

        // Proceed with subscription creation
        SubscriptionPlan plan = (SubscriptionPlan) subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Subscription subscription = new Subscription();
        subscription.setUser_id(user_id);
        subscription.setPlanId(plan.getPlanId());
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(plan.getDurationDays()));
        subscription.setStatus("Active");

        return subscriptionRepository.save(subscription);

    }
}

