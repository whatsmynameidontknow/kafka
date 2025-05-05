package training.kafka.final_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.kafka.final_task.entity.Sesuatu;

public interface SesuatuRepository extends JpaRepository<Sesuatu, Long> {
}
