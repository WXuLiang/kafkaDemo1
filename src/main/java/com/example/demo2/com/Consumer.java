package com.example.demo2.com;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Component
public class Consumer {


   // @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record) throws InterruptedException {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        Optional<?> kafkaKey = Optional.ofNullable(record.key());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("listen2 :" + message );
            Thread.sleep(1000);
        }
    }


    @KafkaListener(topics = "test")
    public void listen2(String record) {
        System.out.println("listen1 :" + record );
    }
}

