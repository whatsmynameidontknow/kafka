package training.kafka.final_task.util;

import java.util.stream.Collectors;
import training.kafka.final_task.avro.Operation;
import training.kafka.final_task.avro.Sesuatu;

public class SesuatuUtil {

    private SesuatuUtil() {}

    public static String stringRepresentationOf(Sesuatu sesuatu) {
        String operation = getOperationSymbol(sesuatu.getOperation());
        return sesuatu.getNumbers().stream().map(String::valueOf)
                .collect(Collectors.joining(" " + operation + " "));
    }

    private static String getOperationSymbol(Operation operation) {
        switch (operation) {
            case Operation.ADD:
                return "+";
            case Operation.SUBTRACT:
                return "-";
            case Operation.DIVIDE:
                return "÷";
            case Operation.MULTIPLY:
                return "×";
        }
        return " ";
    }
}
