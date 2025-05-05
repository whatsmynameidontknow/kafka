package training.kafka.final_task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import training.kafka.final_task.dto.SesuatuDto;
import training.kafka.final_task.kafka.producer.SesuatuProducer;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final SesuatuProducer sesuatuProducer;

    ApiController(SesuatuProducer sesuatuProducer) {
        this.sesuatuProducer = sesuatuProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody @Valid SesuatuDto data) {
        sesuatuProducer.send(data.toSesuatu());
        return ResponseEntity.ok().build();
    }
}
