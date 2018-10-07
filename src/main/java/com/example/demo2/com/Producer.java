package com.example.demo2.com;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Producer {


    @Autowired
    private KafkaProducer producer;

    @Value("${kafka.topic}")
    private String topic;


    public void sendMsg(String message) throws InterruptedException {
        try {
            Future wrwr = producer.send(new ProducerRecord<String, String>(topic, message));
            System.out.println("send message: (topic=" + topic + "," + "message=" + message + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



