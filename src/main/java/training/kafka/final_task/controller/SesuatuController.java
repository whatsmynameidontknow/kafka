package training.kafka.final_task.controller;

import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import training.kafka.final_task.dto.SesuatuDto;
import training.kafka.final_task.service.SesuatuService;



@Controller
@RequestMapping("/")
public class SesuatuController {
    private final SesuatuService sesuatuService;

    SesuatuController(SesuatuService sesuatuService) {
        this.sesuatuService = sesuatuService;
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @MessageMapping("/connect")
    @SendTo("/topic/results")
    public List<SesuatuDto> list() {
        return sesuatuService.list();
    }

}
