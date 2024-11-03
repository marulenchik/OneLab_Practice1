package practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JMSKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public JMSKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String PRODUCT_NOTIFICATION_TOPIC = "new_product_notifications";

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendNewProductNotification(String productName, String productDescription) {
        String message = String.format("New product alert! Check out %s: %s", productName, productDescription);
        kafkaTemplate.send(PRODUCT_NOTIFICATION_TOPIC, message);
    }
}


