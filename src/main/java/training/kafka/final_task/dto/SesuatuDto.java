package training.kafka.final_task.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.kafka.final_task.avro.Operation;
import training.kafka.final_task.entity.Sesuatu;

@NoArgsConstructor
@Data
public class SesuatuDto {

    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @NotNull
    private List<Double> numbers;

    @NotNull
    @Pattern(regexp = "(?i)^(ADD|SUBTRACT|MULTIPLY|DIVIDE)$",
            message = "Operation must be one of: ADD, SUBTRACT, MULTIPLY, DIVIDE (case insensitive)")
    private String operation;

    @JsonProperty(access = Access.READ_ONLY)
    private Double result;

    public SesuatuDto(Sesuatu entity) {
        id = entity.getId();
        operation = entity.getOperation();
        numbers = List.of(entity.getNumbers().split(",")).stream().map(Double::valueOf).toList();
        result = entity.getResult();
    }

    public training.kafka.final_task.avro.Sesuatu toSesuatu() {
        training.kafka.final_task.avro.Sesuatu sesuatu =
                new training.kafka.final_task.avro.Sesuatu();
        sesuatu.setNumbers(numbers);
        sesuatu.setOperation(fromOperationString(operation));
        return sesuatu;
    }

    public static String fromOperationEnum(Operation operation) {
        switch (operation) {
            case Operation.ADD:
                return "ADD";
            case Operation.SUBTRACT:
                return "SUBTRACT";
            case Operation.DIVIDE:
                return "DIVIDE";
            case Operation.MULTIPLY:
                return "MULTIPLY";
        }
        return "";
    }

    public static Operation fromOperationString(String operation) {
        switch (operation.toLowerCase()) {
            case "add":
                return Operation.ADD;
            case "subtract":
                return Operation.SUBTRACT;
            case "divide":
                return Operation.DIVIDE;
            case "multiply":
                return Operation.MULTIPLY;
            default:
                return null;
        }
    }
}
