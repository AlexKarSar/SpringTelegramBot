package ru.handh.testkafka.listener;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {



    @KafkaListener(topics = "test", groupId = "test")
    public void listener(String message){
        System.out.println("Message: " + message);

    }

}
