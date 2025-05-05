package training.kafka.final_task.kafka.producer;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import training.kafka.final_task.avro.Sesuatu;

@Service
public class SesuatuProducer {
    private final KafkaTemplate<Long, Sesuatu> template;

    @Value("${topic.sesuatu.name}")
    private String topicName;

    SesuatuProducer(KafkaTemplate<Long, Sesuatu> template) {
        this.template = template;
    }

    public void send(Sesuatu sesuatu) {
        var sent = template.send(topicName, (new Date()).getTime(), sesuatu);
        sent.whenComplete((res, ex) -> {
            if (null == ex) {
                System.out.printf("Message sent = Sesuatu(numbers = %v, operation = %v)%n",
                        sesuatu.getNumbers(), sesuatu.getOperation());
            } else {
                System.out.printf(
                        "Failed to send message = Sesuatu(numbers = %v, operation = %v)%n",
                        sesuatu.getNumbers(), sesuatu.getOperation());
            }
        });
    }
}
