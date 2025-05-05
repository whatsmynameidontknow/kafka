package training.kafka.final_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sesuatu")
public class Sesuatu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numbers;

    private String operation;

    private Double result;
}
