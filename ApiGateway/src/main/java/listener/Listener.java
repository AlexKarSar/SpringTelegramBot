package listener;



import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {

    @KafkaListener(topics = "test", groupId = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listener(){
        System.out.println("Message: ");

    }

}
