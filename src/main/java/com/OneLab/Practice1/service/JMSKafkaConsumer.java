package com.OneLab.Practice1.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JMSKafkaConsumer {

    // Listener for general messages
    @KafkaListener(topics = "my_topic", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    // Listener for new product notifications
    @KafkaListener(topics = "new_product_notifications", groupId = "product-notifications-group")
    public void onNewProductNotification(String message) {
        System.out.println("New Product Notification: " + message);
    }
}


