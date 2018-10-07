package com.example.demo2.com;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
@EnableKafka
public class KafkaConfig {


    @Value("${kafka.bootstrap-servers}")
    private String bootstrap_servers;
    @Value("${kafka.producer.batch_size}")
    private String batch_siz;
    @Value("${kafka.producer.linger_ms}")
    private String linger_ms;
    @Value("${kafka.producer.key_serializer}")
    private String key_serializer;
    @Value("${kafka.producer.value_serializer}")
    private String value_serializer;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put("bootstrap.servers", this.bootstrap_servers);
        propsMap.put("enable.auto.commit", false);
        propsMap.put("auto.commit.interval.ms", "100");
        propsMap.put("session.timeout.ms", "15000");
        propsMap.put("key.deserializer", StringDeserializer.class);
        propsMap.put("value.deserializer", StringDeserializer.class);
        propsMap.put("group.id", "test");
        propsMap.put("auto.offset.reset", "latest");
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(propsMap));
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public KafkaProducer kafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrap_servers);
        //props.put("client.id", "test");
        //batch.size和linger.ms分别从数量和时间上实现批量发送。
        props.put("linger.ms", linger_ms);
        props.put("batch.size", batch_siz);//16M
        props.put("buffer.memory", 33554432);//32M
        props.put("key.serializer", key_serializer);
        props.put("value.serializer", value_serializer);
        return new KafkaProducer<>(props);
    }


}
