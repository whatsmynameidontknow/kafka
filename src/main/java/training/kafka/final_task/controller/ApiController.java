package training.kafka.final_task.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import training.kafka.final_task.dto.SesuatuDto;
import training.kafka.final_task.kafka.producer.SesuatuProducer;
import training.kafka.final_task.service.SesuatuService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final SesuatuProducer sesuatuProducer;

    private final SesuatuService sesuatuService;

    ApiController(SesuatuProducer sesuatuProducer, SesuatuService sesuatuService) {
        this.sesuatuProducer = sesuatuProducer;
        this.sesuatuService = sesuatuService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody @Valid SesuatuDto data) {
        sesuatuProducer.send(data.toSesuatu());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/results")
    public ResponseEntity<List<SesuatuDto>> list() {
        var data = sesuatuService.list();
        return ResponseEntity.ok(data);
    }
}
