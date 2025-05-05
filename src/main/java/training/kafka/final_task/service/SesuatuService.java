package training.kafka.final_task.service;

import java.util.List;
import training.kafka.final_task.avro.Sesuatu;
import training.kafka.final_task.dto.SesuatuDto;

public interface SesuatuService {
    Double calculateAndSave(Sesuatu data);

    List<SesuatuDto> list();
}
