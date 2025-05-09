package training.kafka.final_task.kafka.producer;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import training.kafka.final_task.avro.Sesuatu;

@Service
public class SesuatuProducer {
    private final KafkaTemplate<Long, Sesuatu> template;

    private final KafkaTemplate<Void, Void> anotherTemplate;

    @Value("${topic.sesuatu.name}")
    private String sesuatuTopicName;

    @Value("${topic.results.name}")
    private String resultsTopicName;

    SesuatuProducer(KafkaTemplate<Long, Sesuatu> template,
            KafkaTemplate<Void, Void> anotherTemplate) {
        this.template = template;
        this.anotherTemplate = anotherTemplate;
    }

    public void send(Sesuatu sesuatu) {
        var sent = template.send(sesuatuTopicName, (new Date()).getTime(), sesuatu);
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

    public void send() {
        var sent = anotherTemplate.send(resultsTopicName, null);
        sent.whenComplete((res, ex) -> {
            if (null == ex) {
                System.out.printf("Successfully sent to %s%n", resultsTopicName);
            } else {
                System.out.printf("Failed to send message to %s%n", resultsTopicName);
            }
        });
    }
}
