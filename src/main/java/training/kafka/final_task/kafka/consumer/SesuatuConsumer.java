package training.kafka.final_task.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import training.kafka.final_task.avro.Sesuatu;
import training.kafka.final_task.kafka.producer.SesuatuProducer;
import training.kafka.final_task.service.SesuatuService;
import training.kafka.final_task.util.SesuatuUtil;

@Slf4j
@Service
public class SesuatuConsumer {

    @Autowired
    private SesuatuService sesuatuService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SesuatuProducer sesuatuProducer;

    @RetryableTopic(attempts = "1")
    @KafkaListener(topics = "${topic.sesuatu.name}", groupId = "group-01")
    public void sesuatuConsumer(ConsumerRecord<Long, Sesuatu> record) {
        try {
            log.info("Processing message: key={}, value={}", record.key(), record.value());
            Sesuatu data = record.value();

            if (data == null) {
                log.error("Received null message value");
                return;
            }

            Double result = sesuatuService.calculateAndSave(data);
            System.out.printf("Result of %s = %f%n", SesuatuUtil.stringRepresentationOf(data),
                    result);
            sesuatuProducer.send();
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
            throw e;
        }
    }

    @KafkaListener(topics = "${topic.results.name}", groupId = "group-01")
    public void resultsConsumer() {
        simpMessagingTemplate.convertAndSend("/topic/results", sesuatuService.list());
    }
}
