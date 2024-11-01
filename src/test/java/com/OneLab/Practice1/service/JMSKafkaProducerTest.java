package com.OneLab.Practice1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

class JMSKafkaProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private JMSKafkaProducer producer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNewProductNotification() {
        // Arrange
        String productName = "TestProduct";
        String productDescription = "This is a test product description";

        // Act
        producer.sendNewProductNotification(productName, productDescription);

        // Assert
        String expectedMessage = "New product alert! Check out TestProduct: This is a test product description";
        verify(kafkaTemplate).send("new_product_notifications", expectedMessage);
    }
}
