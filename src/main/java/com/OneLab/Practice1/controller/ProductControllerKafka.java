package com.OneLab.Practice1.controller;

import com.OneLab.Practice1.service.JMSKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductControllerKafka {

    private JMSKafkaProducer producer;

    @Autowired
    ProductControllerKafka(JMSKafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description) {

        producer.sendNewProductNotification(name, description);
        return "Product added and notification sent!";
    }
}

