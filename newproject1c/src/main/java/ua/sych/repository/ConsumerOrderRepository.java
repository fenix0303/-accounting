package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.ConsumerOrder;

@Repository
public interface ConsumerOrderRepository extends JpaRepository<ConsumerOrder, Long> {
}
