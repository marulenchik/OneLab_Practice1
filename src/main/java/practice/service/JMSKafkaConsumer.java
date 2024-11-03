package practice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JMSKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JMSKafkaConsumer.class);

    // Listener for general messages
    @KafkaListener(topics = "my_topic", groupId = "my-group-id")
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }

    // Listener for new product notifications
    @KafkaListener(topics = "new_product_notifications", groupId = "product-notifications-group")
    public void onNewProductNotification(String message) {
        logger.info("New Product Notification: {}", message);
    }
}
