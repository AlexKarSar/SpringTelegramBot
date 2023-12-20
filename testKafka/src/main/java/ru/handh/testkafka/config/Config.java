package ru.handh.testkafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Map;

@EnableKafka
@Configuration
public class Config {


    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;



    @Bean
    ConsumerFactory<Object, Object> kafkaConsumerFactory(){
        Map<String, Object> props = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapAddress,
                ConsumerConfig.GROUP_ID_CONFIG ,  "test",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG , "earliest",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG ,  StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class

        );
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(){
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return  factory;
    }

}
