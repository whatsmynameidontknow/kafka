package training.kafka.final_task.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import training.kafka.final_task.avro.Operation;
import training.kafka.final_task.avro.Sesuatu;
import training.kafka.final_task.dto.SesuatuDto;
import training.kafka.final_task.repository.SesuatuRepository;
import training.kafka.final_task.service.SesuatuService;

@Service
public class SesuatuServiceImpl implements SesuatuService {

    private final SesuatuRepository sesuatuRepository;

    SesuatuServiceImpl(SesuatuRepository sesuatuRepository) {
        this.sesuatuRepository = sesuatuRepository;
    }

    @Override
    public Double calculateAndSave(Sesuatu data) {
        training.kafka.final_task.entity.Sesuatu sesuatu =
                new training.kafka.final_task.entity.Sesuatu();
        sesuatu.setOperation(SesuatuDto.fromOperationEnum(data.getOperation()));
        sesuatu.setNumbers(
                data.getNumbers().stream().map(String::valueOf).collect(Collectors.joining(",")));
        sesuatu.setResult(calculate(data));
        sesuatu = sesuatuRepository.save(sesuatu);
        return sesuatu.getResult();
    }

    @Override
    public List<SesuatuDto> list() {
        List<training.kafka.final_task.entity.Sesuatu> data = sesuatuRepository.findAll();
        return data.stream().map(SesuatuDto::new).toList();
    }


    private Double calculate(Sesuatu data) {
        switch (data.getOperation()) {
            case Operation.ADD:
                return data.getNumbers().stream().reduce(0d, Double::sum);
            case Operation.SUBTRACT:
                return data.getNumbers().subList(1, data.getNumbers().size()).stream()
                        .reduce(data.getNumbers().getFirst(), (t, u) -> t - u);
            case Operation.MULTIPLY:
                return data.getNumbers().stream().reduce(1d, (t, u) -> t * u);
            case Operation.DIVIDE:
                return data.getNumbers().subList(1, data.getNumbers().size()).stream()
                        .reduce(data.getNumbers().getFirst(), (t, u) -> t / u);
        }
        return null;
    }
}
