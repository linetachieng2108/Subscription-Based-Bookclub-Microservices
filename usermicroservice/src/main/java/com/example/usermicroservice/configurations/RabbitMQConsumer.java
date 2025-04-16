package com.example.usermicroservice.configurations;

import com.example.usermicroservice.entity.User;
import com.example.usermicroservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(User user){
        System.out.println("Received message:" + user);
        userRepository.save(user);
    }
}
