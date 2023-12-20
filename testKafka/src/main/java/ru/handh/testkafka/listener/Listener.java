package ru.handh.testkafka.listener;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.handh.testkafka.request.AddRequest;


@Component
public class Listener {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "test", groupId = "test")
    public void listener(String message){
        var request = new AddRequest(message);
        new RestTemplate().postForEntity("http://localhost:8085/user-service/add", new AddRequest(message), String.class);
    }

}
